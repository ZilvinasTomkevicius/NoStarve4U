drop table Product
go

create table Product(ID int identity(1,1) not null, Name varchar(100) not null, Kind varchar(20))
go

alter table Product
add constraint P_ID primary key (ID)
go

alter table Product
add constraint P_Name unique (Name)
go

insert into Product(Name, Kind)
values('Banana', 'Fruit'),
	  ('Apple', 'Fruit'),
      ('Black bread', 'Bakery'),
      ('Butter', 'Milk product'),
      ('Cucumber', 'Vegetable'),
      ('Milk', 'Milk product'),
      ('Cheese', 'Milk product'),
      ('Smoked sausage', 'Meat'),
      ('Tomato', 'Vegetable'),
      ('Egg', 'Animal product'),
      ('Chicken', 'Meat'),
      ('Cacao', 'Drink'),
      ('Pickle', 'Vegetable'),
      ('Pickled peas', 'Vegetable'),
      ('Potato', 'Vegetable'),
      ('Tanned cabbage', 'Vegetable'),
      ('Letucce', 'Vegetable'),
      ('Sour cream', 'Milk product'),
      ('Orange', 'Fruit'),
      ('Carrot', 'Vegetable'),
      ('Beetroot', 'Vegetable'),
      ('Salmon', 'Fish'),
      ('Flour', 'Vegetable product'),
      ('Salt', 'Spice'),
      ('Sugar', 'Spice'),
      ('Mayonnaise', 'Sauce'),
      ('Kefir', 'Milk product'),
      ('Pieniskos desreles', 'Meat'),
      ('Ketchup', 'Sauce'),
      ('Bacon', 'Meat'),
      ('Pepper', 'Spice'),
	  ('Avocado', 'Vegetable'),
	  ('Chicken wings', 'Meat'),
	  ('Beef', 'Meat'),
	  ('Pasta', 'Half-made'),
	  ('Spaghetti', 'Half-made'),
	  ('Rice', 'Crop'),
	  ('White bread', 'Bakery'),
	  ('Pork', 'Meat'),
	  ('Buckwheat', 'Crop'),
	  ('Aubergine', 'Vegetable'),
	  ('Broccoli', 'Vegetable'),
	  ('Grapes', 'Fruit'),
	  ('Watermelon', 'Fruit'),
	  ('Mango', 'Fruit'),
	  ('Pineapple', 'Fruit'),
	  ('Pear', 'Fruit'),
	  ('Peach', 'Fruit'),
	  ('Strawberry', 'Berry'),
	  ('Raspberry', 'Berry'),
	  ('Blueberry', 'Berry'),
	  ('Cherry', 'Berry'),
	  ('Oil', 'Vegetable product'),
	  ('Cookies', 'Bakery'),
	  ('Milk chocolate', 'Chocolate'),
	  ('Black chocolate', 'Chocolate'),
	  ('White chocolate', 'Chocolate'),
	  ('Soda', 'Soda'),
	  ('Onion', 'Vegetable'),
	  ('Garlic', 'Vegetable')
go

select * from Product
go

