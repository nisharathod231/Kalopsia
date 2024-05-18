import React, { useContext, useState, useEffect } from 'react';
import './Navbar.css';
import logo from '../Assets/logo.png';
import cart_icon from '../Assets/cart_icon.png';
import { Link , useNavigate} from 'react-router-dom';
import { ShopContext } from '../../Context/ShopContext';

const Navbar = () => {
  const [menu, setMenu] = useState("holiday");
  const { getTotalCartItems } = useContext(ShopContext);
  const { clearCartItems } = useContext(ShopContext);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userName, setUserName] = useState('');
  const [showLogoutModal, setShowLogoutModal] = useState(false);

  const navigate = useNavigate();

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem('user'));
    if (user) {
      setIsLoggedIn(true);
      setUserName(user.name);
    }
  }, [userName]);

  const handleLogout = () => {
    localStorage.removeItem('user');
    setIsLoggedIn(false);
    setUserName('');
    setShowLogoutModal(false);
    navigate('/login');
    clearCartItems();
  };

  return (
    <div className='navbar'>
      <div className='nav-logo'>
        <Link to='/'><img src={logo} alt="Unable to load img" /></Link>
        <ul className='nav-menu'>
          <li onClick={() => { (setMenu("shop")) }}><Link style={{ textDecoration: 'none', color: '#000' }} to='/'>Holiday Collection</Link>{menu === "shop" ? <hr /> : <></>}</li>
          <li onClick={() => { (setMenu("her")) }}><Link style={{ textDecoration: 'none', color: '#000' }} to='/her'>Her's Only</Link>{menu === "her" ? <hr /> : <></>}</li>
          <li onClick={() => { (setMenu("Accessories")) }}><Link style={{ textDecoration: 'none', color: '#000' }} to='/Accessories'>Accessories</Link>{menu === "Accessories" ? <hr /> : <></>}</li>
       <li>
        <div className='nav-login-cart'>
          {isLoggedIn ? (
            <>
              <span>{userName}</span>

              <button onClick={() => setShowLogoutModal(true)}>Logout</button>
            </>
          ) : (
            <Link to='/signup'><button>Login/Signup</button></Link>
          )}
          <Link to='/cart'><img src={cart_icon} alt="Unable to load img" /></Link>
          <div className='nav-cart-count'>{getTotalCartItems()}</div>
        </div>
        </li>
        </ul>
      </div>
      {showLogoutModal && (
        <div className="logout-modal">
          <div className="logout-modal-content">
            <p>Are you sure you want to logout?</p>
            <button onClick={handleLogout}>Yes</button>
            <button onClick={() => setShowLogoutModal(false)}>No</button>
          </div>
        </div>
      )}
    </div>
  );
}

export default Navbar;
