import axios from "axios";

const instance = axios.create({
  baseURL: "http://localhost:9001/product/products",
});

export default instance;
