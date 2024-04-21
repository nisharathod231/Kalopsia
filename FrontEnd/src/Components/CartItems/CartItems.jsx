// import React, { useContext } from 'react'
// import './CartItems.css'
// import { ShopContext } from '../../Context/ShopContext'
// import remove_icon from '../Assets/cart_cross_icon.png'
// const CartItems = () => {
//     const {getTotalCartAmount,all_product, cartItems, removeFromCart}=useContext(ShopContext);
import React, { useContext, useState } from 'react';
import './CartItems.css';
import { ShopContext } from '../../Context/ShopContext';
import remove_icon from '../Assets/cart_cross_icon.png';

const CartItems = () => {
    const { getTotalCartAmount, all_product, cartItems, removeFromCart } = useContext(ShopContext);
    const [isOrderPlacedPopupVisible, setIsOrderPlacedPopupVisible] = useState(false); // State for popup visibility
  
    const handleCheckoutClick = () => {
        // Check if cart is empty before showing popup or message
        if (Object.keys(cartItems).length === 0) {
          alert('Your cart is empty. Please add items to checkout.');
          return; // Prevent unnecessary state update
        }
      
        // Simulate successful order processing (no API call needed)
        setIsOrderPlacedPopupVisible(true);
      };

  return (
    <div className='cartitems'>
        <div className="cartitems-format-main">
            <p>Products</p>
            <p>Title</p>
            <p>Price</p>
            <p>Quantity</p>
            <p>Total</p>
            <p>Remove</p>
        </div>
        <hr />
        {all_product.map((e) => {
            if (cartItems[e.id] > 0) {
                return (
                    <div key={e.id}>
                        <div className="cartitems-format cartitems-format-main">
                            <img src={e.image} alt="" className="carticon-product-icon" />
                            <p>{e.name}</p>
                            <p>${e.new_price}</p>
                            <button className='cartitems-quantity'>{cartItems[e.id]}</button>
                            <p>${e.new_price * cartItems[e.id]}</p>
                            <img className='cartitems-remove-icon' src={remove_icon} onClick={() => { removeFromCart(e.id) }} alt="" />
                        </div>
                        <hr />
                    </div>
                );
            }
            return null;
        })}

            <div className="cartitems-down">
                <div className="cartitems-total">
                    <h1>Cart Total</h1>
                    <div className="cartitems-total-item">
                        <p>Subtotal</p>
                        <p>${getTotalCartAmount()}</p>
                    </div>
                    <hr />
                    <div className="cartitems-total-item">
                        <p>Shipping Fee</p>
                        <p>Free</p>
                    </div>
                    <hr />
                    <div className="cartitems-total-item">
                        <h3>Total</h3>
                        <h3>${getTotalCartAmount()}</h3>
                    </div>
                </div>
                <button onClick={handleCheckoutClick}>Checkout</button>
      </div>

      {isOrderPlacedPopupVisible && ( // Conditionally render popup
        <div className="order-placed-popup">
          <h2>Order Placed Successfully!</h2>
          <p>Thank you for your purchase.</p>
          <button onClick={() => setIsOrderPlacedPopupVisible(false)}>Close</button>
        </div>
      )}
    </div>
  )
}

export default CartItems