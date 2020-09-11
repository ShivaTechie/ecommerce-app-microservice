import React from "react";
import { useEffect } from "react";

function ProductPage({ productObj }) {
  useEffect(() => {}, [productObj]);

  return (
    <div>
      <h1>Hello {productObj}</h1>
      {console.log(productObj)}
    </div>
  );
}

export default ProductPage;
