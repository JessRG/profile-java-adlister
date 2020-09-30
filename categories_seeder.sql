## RUN FILE FROM TERMINAL: mysql -p -t < categories_seeder.sql

USE adlister_db;
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE categories;
SET FOREIGN_KEY_CHECKS = 1;

ALTER TABLE categories
ADD COLUMN ParentId int;

INSERT INTO categories
(category_name, ParentId)
VALUES
('for sale', NULL),
('housing', NULL),
('jobs', NULL),
('furniture', 1),
('vehicles', 1),
('electronics', 1),
('free stuff', 1),
('real estate', 2),
('for rent', 2),
('office', 2),
('vacation rentals', 2),
('food and beverage', 3),
('it support', 3),
('real estate', 3),
('security', 3);
SELECT * FROM categories;



