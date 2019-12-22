CREATE TABLE orders (
     id UUID NOT NULL PRIMARY KEY,
     orderStatus INTEGER,
     totalCost FLOAT

);
CREATE TABLE orderItems (
     itemId UUID NOT NULL PRIMARY KEY,
     orderId UUID NOT NULL REFERENCES orders (id),
     amount INTEGER
);