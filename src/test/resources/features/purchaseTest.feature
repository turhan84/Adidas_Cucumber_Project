Feature: Order Placement Functionalities

  Agile Story: As a customer, I should be able to place order
  from demoblaze website so that I can shop online

  @wip
  Scenario: Placing a successful order
    When User navigates to the "Laptops" category
    And User adds "Sony vaio i5" to the cart and accepts the pop-up confirmation
    And User navigates to the "Laptops" category
    And User adds "Dell i7 8gb" to the cart and accepts the pop-up confirmation
    And User navigates to the "Cart" page
    And User deletes "Dell i7 8gb" from the cart
    And User clicks on "Place order"
    Then User fills in all the web form fields
      | Name        | Turhan              |
      | Country     | UK                  |
      | City        | London              |
      | Credit Card | 7678 6787 6423 1233 |
      | Month       | 05                  |
      | Year        | 2024                |
    And User clicks on "Purchase"
    Then User should capture and log the purchase ID and Amount
    And User should assert that the purchase amount equals expected
    And User clicks on "Ok"
