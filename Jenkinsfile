pipeline {
    agent any
    options {
        timestamps()
    }
    environment {
        SCANNER_HOME=tool 'sonar-scanner'
        DOCKERHUB_CREDENTIALS=credentials('dockerhub')
        DOCKERHUB_USERNAME='nishthapaul'
    }
    stages {
        stage ("Clone Git") {
            steps {
                git branch: 'main', url: 'https://github.com/nisharathod231/Kalopsia.git'
            }
        }
        stage ("Compile via Maven") {
            steps {
                dir('Backend') {
                    sh 'mvn clean compile'
                }
            }
        }
        stage ("Unit Tests via Maven") {
            steps {
                dir('Backend') {
                    sh 'mvn test'
                }
            }
        }
        stage ("Check Code Coverage") {
            steps {
                dir('Backend') {
                    sh 'mvn verify'
                }
            }
        }
        stage ("SonarQube Analysis") {
            steps {
                try {
                    dir('Backend') {
                            withSonarQubeEnv('sonar-kalopsia') {
                                sh ''' $SCANNER_HOME/bin/sonar-scanner \
                                -Dsonar.projectName=KalopsiaBackend \
                                -Dsonar.java.binaries=. \
                                -Dsonar.sources=src/main/java \
                                -Dsonar.sourceEncoding=UTF-8 \
                                -Dsonar.language=java \
                                -Dsonar.projectKey=KalopsiaBackend '''
                            }
                        }
                    }
                    catch (err) {
                        echo err.getMessage()
                    }
                }
            }
        }

        stage ("Build via Maven") {
            steps {
                dir('Backend') {
                    sh 'mvn install'
                }
            }
        }

        stage ("Create Docker Image of Backend") {
            steps {
                dir('Backend') {
                    sh 'docker build -t nishthapaul/kalopsia-backend -f Dockerfile.dev .'
                }
            }
        }

        stage ("Compile via NPM") {
            steps {
                dir('FrontEnd') {
                    sh 'npm install'
                }
            }
        }

        stage ("Create Docker Image of Frontend") {
            steps {
                dir('FrontEnd') {
                    sh 'docker build -t nishthapaul/kalopsia-frontend -f Dockerfile.dev .'
                }
            }
        }

        stage ("Push Docker Image") {
            steps {
                script {
                    sh 'docker login -u $DOCKERHUB_USERNAME -p $DOCKERHUB_CREDENTIALS'
                    sh 'docker push nishthapaul/kalopsia-backend'
                    sh 'docker push nishthapaul/kalopsia-frontend'
                }
            }
        }

        stage ("Run Ansible Playbook") {
            steps {
                sh 'ansible-playbook -i inventory.ini playbook.yml'
            }
        }
    }
    post {
        success {
            jacoco()
        }
        failure {
            script {
                def jenkinsBuildUrl = "http://localhost:8080/job/${env.JOB_NAME}/${env.BUILD_NUMBER}/console"
                mail bcc: '', cc: '', from: '', replyTo: '', to: 'paulnishtha19@gmail.com', subject: "Pipeline failed in Jenkins: ${env.JOB_NAME} - #${env.BUILD_NUMBER}", body: "Check console output at ${jenkinsBuildUrl} to view the results."
            }
        }
    }
}
