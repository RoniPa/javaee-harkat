
use jee;
DROP TABLE tuotetaulu;

CREATE TABLE tuotetaulu (
 TuoteId INTEGER AUTO_INCREMENT NOT NULL,
 nimi VARCHAR(50),
 koodi VARCHAR(20),
 hinta FLOAT,
 PRIMARY KEY(TuoteId) 
);

insert into tuotetaulu values (null, "Microsoft Office 2010 Standard Open License", "021-05429", 736.90);
insert into tuotetaulu values (null, "HP Mini 5103 10.1", "XM592AA#UUW", 398.90);
insert into tuotetaulu values (null, "Adobe Creative Suite 5 Design Premium", "65064508AF01A00", 2273.90);
insert into tuotetaulu values (null, "Adobe Acrobat Pro 7.0", "22020173", 623.90);
insert into tuotetaulu values (null, "Sony Bravia KDL-40EX402 40 Full HD", "KDL40EX402", 699.90 );
insert into tuotetaulu values (null, "Archos 101 Internet Tablet 16 GB","501594", 408.90);
insert into tuotetaulu values (null, "Nokia Booklet 3G", "02717X8", 589.90);
insert into tuotetaulu values (null, "Apple Mac OS X v10.6.3 Snow Leopard", "MC573", 27.90);
insert into tuotetaulu values (null, "F-Secure Internet Security 2010 Fin", "FCI0OE1N001FI", 29.90);
insert into tuotetaulu values (null, "Nokia N900", "002M115", 449.90);

select * from tuotetaulu;