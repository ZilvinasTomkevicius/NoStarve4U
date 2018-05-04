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
values ('Sumuðtinis su sûriu', 'Ant duonos uþtepti sviesto ir uþdëti sûrio.', 3 ), 
	   ('Darþoviø salotos (su raugintais agurkais)', 'Darþoves supjaustyti, sudëti á dubená', 10),
       ('Darþoviø salotos', 'Darþoves supjaustyti, sudëti á dubená.', 10),
       ('Troðkinys su viðtiena', 'Bulves, morkas, viðtienà, supjaustyti, troðkinti puode. Iki paruoðimo likus 3 min, uþdrëbti majonezo', 40 ),
       ('Studento pusryèiai','Deðreles iðvirti, valgyti su duona arba keèupu.', 10 ),       
       ('Sumuðtinis su laðiða','Ant duonos uþtepti sviesto, uþdëti laðiðos.', 3 ),
       ('Kakavos gërimas su pienu', 'Kakavà uþpilti verdanèiu vandeniu, pienu.', 5 ),
       ('Triufeliai','Padaryti teðlà ið sviesto, kiauðiniø bei cukraus, mynkant pilti miltus. Padaryti maþus sausainiukus, kepti orkaitëje 200 C. Iðkepus iðvolioti kakavoje.', 60 ),
       ('Keptos bulvës', 'Supjaustyti kubeliais ir kepti keptuvëje.', 20),
       ('Vaisiø salotos', 'Vaisius supjaustyti á dubená.', 15),
       ('Vegetariðkas troðkinys', 'Supjaustyti morkas á raugintus kopûstus ir patroðkinti. Bulves iðvirti. Tuomet á skardà iðpilti patroðkintus raug. kopûstus su morkomis ir ant virðaus iðdëlioti greþinëliais supjaustytas bulves. Pakepti orkaitëje.', 60),
       ('Bulviø koðë', 'Bulves iðvirti ir suminkyti. Ápilti ðiek tiek pieno.', 30),
       ('Pomidorø ir agurkø salotos', 'Agurkus bei pomodorus supjaustyti, sudëti á dubená, uþdrëbti grietinës.', 10 ),
       ('Bulviniai blynai', 'Bulves sutarkuoti, ámuðti kiauðiniø, kepti keptuvëje. Valgyti su grietine.', 40 ),
       ('Sumuðtinis su rûkyta deðra', 'Ant duonos uþtepti sviesto, uþdëti rûkytos deðros, pasigardinti agurku.', 3),
       ('Lenkiðka sriuba', 'Iðvirti rûkytà deðrà, áberti pipirø bei druskos, valgyti su grietine bei duona.', 30),
       ('Balta miðrainë', 'Supjaustyti virtas bulves bei morkas, raugintus agurkus, marinuotus þirnelius, virtus kiauðinius. Viskà sudëti á dubená, uþdrëbti majonezo, pagardinti druska/pipirais.', 30),
       ('Pieno kokteilis', 'Bananà supjaustyti maþais greþinëliais, uþpilti pienu, suplakti plaktuvu.', 5),
       ('Cezario salotos', 'Viðtienà supjaustyti á maþus gabalëlius, sudëti á dubená kartu su solotomis bei supjausyta duona, uþberti sûrio.', 15),
       ('Paprasti blynai', 'Á dubená ámuðti kiauðiná, áberti miltø, ápilti pieno, ádëti cukraus, druskos, viskà sumaiðyti. Kepti keptuvëje, valgyti su dþemu.', 20 ),
       ('Angliðki pusryèiai', 'Iðkepti kiauðinienæ, tosteryje paspraginti duonos, pateikti su darþvomëmis bei kepta kiaulienos ðonine.', 15),
       ('Valstieèio pietûs', 'Iðvirtas bulves valgyti su sviestu ir druska.', 20),
       ('Sviestiniai sausainiai', 'Padaryti teðlà ið sviesto, kiauðiniø, miltu bei cukraus. Suformuoti sasainiukus, kepti orkaitëje 200 C.', 30),
       ('Keptas kiauðinis duonoje', 'Duonos reikëje iðpjauti skylæ, kepti, á vidø ámuðti kiauðiná.', 10),
       ('„Aðtunkojai"', 'Pieniðkø deðreliø galus ápjauti iðilgai, kepti keptuvëje, valgyti su keèupu.', 10),
       ('Kepta duona su pikantiðku padaþu', 'Padaþà keptai duonai padaryti ið supjaustytø virtø kiauðiniø, tarkuoto sûrio bei majonezo. Tepti ant keptos duonos.', 20),
       ('Karðtas sumuðtinis (su rûkyta deðra)', 'Rûkytà deðrà supjaustyti greþinëliais, sudëti ant keèupu suteptos duonos, apibarstyti sûriu, kepti orkaitëje.', 15),
       ('Karðtas sumuðtinis (su pieniðka deðrele)', 'Pieniðkà deðrelæ supjaustyti greþinëliais, sudëti ant keèupu suteptos duonos, apibarstyti sûriu, kepti orkaitëje.', 10),
       ('Laðiðos salotos', 'Viskà supjaustyti, sudëti á dubená.', 15),
       ('Obuoliø pyragas', 'Padaryti teðlà ið cukraus, miltø, kiauðiniø bei pieno. Ámaiðyti supjaustytus obuolius, kepti orkaitëje.', 40),
       ('Virtos bulvës su kefyru', 'Iðvirti bulves, valgyti uþsigeriant kefyru (kaip tikri lietuviai!).', 40),
       ('Burokëliø sriuba', 'Á verdantá vandená sudëti burokus, bulves, morkas, pagardinti pipirais, druska.', 30),
       ('Apelsinø sultys', 'Apelsinà perpjauti per pusæ, iðspausti.', 10),
       ('Apelsinø ir obuoliø sultys', 'Apelsinà perpjauti per pusæ, obuolius supjaustyti skiltelëmis, iðspausti.', 10),
       ('Sumuðtinis su duona, grietine bei cukrumi', 'Ant duonos storai uþtepti grietinës, uþberti cukraus.', 10),
       ('Kepti obuoliai', 'Iðgremþti obuolio grauþtukà ir apbarstyti cukrumi. Kepti kol obuolys suminkðtës.', 15),
       ('„Barfi"', 'Á pienà suberti cukrø, virinti maiðant tol kol pasidarys tirðta masë. Tuomet palaikius ðaldytuve kol atvës, padaryti kamuolukus ir turime saldumynus.', 15)
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












