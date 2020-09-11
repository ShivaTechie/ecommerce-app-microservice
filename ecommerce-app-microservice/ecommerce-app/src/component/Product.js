import React, { useEffect, useState } from "react";
import axios from "../apis/ProductApi";
import { BrowserRouter as Router, Link, Route } from "react-router-dom";
import {
  Card,
  CardText,
  CardBody,
  CardDeck,
  CardFooter,
  Row,
  Container,
  CardHeader,
} from "reactstrap";
import Quantity from "../counter/quantity";
import "../counter/Quantity.css";
import { CardImg } from "react-bootstrap";
import ProductPage from "./ProductPage";
import "../css/ProductList.css";

function Product({ value, getProductPageId }) {
  const [productsData, setProductsData] = useState([]);
  // const [pageId,]
  // const setPageId = () => {
  //   getProductPageId(pageId, productPageData);
  // };
  useEffect(() => {
    const fetchProductData = async () => {
      await axios
        .get("/recommendations")
        .then((res) => {
          console.log(res);
          setProductsData(res.data);
        })
        .catch((err) => console.log(err));
    };
    fetchProductData();
  }, []);

  return (
    <React.Fragment>
      <Container>
        <div className="product-list">
          {productsData.map((productObj) => (
            <ul key={productObj.id}>
              {
                <div className="product">
                  <Card
                    style={{
                      width: "100%",
                      height: "100%",
                      marginTop: "50px",
                      padding: "10px",
                    }}
                  >
                    <CardHeader>
                      <CardDeck style={{ margin: "auto" }}>
                        {productObj.name}{" "}
                      </CardDeck>
                    </CardHeader>
                    <CardBody>
                      <CardImg
                        height="100%"
                        width="100%"
                        src={productObj.image}
                      />
                    </CardBody>
                    <CardText>{productObj.description}</CardText>
                    <CardFooter>
                      <CardText>₹{productObj.price}</CardText>
                      <Quantity value={value} productData={productObj} />
                    </CardFooter>
                  </Card>
                </div>
              }
            </ul>
          ))}
        </div>
      </Container>
    </React.Fragment>
  );
}

export default Product;
