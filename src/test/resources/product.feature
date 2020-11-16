Feature: the product can be salved
  Scenario: client makes call to POST /product
    When the client calls /product
    Then the client receives status code of 201
    