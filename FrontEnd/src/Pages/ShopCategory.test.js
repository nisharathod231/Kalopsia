import React from "react";
import { render, screen, waitFor } from "@testing-library/react";
import ShopCategory from "./ShopCategory"; // Assuming ShopCategory.js is in the same directory
import axios from "axios";
import { act } from "react-dom/test-utils";

jest.mock("axios"); // Mock the axios library

const mockData = [
  {
    name: "Sapphire Whisper Platinum Ensemble",
    category: "Accessories",
    image:
      "https://whitesatin.ca/wp-content/uploads/2021/11/Sara-Gabriel-Hazel-Bracelet-300x500.webp",
    newPrice: 890.0,
    oldPrice: 1200.0,
  },
];

test("ShopCategory renders loading state while fetching data", () => {
  // Mock axios to return a promise that never resolves
  axios.get.mockImplementationOnce(() => new Promise(() => {}));
  render(<ShopCategory />);

  const loadingText = screen.getByText(/Loading data.../i);
  expect(loadingText).toBeInTheDocument();
});

// test('ShopCategory renders error message on API error', () => {
//   const error = new Error('API request failed');
//   axios.get.mockRejectedValueOnce(error);
//   render(<ShopCategory />);

//   const errorText = screen.getByText(/Error/i);
//   expect(errorText).toBeInTheDocument();
// });

test("ShopCategory renders product list after successful data fetch", async () => {
  axios.get.mockResolvedValueOnce({ data: mockData });
  await act(async () => {
    render(<ShopCategory />);
  });

  // const productList = screen.queryByTestId('product-list'); // Assuming you add a data-testid attribute to the product list element
  // expect(productList).toBeInTheDocument();

  // // Test for specific product information
  // const productName = screen.queryByText('Sapphire Whisper Platinum Ensemble');
  // expect(productName).toBeInTheDocument();

  waitFor(() =>
    expect(screen.queryByTestId("product-list")).toBeInTheDocument()
  );

  waitFor(() =>
    expect(
      screen.queryByText("Sapphire Whisper Platinum Ensemble")
    ).toBeInTheDocument()
  );
});
