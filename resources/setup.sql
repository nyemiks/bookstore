
insert into authors (firstname, lastname) values ('Paul','Deitel');
insert into authors (firstname, lastname) values ('Harvey','Deitel');
insert into authors (firstname, lastname) values ('Abbey','Deitel');
insert into authors (firstname, lastname) values ('Michael','Morgano');
insert into authors (firstname, lastname) values ('Eric','Kern');


insert into titles (ISBN, TITLE, EDITIONNUMBER, COPYRIGHT) values ('0132152134','Visual Basic 2010 How to Program', 5, '2011');
insert into titles (ISBN, TITLE, EDITIONNUMBER, COPYRIGHT) values ('0132151421','Visual C# 2010 How to Program', 4, '2011');
insert into titles (ISBN, TITLE, EDITIONNUMBER, COPYRIGHT) values ('0132575663','Java How to Program', 9, '2012');
insert into titles (ISBN, TITLE, EDITIONNUMBER, COPYRIGHT) values ('0132662361','C++ How to Program', 8, '2012');
insert into titles (ISBN, TITLE, EDITIONNUMBER, COPYRIGHT) values ('0132404168','C How to Program', 6, '2010');
insert into titles (ISBN, TITLE, EDITIONNUMBER, COPYRIGHT) values ('013705842X','iPhone for Programmers: An App-Driven Approach', 1, '2010');
insert into titles (ISBN, TITLE, EDITIONNUMBER, COPYRIGHT) values ('0132121360','Android for Programmers: An App-Driven Approach', 1, '2012');


insert into authorisbn (authorid, isbn) values (1, '0132152134');
insert into authorisbn (authorid, isbn) values (2, '0132575663');
insert into authorisbn (authorid, isbn) values (2, '0132152134');
insert into authorisbn (authorid, isbn) values (1, '0132662361');
insert into authorisbn (authorid, isbn) values (1, '0132151421');
insert into authorisbn (authorid, isbn) values (2, '0132662361');
insert into authorisbn (authorid, isbn) values (2, '0132151421');
insert into authorisbn (authorid, isbn) values (1, '0132404168');
insert into authorisbn (authorid, isbn) values (1, '0132575663');
insert into authorisbn (authorid, isbn) values (1, '0132121360');
insert into authorisbn (authorid, isbn) values (1, '013705842X');
insert into authorisbn (authorid, isbn) values (1, '0132121360');
insert into authorisbn (authorid, isbn) values (2, '013705842X');
insert into authorisbn (authorid, isbn) values (2, '0132121360');
insert into authorisbn (authorid, isbn) values (3, '013705842X');
insert into authorisbn (authorid, isbn) values (3, '0132121360');
insert into authorisbn (authorid, isbn) values (4, '013705842X');
insert into authorisbn (authorid, isbn) values (4, '0132121360');
insert into authorisbn (authorid, isbn) values (5, '013705842X');