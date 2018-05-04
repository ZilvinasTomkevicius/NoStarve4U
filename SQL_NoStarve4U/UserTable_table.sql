drop table [User]
go

create table [User]
(UserID int primary key identity (1,1) not null, 
Name varchar(20) not null, 
[Password] varchar(128),
Email varchar(128) not null, 
[DateTime] DateTime default getdate())
go

alter table [User]
add constraint U_Name unique (Name)
go

alter table [User]
add constraint U_Email unique (Email)
go

insert into [User](Name, [Password], Email)
values('moznikas', 'lopackas', 'lopas@gmail.com')

select * from [User]