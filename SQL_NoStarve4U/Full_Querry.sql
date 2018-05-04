drop database NoStarve4U
go

create database NoStarve4U
go

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
values('Bananas', 'Vaisius'),
	  ('Obuolys', 'Vaisius'),
      ('Duona', 'Pusfrabikatis'),
      ('Sviestas', 'Pieno produktas'),
      ('Agurkas', 'Darzove'),
      ('Pienas', 'Pieno produktas'),
      ('Suris', 'Pieno produktas'),
      ('Rukyta desra', 'Mesa'),
      ('Pomidoras', 'Darzove'),
      ('Kiausiniai', 'Gyvulinis produktas'),
      ('Vistiena', 'Mesa'),
      ('Kakava', 'Gerimas'),
      ('Rauginti agurkai', 'Darzove'),
      ('Marinuoti zirneliai', 'Darzove'),
      ('Bulves', 'Darzove'),
      ('Rauginti kopustai', 'Darzove'),
      ('Salotu lapai', 'Darzove'),
      ('Grietine', 'Pieno produktai'),
      ('Apelsinas', 'Vaisius'),
      ('Morkos', 'Darzove'),
      ('Burokas', 'Darzove'),
      ('Lasisa', 'Zuvis'),
      ('Miltai', 'Augalinis produktas'),
      ('Druska', 'Prieskonis'),
      ('Cukrus', 'Prieskonis'),
      ('Majonezas', 'Padazas'),
      ('Kefyras', 'Pieno produktas'),
      ('Pieniskos desreles', 'Mesa'),
      ('Kecupas', 'Padazas'),
      ('Kiaulienos sonine', 'Mesa'),
      ('Pipirai', 'Prieskonis')
go

select * from Product
go

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
values ('Sumu�tinis su s�riu', 'Ant duonos u�tepti sviesto ir u�d�ti s�rio.', 3 ), 
	   ('Dar�ovi� salotos (su raugintais agurkais)', 'Dar�oves supjaustyti, sud�ti � duben�', 10),
       ('Dar�ovi� salotos', 'Dar�oves supjaustyti, sud�ti � duben�.', 10),
       ('Tro�kinys su vi�tiena', 'Bulves, morkas, vi�tien�, supjaustyti, tro�kinti puode. Iki paruo�imo likus 3 min, u�dr�bti majonezo', 40 ),
       ('Studento pusry�iai','De�reles i�virti, valgyti su duona arba ke�upu.', 10 ),       
       ('Sumu�tinis su la�i�a','Ant duonos u�tepti sviesto, u�d�ti la�i�os.', 3 ),
       ('Kakavos g�rimas su pienu', 'Kakav� u�pilti verdan�iu vandeniu, pienu.', 5 ),
       ('Triufeliai','Padaryti te�l� i� sviesto, kiau�ini� bei cukraus, mynkant pilti miltus. Padaryti ma�us sausainiukus, kepti orkait�je 200 C. I�kepus i�volioti kakavoje.', 60 ),
       ('Keptos bulv�s', 'Supjaustyti kubeliais ir kepti keptuv�je.', 20),
       ('Vaisi� salotos', 'Vaisius supjaustyti � duben�.', 15),
       ('Vegetari�kas tro�kinys', 'Supjaustyti morkas � raugintus kop�stus ir patro�kinti. Bulves i�virti. Tuomet � skard� i�pilti patro�kintus raug. kop�stus su morkomis ir ant vir�aus i�d�lioti gre�in�liais supjaustytas bulves. Pakepti orkait�je.', 60),
       ('Bulvi� ko��', 'Bulves i�virti ir suminkyti. �pilti �iek tiek pieno.', 30),
       ('Pomidor� ir agurk� salotos', 'Agurkus bei pomodorus supjaustyti, sud�ti � duben�, u�dr�bti grietin�s.', 10 ),
       ('Bulviniai blynai', 'Bulves sutarkuoti, �mu�ti kiau�ini�, kepti keptuv�je. Valgyti su grietine.', 40 ),
       ('Sumu�tinis su r�kyta de�ra', 'Ant duonos u�tepti sviesto, u�d�ti r�kytos de�ros, pasigardinti agurku.', 3),
       ('Lenki�ka sriuba', 'I�virti r�kyt� de�r�, �berti pipir� bei druskos, valgyti su grietine bei duona.', 30),
       ('Balta mi�rain�', 'Supjaustyti virtas bulves bei morkas, raugintus agurkus, marinuotus �irnelius, virtus kiau�inius. Visk� sud�ti � duben�, u�dr�bti majonezo, pagardinti druska/pipirais.', 30),
       ('Pieno kokteilis', 'Banan� supjaustyti ma�ais gre�in�liais, u�pilti pienu, suplakti plaktuvu.', 5),
       ('Cezario salotos', 'Vi�tien� supjaustyti � ma�us gabal�lius, sud�ti � duben� kartu su solotomis bei supjausyta duona, u�berti s�rio.', 15),
       ('Paprasti blynai', '� duben� �mu�ti kiau�in�, �berti milt�, �pilti pieno, �d�ti cukraus, druskos, visk� sumai�yti. Kepti keptuv�je, valgyti su d�emu.', 20 ),
       ('Angli�ki pusry�iai', 'I�kepti kiau�inien�, tosteryje paspraginti duonos, pateikti su dar�vom�mis bei kepta kiaulienos �onine.', 15),
       ('Valstie�io piet�s', 'I�virtas bulves valgyti su sviestu ir druska.', 20),
       ('Sviestiniai sausainiai', 'Padaryti te�l� i� sviesto, kiau�ini�, miltu bei cukraus. Suformuoti sasainiukus, kepti orkait�je 200 C.', 30),
       ('Keptas kiau�inis duonoje', 'Duonos reik�je i�pjauti skyl�, kepti, � vid� �mu�ti kiau�in�.', 10),
       ('�A�tunkojai"', 'Pieni�k� de�reli� galus �pjauti i�ilgai, kepti keptuv�je, valgyti su ke�upu.', 10),
       ('Kepta duona su pikanti�ku pada�u', 'Pada�� keptai duonai padaryti i� supjaustyt� virt� kiau�ini�, tarkuoto s�rio bei majonezo. Tepti ant keptos duonos.', 20),
       ('Kar�tas sumu�tinis (su r�kyta de�ra)', 'R�kyt� de�r� supjaustyti gre�in�liais, sud�ti ant ke�upu suteptos duonos, apibarstyti s�riu, kepti orkait�je.', 15),
       ('Kar�tas sumu�tinis (su pieni�ka de�rele)', 'Pieni�k� de�rel� supjaustyti gre�in�liais, sud�ti ant ke�upu suteptos duonos, apibarstyti s�riu, kepti orkait�je.', 10),
       ('La�i�os salotos', 'Visk� supjaustyti, sud�ti � duben�.', 15),
       ('Obuoli� pyragas', 'Padaryti te�l� i� cukraus, milt�, kiau�ini� bei pieno. �mai�yti supjaustytus obuolius, kepti orkait�je.', 40),
       ('Virtos bulv�s su kefyru', 'I�virti bulves, valgyti u�sigeriant kefyru (kaip tikri lietuviai!).', 40),
       ('Burok�li� sriuba', '� verdant� vanden� sud�ti burokus, bulves, morkas, pagardinti pipirais, druska.', 30),
       ('Apelsin� sultys', 'Apelsin� perpjauti per pus�, i�spausti.', 10),
       ('Apelsin� ir obuoli� sultys', 'Apelsin� perpjauti per pus�, obuolius supjaustyti skiltel�mis, i�spausti.', 10),
       ('Sumu�tinis su duona, grietine bei cukrumi', 'Ant duonos storai u�tepti grietin�s, u�berti cukraus.', 10),
       ('Kepti obuoliai', 'I�grem�ti obuolio grau�tuk� ir apbarstyti cukrumi. Kepti kol obuolys sumink�t�s.', 15),
       ('�Barfi"', '� pien� suberti cukr�, virinti mai�ant tol kol pasidarys tir�ta mas�. Tuomet palaikius �aldytuve kol atv�s, padaryti kamuolukus ir turime saldumynus.', 15)
go

select * from Recipe
go

drop table TheMostPopularRecipes
go

create table TheMostPopularRecipes(RecID int, Name varchar(100), Score int check (Score <= 10), ScoreDate date default getdate())
go

insert into TheMostPopularRecipes(RecID, Name, Score)
values(1, 'student. desreles', 9),
	  (2, 'sumust. su desra', 7),
	  (3, 'vienuolio pietus', 4),
	  (4, 'student. desreles', 8)
go

select * from TheMostPopularRecipes
go

drop view MaxScore
go

create view MaxScore as select * from TheMostPopularRecipes where Score = (select max(Score) from TheMostPopularRecipes)
go

select * from MaxScore

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
	  (9, 24),
	  (10, 2),
	  (10, 19),
	  (10, 1),
	  (11, 15),
	  (11, 20),
	  (11, 16),
	  (12, 15),
	  (12, 6),
	  (13, 9),
	  (13, 5),
	  (13, 18),
	  (14, 15),
	  (14, 10),
	  (14, 24),
	  (15, 3),
	  (15, 8),
	  (15, 4),
	  (16, 3),
	  (16, 8),
	  (16, 24),
	  (16, 31),
	  (17, 15),
	  (17, 20),
	  (17, 24),
	  (17, 31),
	  (17, 13),
	  (17, 10),
	  (17, 26),
	  (17, 14),
	  (18, 1),
	  (18, 6),
	  (19, 3),
	  (19, 17),
	  (19, 11),
	  (19, 7),
	  (20, 10),
	  (20, 23),
	  (20, 6),
	  (20, 24),
	  (20, 25),
	  (21, 10),
	  (21, 3),
	  (21, 30),
	  (21, 24),
	  (22, 15),
	  (22, 24),
	  (22, 4),
	  (23, 10),
	  (23, 23),
	  (23, 4),
	  (23, 25),
	  (24, 10),
	  (24, 3),
	  (24, 24),
	  (25, 28),
	  (25, 29),
	  (26, 10),
	  (26, 3),
	  (26, 7),
	  (26, 24),
	  (27, 29),
	  (27, 3),
	  (27, 8),
	  (27, 7),
	  (28, 29),
	  (28, 3),
	  (28, 28),
	  (28, 7),
	  (29, 22),
	  (29, 17),
	  (29, 5),
	  (29, 24),
	  (30, 2),
	  (30, 23),
	  (30, 25),
	  (30, 6),
	  (30, 10),
	  (31, 15),
	  (31, 27),
	  (32, 21),
	  (32, 15),
	  (32, 20),
	  (32, 24),
	  (32, 31),
	  (32, 18),
	  (33, 19),
	  (34, 19),
	  (34, 2),
	  (35, 3),
	  (35, 18),
	  (35, 25),
	  (36, 2),
	  (36, 25),
	  (37, 6),
	  (37, 25)
go

select * from Recipe_Products
go

create type Temp_Product as table (ID int primary key, Name varchar(100))
go
create type MatchedRecipes as table (ID int primary key, Name varchar(20), CookingTime int, RecDescription varchar(1024))
go

drop procedure Recipe_matching
go

create procedure Recipe_matching
	as
	declare @tp as Temp_Product
	insert into @tp (ID, Name)
	values(12, 'Kakava'),
	  (4, 'Sviestas'),
	  (6, 'Pienas'),
	  (8, 'Rukyta desra'),
	  (3, 'Duona') 

	select q.name, q.ProductCount, q2.ExistingProducts
	from (select recipe.id, Name, count(*) as ProductCount
		from Recipe join Recipe_Products
		on Recipe.ID = Recipe_Products.RecID
		group by recipe.id, Recipe.Name	
		having count(*)<= 5) q join
		(select Recipe_Products.RecID, count(*) as ExistingProducts
				from Recipe_Products join @tp t 
					on t.ID = Recipe_Products.ProdID
				group by Recipe_Products.RecID) q2
		on q.ID = q2.RecID
	where q.ProductCount = q2.ExistingProducts
	go












