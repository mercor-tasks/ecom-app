In this whole project, following things can be assumed.

- No tax for this business.
- No extra charges other than item price.
- The price of an item stays static, and and item price will never change.

So an order amount = sum of the items amount based on the qty.

---

#### Other Details

All the provided ids which are `String` are UUIDs. So empty values are not allowed. The validations must include empty check. UUID check is not required.

- A cart is created for a customer when the customer account is created, so there'll always be a cart associated with the cart. In case of failure scenarios, it's possible that cartId is empty for the corresponding customer.
