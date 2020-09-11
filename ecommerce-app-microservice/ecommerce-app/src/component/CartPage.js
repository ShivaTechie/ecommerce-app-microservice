import React, { useState, useEffect } from "react";
import { Button, Jumbotron } from "reactstrap";
import { Container, Col, Row } from "react-bootstrap";
import axios from "../apis/CartApi";
import "../css/CartPage.css";

function CartPage(props) {
  const [isPlace, setIsPlace] = useState(false);
  const afterPlaceOrderCart = 0;
  const [cartIsEmpty, setCartIsEmpty] = useState(false);
  const [id, setId] = useState("");
  const [item, setItem] = useState([]);

  const handelOrderPlace = () => {
    setIsPlace(true);
    deleteCart();
    setId("");
    setCartIsEmpty(true);
    props.manageCartDetails(0);
  };

  const deleteCart = async () => {
    axios
      .delete("/cartid123")
      .then((response) => console.log(response.data))
      .catch((err) => console.log(err));
  };

  useEffect(() => {
    const fetchCartDetails = async () => {
      await axios
        .get("/cartid123")
        .then((response) => {
          if (response.data.id == undefined) setId("");
          else setId(response.data.id);
          setItem(response.data.items);
        })
        .catch((err) => console.log(err));
    };
    fetchCartDetails();
  }, [id, cartIsEmpty]);

  let number = 0;

  return (
    <div>
      <div className="jumbo">
        {console.log(id + " id is")}
        {!(id == "") && !cartIsEmpty ? (
          <h1>Cart id is {id}</h1>
        ) : (
          <h1>Your cart is Empty {id}</h1>
        )}
      </div>
      {!isPlace && (
        <div className="cart__items">
          {item !== undefined &&
            item.map((obj) => (
              <ul key={obj.index}>
                <div className="cart__item">
                  <Col
                    className="text-left"
                    style={{
                      color: "white",
                      display: "flex",
                    }}
                  >
                    {(number = number + 1)} {obj.name}
                  </Col>
                  <Row>
                    <img
                      height="150px"
                      width="230px"
                      src={`../assets/images/download${obj.id}.jpg`}
                    />
                  </Row>
                  <Col
                    style={{
                      color: "white",
                      display: "flex",
                      justifyContent: "center",
                    }}
                    className="text-left"
                  >
                    {" " + obj.price}
                  </Col>
                </div>
                <hr style={{ background: "black" }} />
              </ul>
            ))}
        </div>
      )}
      <div
        style={{
          color: "white",
          display: "flex",
          alignContent: "center",
          justifyContent: "center",
        }}
      >
        {!isPlace && !(item === undefined) ? (
          <Button type="button" variant="primary" onClick={handelOrderPlace}>
            {" "}
            Place your order.
          </Button>
        ) : (
          <h1
            style={{
              color: "white",
              display: "flex",
              justifyContent: "center",
            }}
          >{`You Order has been placed Succesfully`}</h1>
        )}
      </div>
    </div>
  );
}

export default CartPage;
