# Overview

This a cryptocurrency portfolio tracker coden in Java.

This a program allows you to input your cryptocurrency holdings and returns the current value of your holdings by grabbing the current prices from Coinmarketcap.com using the API they provide.

It uses a hashmap in order to store the ticker of the crypto currency and the amount of the crypto currency you hold. It makes it easy to store and get the information.

My goal was to build something around something I enjoy and thats how I came up with tracking crypto currency.


# Development Environment

These are the things used.

- Java Development Kit

- Coin marketcap API

- Libraries: 
    - java.util.HashMap which allowed for the data structure to store tickers and amounts
    - java.util.Scanner which helped with user input
    - java.net.HttpURLConnection for the CMC API
    - java.net.URL for the CMC API
    - java.io.BufferedReader for the CMC API
    - java.io.InputStreamReader for the CMC API


# Useful Websites

{Make a list of websites that you found helpful in this project}

- [W3schools.com](https://www.w3schools.com/java/java_syntax.asp)
- [Learnjavaonline.org](https://www.learnjavaonline.org/)
- [Youtube.com](https://www.youtube.com/results?search_query=java+api+tutorial)
- [Rapidapi.com](https://rapidapi.com/blog/how-to-use-an-api-with-java/e)
- [Coinmarketcap.com](https://coinmarketcap.com/api/documentation/v1/)

# Future Work

- Add stocks
- Cache prices / history of portfolio 
- Simulated buying / selling