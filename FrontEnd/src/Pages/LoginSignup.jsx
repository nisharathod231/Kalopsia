import React, { useState } from 'react';
import './CSS/LoginSignup.css'
const LoginSignup = () => {
  const [showLoginModal, setShowLoginModal] = useState(false);

  const handleLoginClick = () => {
    setShowLoginModal(true); // Show the modal on login click
  };

  const handleCloseModal = () => {
    setShowLoginModal(false); // Hide the modal on close button click
  };

  // Implement login logic here (e.g., form submission or API call)
  const handleLoginSubmit = (event) => {
    event.preventDefault(); // Prevent default form submission behavior
    const email = event.target.email.value;
    const password = event.target.password.value;

    // Your login logic using email and password
    console.log(`Email: ${email}, Password: ${password}`); // Example logging

    handleCloseModal(); // Close the modal after login attempt
  };


  return (
    <div className='loginsignup'>
        <div className="loginsignup-container">
          <h1>Sign up</h1>
          <div className="loginsignup-feilds">
            <input type="text"placeholder='Name' />
            <input type="email"placeholder='Email' />
            <input type="password"placeholder='Password' />
          </div>
          <button>Continue</button>
          <p className="loginsignup-login">
        Already have an account?{' '}
        <span className="loginsignup-login-link" onClick={handleLoginClick}>
          Login here
        </span>
      </p>

      {showLoginModal && ( // Conditionally render the modal
        <div className="login-modal">
          <div className="login-modal-content">
            <h2>Login</h2>
            <form onSubmit={handleLoginSubmit}>
              <div className="login-modal-field">
                <label htmlFor="email">Email</label>
                <input type="email" id="email" name="email" required />
              </div>
              <div className="login-modal-field">
                <label htmlFor="password">Password</label>
                <input type="password" id="password" name="password" required />
              </div>
              <button type="submit">Login</button>
            </form>
            <button className="login-modal-close" onClick={handleCloseModal}>
              Close
            </button>
          </div>
        </div>
      )}
    </div>
    </div>
  );
};

export default LoginSignup;