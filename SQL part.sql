7) В подключенном MySQL репозитории создать базу данных “Друзья человека”

DROP DATABASE IF exists Humansfriends;
CREATE DATABASE Humansfriends;

8) Создать таблицы с иерархией из диаграммы в БД
USE Humansfriends;

INSERT INTO Animals (Animal_type) VALUES 
('Pack'),
('Pet'); 

CREATE TABLE Packanimals (
 Id INT AUTO_INCREMENT PRIMARY KEY,
 PA_name VARCHAR (20),
 Kind_id INT,
 FOREIGN KEY (Kind_id) REFERENCES Animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Packanimals (PA_name, Kind_id) VALUES 
('Horses', 1),
('Donkeys', 1), 
('Camels', 1); 
 
CREATE TABLE Pets (
 Id INT AUTO_INCREMENT PRIMARY KEY,
 P_name VARCHAR (20),
 Kind_id INT,
 FOREIGN KEY (Kind_id) REFERENCES Animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Pets (P_name, Kind_id) VALUES 
('Cats', 2),
('Dogs', 2), 
('Hamsters', 2); 
SHOW DATABASES

9) Заполнить низкоуровневые таблицы именами(животных), командами которые они выполняют и датами рождения
CREATE TABLE Dogs ( 
 Id INT AUTO_INCREMENT PRIMARY KEY, 
 Name VARCHAR(20), 
 Birthday DATE,
 Commands VARCHAR(50),
 Kind_id int,
 Foreign KEY (Kind_id) REFERENCES Pets (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO Dogs (Name, Birthday, Commands, Kind_id) VALUES 
('Lyusha', '2020-01-01', 'mesto, fas, golos', 2),
('Barbos', '2021-08-22', 'stoyat', legat, sidet', 2), 
('Sharik', '2017-10-10', 'golos,sidet',mesto', 2);

CREATE TABLE Hamsters ( 
 Id INT AUTO_INCREMENT PRIMARY KEY, 
 Name VARCHAR(20), 
 Birthday DATE,
 Commands VARCHAR(50),
 Kind_id int,
 Foreign KEY (Kind_id) REFERENCES Pets (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Hamsters (Name, Birthday, Commands, Kind_id) VALUES 
('Sonya', '2022-12-12', 'sleep', 3),
('Tom', '2023-01-02', 'be quiet', 3), 
('Petr', '2023-05-05', 'be quiet', 3), 
('Sonny', '2022-07-17', 'be quiet', 3);

CREATE TABLE Cats ( 
 Id INT AUTO_INCREMENT PRIMARY KEY, 
 Name VARCHAR(20), 
 Birthday DATE,
 Commands VARCHAR(50),
 Kind_id int,
 Foreign KEY (Kind_id) REFERENCES Pets (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Cats (Name, Birthday, Commands, Kind_id) VALUES 
('murzik', '2020-01-01', 'be quiet', 1),
('Vasya', '2021-02-02', 'go', 1), 
('Felix', '2022-03-03', 'go eat', 1); 

CREATE TABLE Donkeys ( 
 Id INT AUTO_INCREMENT PRIMARY KEY, 
 Name VARCHAR(20), 
 Birthday DATE,
 Commands VARCHAR(50),
 Kind_id int,
 Foreign KEY (Kind_id) REFERENCES Pack_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Donkeys (Name, Birthday, Commands, Kind_id) VALUES 
('Grey', '2019-04-10', 'go', 2),
('Black', '2020-03-12', '', 2), 
('White', '2021-07-12', 'go', 2), 
('Pink', '2022-12-10', 'go', 2);

CREATE TABLE Camels ( 
 Id INT AUTO_INCREMENT PRIMARY KEY, 
 Name VARCHAR(20), 
 Birthday DATE,
 Commands VARCHAR(50),
 Kind_id int,
 Foreign KEY (Kind_id) REFERENCES Pack_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Camels (Name, Birthday, Commands, Kind_id) VALUES 
('Turbo', '2022-05-10', 'turn', 3),
('Chin-Chin', '2019-03-12', 'drag, drop', 3), 
('LoveIs', '2015-07-12', 'chew, spit, run', 3);

CREATE TABLE Horses ( 
 Id INT AUTO_INCREMENT PRIMARY KEY, 
 Name VARCHAR(20), 
 Birthday DATE,
 Commands VARCHAR(50),
 Kind_id int,
 Foreign KEY (Kind_id) REFERENCES Pack_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Horses (Name, Birthday, Commands, Kind_id) VALUES 
('Voron', '2012-12-12', 'baryer', 1),
('Molnia', '2017-12-03', 'sleep, stop', 1), 
('Rassvet', '2018-11-12', 'galop', 1);

10) Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку. 
Объединить таблицы лошади, и ослы в одну таблицу.

SET SQL_SAFE_UPDATES = 0;
DELETE FROM Camels;

SELECT Name, Birthday, Commands FROM Horses
UNION SELECT Name, Birthday, Commands FROM Donkeys;


11) Создать новую таблицу “молодые животные” в которую попадут все животные старше 1 года, 
но младше 3 лет и в отдельном столбце с точностью до месяца подсчитать возраст животных в новой таблице
DROP table IF exists allanimals;

CREATE TEMPORARY TABLE allanimals AS 
SELECT *, 'Horses' as Genus FROM Horses
UNION SELECT *, 'Donkeys' AS Genus FROM Donkeys
UNION SELECT *, 'Camels' AS Genus FROM Camels
UNION SELECT *, 'Dogs' AS Genus FROM Dogs
UNION SELECT *, 'Cats' AS Genus FROM Cats
UNION SELECT *, 'Hamsters' AS Genus FROM Hamsters;

CREATE TABLE younganimals AS
SELECT Name, Birthday, Commands, Genus, TIMESTAMPDIFF(MONTH, Birthday, CURDATE()) AS Age_in_month
FROM Any_animals WHERE Birthday BETWEEN ADDDATE(curdate(), INTERVAL -3 YEAR) AND ADDDATE(CURDATE(), INTERVAL -1 YEAR);
 
SELECT * FROM younganimals;

12) Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую 
принадлежность к старым таблицам.

create table new (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY)
select  Name, 
        Birthday,
        Commands,
        'dogs' as oldTable
from dogs d  union

select  Name, 
        Birthday,
        Commands, 
        'hamsters' as oldTable
from hamsters h  union 

select  Name, 
        Birthday,
        Commands,
        'cats' as oldTable
from cats c union 

select  Name, 
        Birthday,
        Commands,
        'donkeys' as oldTable
from donkeys d ;

select  Name, 
        Birthday,
        Commands, 
        'horses' as oldTable
from horses h  union 


select * from new;
