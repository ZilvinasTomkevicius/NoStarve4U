drop table Recipe_Products
go

create table Recipe_Products(RecID int not null, ProdID int not null)
go

alter table Recipe_Products
add constraint RP_ID primary key (RecID, ProdID)

insert into Recipe_Products(RecID, ProdID)
values(1, 3),
	  (1, 4),
	  (1, 7),
	  (2, 9),
	  (2, 13),
	  (2, 14),
	  (2, 17),
	  (3, 5),
	  (3, 9),
	  (3, 14),
	  (3, 17),
	  (4, 11),
	  (4, 20),
	  (4, 26),
	  (4, 15),
	  (5, 28),
	  (5, 3),
	  (5, 29),
	  (6, 3),
	  (6, 4),
	  (6, 22),
	  (7, 6),
	  (7, 12),
	  (8, 12),
	  (8, 23),
	  (8, 10),
	  (8, 25),
	  (8, 4),
	  (9, 15),
	  (9, 24)
go

select * from Recipe_Products
go
select * from Recipe
go
select * from Product