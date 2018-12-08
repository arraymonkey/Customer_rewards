
# Son Nguyen
# Customer Reward App
CREATE DATABASE REWARDS;
GRANT ALL PRIVILEGES ON REWARDS.* TO ‘student’@’localhost’; FLUSH PRIVILEGES;
use REWARDS;


CREATE TABLE Acct(
user_id int primary key auto_increment primary key,
first_name varchar(225),
last_name varchar(255),
phone varchar(30),
email varchar (255),
password varchar(255),
date_time datetime default current_timestamp,
type boolean default 0);


ALTER TABLE Acct AUTO_INCREMENT=100;


CREATE TABLE Purchase_H(
user_id int,
amount decimal(6,2),
date_time datetime default current_timestamp,
  constraint fk_ponts foreign key(user_id) references Acct(user_id));


CREATE TABLE Rewards(
user_id int,
points bigint,
value decimal(6,2),
redeem_code varchar(8) primary key);


CREATE TABLE Redeem(
redeem_code varchar(8) primary key,
date_time datetime default current_timestamp,
constraint fk_redeem foreign key(redeem_code) references Rewards(redeem_code));


CREATE TABLE Points(
user_id int,
points bigint default 0,
value decimal(6,2) default 0);





CREATE TRIGGER update_total after INSERT ON Purchase_H
FOR EACH ROW
UPDATE Points SET points = (points + points(new.amount)),  value = CONCAT(value+(points(new.amount)/20)) WHERE user_id = NEW.user_id;

DELIMITER //

CREATE TRIGGER total
AFTER INSERT
ON Acct FOR EACH ROW
BEGIN
INSERT INTO Points(user_id) VALUE(NEW.user_id);
END //
DELIMITER ;



DELIMITER //

CREATE FUNCTION points(amount DECIMAL (6,2))
    RETURNS bigint
    READS SQL DATA
DETERMINISTIC
    BEGIN
        DECLARE calc_points BIGINT;
        SET  calc_points = floor(amount);
        RETURN calc_points;
    END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER clear_total
After insert
ON Rewards FOR EACH ROW
BEGIN
UPDATE Points SET points = 0, value =0 WHERE user_id=new.user_id;
END //
DELIMITER ;


DELIMITER //

CREATE FUNCTION reward_code()
    RETURNS char(250)
    READS SQL DATA
	DETERMINISTIC
    BEGIN
        DECLARE reward_code CHAR(250);
        SET reward_code =LPAD(CONV(FLOOR(RAND()*POW(36,6)), 10, 36), 6, 0);
        RETURN reward_code;
    END //

DELIMITER ;
INSERT INTO Acct (first_name,last_name,phone,email,password) VALUES ("Chantale","Ramos","1-300-541-6719","Sed@Phasellusfermentum.net",SHA1(1234)),("Lynn","Oneil","1-724-817-7996","augue.eu.tellus@Nunc.com",SHA1(1234)),("Lane","Huff","1-825-112-3295","dignissim@venenatisvel.net",SHA1(1234)),("Jenette","Wiggins","1-161-462-9431","bibendum@ipsum.edu",SHA1(1234)),("Slade","Kane","1-128-606-9045","commodo.hendrerit.Donec@tristiquepellentesque.co.uk",SHA1(1234)),("Maggie","Woodard","1-190-925-1672","in@mollis.net",SHA1(1234)),("Keane","Aguilar","1-504-964-0567","magnis.dis.parturient@Nullam.com",SHA1(1234)),("Mollie","Mclaughlin","1-941-427-9507","vitae.aliquet@egestaslaciniaSed.ca",SHA1(1234)),("Clarke","Puckett","1-742-440-2716","mauris.sapien.cursus@magnased.edu",SHA1(1234)),("Guinevere","Atkins","1-331-232-8339","tincidunt.orci.quis@primis.net",SHA1(1234));
INSERT INTO Acct (first_name,last_name,phone,email,password) VALUES ("Stella","Guthrie","1-345-530-5466","auctor.vitae.aliquet@metus.ca",SHA1(1234)),("Bevis","Larsen","1-406-965-1241","eget@lacusEtiambibendum.co.uk",SHA1(1234)),("Macaulay","Logan","1-453-353-4141","Praesent.luctus@dolorsit.net",SHA1(1234)),("Nathan","Mills","1-564-918-8133","lacus.vestibulum@arcuVestibulumante.co.uk",SHA1(1234)),("Aquila","Byrd","1-874-974-4349","dui@In.co.uk",SHA1(1234)),("Mufutau","Pena","1-450-678-9165","arcu.Vestibulum.ut@necligula.edu",SHA1(1234)),("Xantha","Guzman","1-756-833-5100","enim@Lorem.net",SHA1(1234)),("Jorden","Dickson","1-459-825-0992","lectus@tempusmauris.ca",SHA1(1234)),("Emery","Harrell","1-379-738-7939","arcu.imperdiet.ullamcorper@ami.edu",SHA1(1234)),("Emi","Farley","1-545-645-6811","purus.accumsan@luctuslobortis.ca",SHA1(1234));
INSERT INTO Acct (first_name,last_name,phone,email,password) VALUES ("Olga","Williams","1-303-642-9837","eu.euismod@loremeumetus.com",SHA1(1234)),("Owen","Duran","1-865-697-0033","Nam.nulla.magna@Vestibulumante.com",SHA1(1234)),("Fuller","Olsen","1-877-309-7960","sem@ligulaNullamenim.edu",SHA1(1234)),("Bertha","Skinner","1-770-293-1562","commodo.at.libero@dignissimpharetra.com",SHA1(1234)),("April","Weiss","1-141-655-1758","nisi.dictum.augue@torquent.org",SHA1(1234)),("Alexis","Richardson","1-592-403-3772","ipsum@Nullafacilisis.com",SHA1(1234)),("Vaughan","Downs","1-642-163-2323","ante@Quisque.org",SHA1(1234)),("Carter","Bradford","1-709-425-5022","et@parturientmontes.com",SHA1(1234)),("Judith","Dillon","1-226-364-2637","scelerisque.neque@aenimSuspendisse.org",SHA1(1234)),("Jack","Pacheco","1-630-468-1702","luctus.vulputate@nascetur.co.uk",SHA1(1234));
INSERT INTO Acct (first_name,last_name,phone,email,password) VALUES ("Sean","Gardner","1-409-943-5027","non.luctus@fringillacursuspurus.org",SHA1(1234)),("Judah","Olson","1-189-175-4776","vitae.sodales.nisi@sitametdiam.net",SHA1(1234)),("Jermaine","Petty","1-693-875-2214","Proin.mi.Aliquam@dolor.com",SHA1(1234)),("Desirae","Lloyd","1-588-194-8513","ipsum.primis.in@magnaSed.net",SHA1(1234)),("Gavin","Pena","1-279-406-3016","mauris.Integer.sem@elitpretium.net",SHA1(1234)),("Orla","Hensley","1-692-537-2111","Integer.vulputate@malesuadafames.com",SHA1(1234)),("Quin","Delacruz","1-719-106-3627","eu.accumsan@laoreetipsumCurabitur.ca",SHA1(1234)),("Pamela","Rasmussen","1-834-369-4313","sapien.gravida@est.ca",SHA1(1234)),("Gillian","Brooks","1-906-547-2194","enim.Sed.nulla@velitQuisque.edu",SHA1(1234)),("Medge","Cruz","1-133-128-7477","scelerisque.scelerisque@Fuscediam.co.uk",SHA1(1234));
INSERT INTO Acct (first_name,last_name,phone,email,password) VALUES ("Keiko","Buckley","1-323-931-0548","Curabitur.ut.odio@eu.org",SHA1(1234)),("Madeson","Stanton","1-423-310-2021","eget.lacus@interdum.net",SHA1(1234)),("Allistair","Trujillo","1-105-751-7960","magna@tinciduntneque.co.uk",SHA1(1234)),("Fletcher","Nicholson","1-724-928-9610","dolor@idrisus.com",SHA1(1234)),("Winter","Jacobson","1-932-217-8710","Cum@hendrerit.org",SHA1(1234)),("Charity","Albert","1-440-220-5342","ligula@tellusjusto.edu",SHA1(1234)),("Michael","Flynn","1-584-486-8119","risus.Nunc.ac@diamSeddiam.co.uk",SHA1(1234)),("Arthur","Ford","1-598-205-3389","eget.ipsum@diamPellentesquehabitant.com",SHA1(1234)),("Kelly","Wilkerson","1-138-673-7747","volutpat.nunc.sit@risusodio.com",SHA1(1234)),("Gwendolyn","Kane","1-904-640-3687","netus.et@ultricesVivamus.com",SHA1(1234));
INSERT INTO Acct (first_name,last_name,phone,email,password) VALUES ("Xander","Simmons","1-574-300-2398","adipiscing.lobortis@morbitristiquesenectus.edu",SHA1(1234)),("Sonia","Powell","1-114-503-9534","vestibulum.Mauris.magna@arcu.org",SHA1(1234)),("Dorian","Wagner","1-527-886-5423","Duis@Proinvelarcu.org",SHA1(1234)),("Lara","Holman","1-845-507-7206","dis.parturient@mus.edu",SHA1(1234)),("Brynn","Gentry","1-525-352-8206","quam@variusorci.ca",SHA1(1234)),("Kirk","Walter","1-367-214-3786","Aenean.egestas.hendrerit@diamlorem.co.uk",SHA1(1234)),("Aaron","Cardenas","1-216-602-0288","adipiscing.lacus.Ut@mauris.net",SHA1(1234)),("Idola","Castro","1-855-917-8181","enim.nec.tempus@etrisus.co.uk",SHA1(1234)),("Lawrence","Pope","1-422-878-3043","cubilia.Curae@sitametmetus.co.uk",SHA1(1234)),("Juliet","Langley","1-622-175-7769","Curabitur@iaculis.ca",SHA1(1234));
INSERT INTO Acct (first_name,last_name,phone,email,password) VALUES ("Scarlett","Gutierrez","1-292-552-8418","tortor@elitsedconsequat.co.uk",SHA1(1234)),("Amelia","Fuentes","1-734-198-6948","est.tempor@egestasurnajusto.ca",SHA1(1234)),("Maia","Singleton","1-589-139-5896","dolor.elit@ligulaconsectetuer.org",SHA1(1234)),("Edward","Harris","1-742-369-2469","erat@disparturientmontes.co.uk",SHA1(1234)),("Rajah","Garcia","1-239-267-0330","suscipit.nonummy@magna.co.uk",SHA1(1234)),("Justina","Moran","1-152-343-0451","molestie.in@ametultricies.edu",SHA1(1234)),("Jacqueline","Leon","1-178-651-6686","Nam@duinec.edu",SHA1(1234)),("Dieter","Merritt","1-543-351-1660","urna.Nullam@elementumdui.org",SHA1(1234)),("Octavia","Richards","1-188-155-7063","tempor@Duiscursusdiam.edu",SHA1(1234)),("Garth","Sears","1-743-606-2866","Nam.tempor@porttitorvulputateposuere.ca",SHA1(1234));
INSERT INTO Acct (first_name,last_name,phone,email,password) VALUES ("Ivor","Frye","1-413-520-1945","magna.Nam.ligula@non.ca",SHA1(1234)),("Fiona","Newman","1-998-725-6311","non@musDonecdignissim.co.uk",SHA1(1234)),("Oprah","Jacobs","1-865-397-3352","consectetuer.mauris@molestiedapibusligula.ca",SHA1(1234)),("Jessamine","Sawyer","1-290-683-8681","augue@Fuscemilorem.edu",SHA1(1234)),("Murphy","Hunt","1-682-367-0030","amet.risus.Donec@nuncullamcorpereu.com",SHA1(1234)),("Charde","Fleming","1-864-736-8879","ultrices@elitAliquam.org",SHA1(1234)),("Emmanuel","Lucas","1-306-711-6821","adipiscing.fringilla.porttitor@Etiamgravida.ca",SHA1(1234)),("Isadora","Thornton","1-840-846-3175","vitae.diam@Donecvitaeerat.com",SHA1(1234)),("Maryam","Dunn","1-875-315-9642","vestibulum.Mauris@dui.com",SHA1(1234)),("Kaye","Taylor","1-334-396-2773","lobortis.risus.In@ornarelectusante.edu",SHA1(1234));
INSERT INTO Acct (first_name,last_name,phone,email,password) VALUES ("Chase","Butler","1-600-434-4667","jdoe2",SHA1('jdoe2')),("Bethany","Anthony","1-236-635-8676","purus.Maecenas.libero@mauris.co.uk",SHA1(1234)),("Henry","Hebert","1-992-771-9039","luctus@ligula.ca",SHA1(1234)),("Carla","Brooks","1-116-463-9917","cursus.et@lectusa.ca",SHA1(1234)),("Lois","Austin","1-330-368-7455","elit.Nulla@euligula.org",SHA1(1234)),("Boris","Ford","1-173-309-0688","rhoncus.Proin.nisl@ultricesposuere.org",SHA1(1234)),("Colin","Skinner","1-768-554-3112","semper@ligula.org",SHA1(1234)),("Naomi","Raymond","1-207-976-6781","est.Nunc.laoreet@CuraePhasellusornare.ca",SHA1(1234)),("Lois","Middleton","1-799-171-7891","parturient@laciniaSed.ca",SHA1(1234)),("Edan","Sargent","1-760-103-3635","interdum.libero.dui@etmalesuada.co.uk",SHA1(1234));
INSERT INTO Acct (first_name,last_name,phone,email,password) VALUES ("Jane","Doe","1-816-653-0236","jdoe",SHA1('jdoe')),("Paloma","Hensley","1-338-677-9678","Pellentesque.habitant@nequesedsem.net",SHA1(1234)),("Jackson","Salazar","1-691-265-7505","nunc@pharetranibhAliquam.co.uk",SHA1(1234)),("Eric","Townsend","1-624-662-3310","Duis.gravida.Praesent@augueutlacus.net",SHA1(1234)),("Felix","Cummings","1-593-309-6208","elementum@molestiesodales.org",SHA1(1234)),("Clark","Fernandez","1-905-989-3580","justo.nec.ante@blanditmattisCras.com",SHA1(1234)),("Dolan","Gray","1-996-744-3360","vitae.aliquam.eros@sitametdiam.net",SHA1(1234)),("Anika","Mendez","1-954-979-6507","posuere@fringillamilacinia.ca",SHA1(1234)),("Renee","Johnson","1-857-477-3512","nec.imperdiet@Nullamfeugiat.co.uk",SHA1(1234)),("Ariel","Robinson","1-212-632-5800","venenatis.lacus.Etiam@nasceturridiculus.net",SHA1(1234));
update Acct set type =1 where email = 'jdoe';


INSERT INTO Purchase_H (user_id,amount) VALUES (159,"237.87"),(141,"202.80"),(113,"182.74"),(164,"71.41"),(146,"208.67"),(100,"128.03"),(105,"217.76"),(120,"168.88"),(176,"264.33"),(181,"112.15");
INSERT INTO Purchase_H (user_id,amount) VALUES (130,"269.09"),(184,"154.54"),(130,"112.29"),(161,"230.22"),(117,"109.89"),(146,"7.40"),(176,"218.76"),(128,"130.46"),(115,"120.04"),(129,"265.04");
INSERT INTO Purchase_H (user_id,amount) VALUES (107,"150.44"),(171,"173.86"),(155,"61.77"),(157,"256.51"),(195,"173.66"),(101,"92.00"),(106,"171.85"),(107,"158.65"),(144,"73.88"),(163,"82.35");
INSERT INTO Purchase_H (user_id,amount) VALUES (144,"113.36"),(189,"282.08"),(198,"105.79"),(165,"66.88"),(124,"32.07"),(111,"192.71"),(154,"179.86"),(161,"183.89"),(109,"62.78"),(181,"98.10");
INSERT INTO Purchase_H (user_id,amount) VALUES (108,"29.56"),(182,"98.53"),(112,"4.27"),(100,"217.04"),(165,"283.79"),(121,"242.42"),(187,"163.77"),(183,"186.12"),(104,"127.39"),(123,"149.88");
INSERT INTO Purchase_H (user_id,amount) VALUES (119,"71.20"),(173,"136.40"),(176,"249.76"),(145,"183.57"),(184,"42.83"),(188,"132.33"),(111,"10.11"),(177,"118.46"),(124,"126.29"),(132,"41.55");
INSERT INTO Purchase_H (user_id,amount) VALUES (199,"154.63"),(186,"295.82"),(152,"108.74"),(132,"270.76"),(170,"75.09"),(173,"142.31"),(104,"173.75"),(132,"161.54"),(108,"168.56"),(197,"127.51");
INSERT INTO Purchase_H (user_id,amount) VALUES (197,"69.06"),(175,"225.30"),(147,"52.18"),(153,"169.83"),(178,"149.98"),(118,"46.18"),(144,"100.02"),(190,"276.87"),(192,"135.22"),(178,"167.07");
INSERT INTO Purchase_H (user_id,amount) VALUES (176,"84.74"),(183,"207.10"),(152,"58.14"),(155,"149.45"),(199,"182.75"),(159,"174.57"),(168,"291.31"),(120,"111.59"),(185,"104.09"),(108,"55.28");
INSERT INTO Purchase_H (user_id,amount) VALUES (180,"267.93"),(138,"2.13"),(145,"39.45"),(144,"259.36"),(173,"80.44"),(116,"79.28"),(139,"229.87"),(131,"66.06"),(168,"215.77"),(175,"210.02");

INSERT INTO Purchase_H (user_id,amount) VALUES (136,"11.06"),(125,"4.34"),(172,"0.77"),(141,"240.42"),(157,"106.91"),(199,"47.81"),(159,"297.93"),(173,"146.23"),(156,"290.20"),(121,"209.35");
INSERT INTO Purchase_H (user_id,amount) VALUES (123,"229.54"),(161,"264.75"),(179,"248.21"),(111,"37.53"),(156,"244.54"),(137,"163.65"),(145,"242.99"),(195,"261.23"),(172,"299.47"),(177,"19.01");
INSERT INTO Purchase_H (user_id,amount) VALUES (145,"241.52"),(132,"198.47"),(182,"128.89"),(159,"203.36"),(132,"78.60"),(127,"14.47"),(116,"94.36"),(195,"118.25"),(184,"12.20"),(127,"281.59");
INSERT INTO Purchase_H (user_id,amount) VALUES (145,"71.83"),(198,"236.54"),(138,"106.03"),(164,"14.92"),(169,"156.56"),(150,"114.18"),(140,"69.98"),(154,"169.49"),(157,"37.88"),(166,"71.73");
INSERT INTO Purchase_H (user_id,amount) VALUES (189,"288.83"),(168,"100.08"),(172,"112.23"),(134,"117.64"),(109,"95.49"),(198,"284.22"),(177,"38.40"),(165,"148.79"),(164,"92.31"),(149,"287.91");
INSERT INTO Purchase_H (user_id,amount) VALUES (189,"164.24"),(109,"282.69"),(177,"173.79"),(184,"142.74"),(162,"5.68"),(171,"201.57"),(114,"205.86"),(137,"160.27"),(167,"236.84"),(144,"41.99");
INSERT INTO Purchase_H (user_id,amount) VALUES (146,"295.56"),(137,"22.66"),(143,"22.17"),(166,"25.14"),(154,"247.53"),(199,"168.65"),(142,"214.66"),(152,"89.58"),(186,"240.07"),(127,"222.05");
INSERT INTO Purchase_H (user_id,amount) VALUES (102,"19.20"),(153,"43.04"),(187,"249.07"),(148,"211.46"),(134,"85.42"),(162,"66.52"),(153,"29.73"),(115,"220.59"),(160,"269.45"),(173,"237.13");
INSERT INTO Purchase_H (user_id,amount) VALUES (151,"88.02"),(122,"100.84"),(126,"124.00"),(193,"7.05"),(137,"210.29"),(182,"44.29"),(181,"206.11"),(141,"274.14"),(120,"0.80"),(172,"246.32");
INSERT INTO Purchase_H (user_id,amount) VALUES (105,"109.81"),(132,"92.90"),(119,"180.95"),(134,"228.22"),(165,"136.99"),(167,"141.83"),(197,"238.61"),(150,"239.84"),(161,"214.94"),(148,"68.32");



