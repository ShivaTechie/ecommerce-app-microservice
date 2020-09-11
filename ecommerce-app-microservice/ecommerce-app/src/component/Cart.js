import React, { useEffect } from "react";

function Cart({ dataQuan }) {
  useEffect(() => {}, [dataQuan]);

  return (
    <div className="cart">
      <img width="50px" height="50px" src="../assets/images/cart.jpg" />
      <p style={{ color: "white" }}>{dataQuan}</p>
    </div>
  );
}

export default Cart;
