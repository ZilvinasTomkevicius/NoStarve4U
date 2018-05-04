create type Temp_Product as table (ID int primary key, Name varchar(100))
go

drop procedure Recipe_matching
go

create procedure Recipe_matching (@tp as Temp_Product readonly)
	as

	select q.ID, q.name, q.recDescription, q.cookingTime, q.ProductCount, q2.ExistingProducts
	from (select recipe.id, recipe.name, recipe.recDescription, Recipe.cookingTime, count(*) as ProductCount
		from Recipe join Recipe_Products
		on Recipe.ID = Recipe_Products.RecID
		group by recipe.id, Recipe.Name, recipe.recDescription, Recipe.cookingTime	
		having count(*)<= 5) q join
		(select Recipe_Products.RecID, count(*) as ExistingProducts
				from Recipe_Products join @tp t 
					on t.ID = Recipe_Products.ProdID
				group by Recipe_Products.RecID) q2
		on q.ID = q2.RecID
	where q.ProductCount = q2.ExistingProducts
	go

	execute Recipe_matching

