import React, { useState, useEffect } from "react";
import "../counter/Quantity.css";
import { Button } from "react-bootstrap";
import axios from "../apis/CartApi";

function Quantity({ productData, value }) {
  // const { id, name, description, price, currency, image, url } = productData;

  const [isQuantityLessThanZero, setIsQuantityLessThanZero] = useState(false);

  const [cartKey, setCartKey] = useState("cartid123");

  useEffect(() => {
    console.log(cartKey);
  }, [isQuantityLessThanZero, cartKey]);

  const sendQuantityToProduct = async () => {
    await axios
      .post(`/${cartKey}`, productData)
      .then((response) => {
        console.log(response.data);
        value(response.data.items.length);
        setCartKey(cartKey);
      })
      .catch((err) => console.log(err));
  };

  return (
    <div>
      <Button block="true" variant="primary" onClick={sendQuantityToProduct}>
        Add to cart
      </Button>
    </div>
  );
}

export default Quantity;
