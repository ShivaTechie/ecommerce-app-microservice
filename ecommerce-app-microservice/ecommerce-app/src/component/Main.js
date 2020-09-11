import React, { useState, useEffect } from "react";
import "../App.css";
import Product from "../component/Product";
import { Navbar, Nav } from "react-bootstrap";
import Cart from "../component/Cart";
import { BrowserRouter as Router, Link, Switch, Route } from "react-router-dom";
import CartPage from "./CartPage";
import axios from "../apis/CartApi";
import ProductPage from "./ProductPage";
import NotFoundPageHere from "./NotFoundPageHere";
import "../css/Main.css";

function Main() {
  const initialValue = 0;
  const [count, setCount] = useState(initialValue);
  const [change, setChange] = useState(false);
  const [pageId, setPageId] = useState(1);
  const [product, setProduct] = useState({});

  useEffect(() => {
    const fetchTotalInCart = async () => {
      await axios
        .get(`/cartid123`)
        .then((response) => {
          if (response.data.items !== undefined)
            setCount(response.data.items.length);
          else setCount(0);
        })
        .catch((err) => console.log(err));
    };
    fetchTotalInCart();
    console.log("Hii from main");
  }, [count, change, pageId, product]);

  const addToCart = (data) => {
    console.log(data + " In Main app");
    setCount(data);
    console.log(count + " hi");
  };

  const manageCartDetails = (data) => {
    if (data === 0) {
      setChange(!change);
      setCount(0);
      console.log("in anywhere");
    }
  };

  // const getProductPageId = (pageId, product) => {
  //   setPageId(pageId);
  //   setProduct(product);
  // };
  return (
    <div className="main">
      <Router>
        <Navbar
          collapseOnSelect
          expand="lg"
          bg="dark"
          variant="dark"
          className="navbar"
        >
          <Link to="/product">
            <Navbar.Brand>E-commerce </Navbar.Brand>
          </Link>
          <Navbar.Toggle aria-controls="responsive-navbar-nav" />
          <Navbar.Collapse id="responsive-navbar-nav">
            <Nav className="ml-auto">
              <Link to="/cart">
                <Navbar.Brand>
                  <Cart dataQuan={count} />
                </Navbar.Brand>{" "}
              </Link>
            </Nav>
          </Navbar.Collapse>
        </Navbar>
        <Switch>
          <Route exact path="/">
            <Product value={addToCart} />
          </Route>
          <Route exact path="/product">
            <Product value={addToCart} />
            {/* getProductPageId={getProductPageId} */}
          </Route>
          <Route exact path="/cart">
            <CartPage manageCartDetails={manageCartDetails} />
          </Route>
          {/* <Route exact to={`/product/${pageId}`}>
            <ProductPage productObj={product} />
          </Route> */}
          <Route path="*">
            <NotFoundPageHere />
          </Route>
        </Switch>
      </Router>
    </div>
  );
}

export default Main;
