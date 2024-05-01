import React, { useEffect, useState } from 'react';
import './CSS/ShopCategory.css';
import Item from '../Components/Item/Item';
import axios from 'axios';

const ShopCategory = (props) => {
  const [data, setData] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get('http://localhost:9010/api/products');
        setData(response.data);
      } catch (error) {
        console.error('Error fetching data:', error);
        setError(error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, []);
if (isLoading) {
    return <p>Loading data...</p>;
  }

  if (error) {
    return <p>Error: {error.message}</p>;
  }

  // Filter data and display items
  const filteredItems = data.filter(item => item.category === props.category);

  return (
    <div className='shop-category' data-testid='product-list'>
      <div className="shopcategory-products">
        {filteredItems.map((item) => (
          <Item key={item.id} id={item.id} name={item.name} image={item.image} new_price={item.newPrice} old_price={item.oldPrice} />
        ))}
      </div>
    </div>
  );
};

export default ShopCategory;
