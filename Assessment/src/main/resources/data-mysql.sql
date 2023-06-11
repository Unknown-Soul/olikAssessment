-- Insert Customer data
INSERT INTO user (name, email, phone)
VALUES
    ('John Doe', 'john@example.com', '1234567890'),
    ('Jane Smith', 'jane@example.com', '9876543210');

-- Insert Product data
INSERT INTO product (name, image, cost_per_hour, is_booked)
VALUES
    ('Roadster Bike', 'mountain_bike.jpg', 10,false),
    ('Mountain Bike', 'mountain_bike.jpg', 10,false),
    ('Tennis Racket','tennis_racket.jpg', 5,false),
    ('Table Tennis Racket', 'tennis_racket.jpg', 5, false);


-- Insert Category data
INSERT INTO category (name, description)
VALUES
    ('Cycles', 'Bicycles and related products'),
    ('Rackets', 'Sports rackets and equipment');

-- Associate Products with Categories
INSERT INTO product_category (product_id, category_id)
VALUES
    (1, 1),
    (2, 1),
    (3, 2),
    (4, 2);
