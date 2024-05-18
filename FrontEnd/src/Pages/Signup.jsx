import React, { useState } from 'react';
import axios from 'axios';
import './CSS/LoginSignup.css';

const Signup = () => {
  const [formData, setFormData] = useState({
    name: '',
    phoneNumber: '',
    password: '',
  });
  const [error, setError] = useState('');

  const handleChange = (e) => {
    setError('');
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:0/api/users/signup', formData);
      if (response.status === 201) {
        window.location.href = '/login';
      }
    } catch (error) {
      if (error.response && error.response.status === 409) {
        setError('Phone number already in use.');
      } else {
        setError('An error occurred. Please try again.');
      }
    }
  };

  return (
    <div className='loginsignup'>
      <div className="loginsignup-container">
        <h1>Sign up</h1>
        {error && <p className="error">{error}</p>}
        <form onSubmit={handleSubmit} className="loginsignup-feilds">
          <input
            type="text"
            name="name"
            placeholder='Name'
            value={formData.name}
            onChange={handleChange}
            required
          />
          <input
            type="text"
            name="phoneNumber"
            placeholder='Phone Number'
            value={formData.phoneNumber}
            onChange={handleChange}
            required
          />
          <input
            type="password"
            name="password"
            placeholder='Password'
            value={formData.password}
            onChange={handleChange}
            required
          />
          <button type="submit">Continue</button>
        </form>
        <p className="loginsignup-login">
          Already have an account? <a href="/login">Login here</a>
        </p>
      </div>
    </div>
  );
};

export default Signup;
