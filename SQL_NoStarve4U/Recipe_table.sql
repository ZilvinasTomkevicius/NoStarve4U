drop table Recipe
go

create table Recipe(ID int identity (1,1) not null, name varchar(100) not null, recDescription varchar(2048) not null, cookingTime int)
go

alter table Recipe
add constraint R_ID primary key (ID)
go

alter table Recipe
add constraint R_Name unique (Name)
go

alter table Recipe
add constraint R_Description unique (recDescription)
go

insert into Recipe(name, recDescription, cookingTime)
values ('Sandwich with cheese', 'Spread some butter onto the slice of bread and put on some cheese.', 3 ), 
	   ('Basic salad (with pickles)', 'Cut various vegetable in small pieces and put together in a bowl', 10),
       ('Basic salad', 'Cut various vegetable in small pieces and put together in a bowl.', 10),
       ('Basic stew', 'Cuddle potatoes, carrots and chicken in a pot until 3 min is left and then put in some mayonnaise.', 40 ),
       ('Student breakfast.', 'Eat coocked sausages with ketchup or bread.', 10 ),       
       ('Sandwich with salmon','Spread some butter onto the slice of bread and put on some salmon.', 3 ),
       ('Cacao drink with milk', 'Basic cacao drink with milk.', 5 ),
       ('Truffels','Make the dough from butter, egg, flour and sugar and knead it. Make small cookies and bake it in oven set on 200 degrees. Cover baked cookies in cacao.', 60 ),
       ('Fried potaotes', 'Cut potatoes into cubes and fry them in pan.', 20)
go

select * from Recipe
go