create database Telecomunicatii;

use Telecomunicatii;

 drop table Utilizatori
 drop table Contacte
 
CREATE TABLE Utilizatori (
    id INT AUTO_INCREMENT PRIMARY KEY,
    Nume VARCHAR(255),
    Username VARCHAR(255) NOT NULL UNIQUE,
    DateOfBirth DATE,
    TelephoneNumber VARCHAR(20) UNIQUE NOT NULL,
    Email VARCHAR(255)UNIQUE,
    PasswordUser VARCHAR(255) NOT NULL
);

INSERT INTO Utilizatori (Nume, Username, DateOfBirth, TelephoneNumber, Email, PasswordUser) VALUES
('Ion Popescu', 'ipopescu', STR_TO_DATE('1/15/1990', '%m/%d/%Y'), '0712345678', 'ion.popescu@example.com', 'password123'),
('Maria Ionescu', 'mionescu', STR_TO_DATE('5/20/1985', '%m/%d/%Y'), '0723456789', 'maria.ionescu@example.com', 'pass456'),
('George Vasilescu', 'gvasilescu', STR_TO_DATE('3/10/1978', '%m/%d/%Y'), '0734567890', 'george.vasilescu@example.com', 'george789'),
('Ana Georgescu', 'ageorgescu', STR_TO_DATE('7/25/1992', '%m/%d/%Y'), '0745678901', 'ana.georgescu@example.com', 'ana000'),
('Vasile Marin', 'vmarin', STR_TO_DATE('11/30/1988', '%m/%d/%Y'), '0756789012', 'vasile.marin@example.com', 'vasile999'),
('Ciobanu Rodion', 'rodion', STR_TO_DATE('1/15/1990', '%m/%d/%Y'), '0712342678', 'rodioncap@gmail.com', '1qazxsw2');

select * from Utilizatori
where Username = 'rodion'

CREATE TABLE Contacte (
    user_id INT NOT NULL,
    contact_id INT NOT NULL,
    custom_name VARCHAR(255),
    PRIMARY KEY (user_id, contact_id),
    FOREIGN KEY (user_id) REFERENCES Utilizatori(id),
    FOREIGN KEY (contact_id) REFERENCES Utilizatori(id)
);

INSERT INTO Contacte (user_id, contact_id, custom_name)
SELECT u1.id AS user_id, u2.id AS contact_id, u2.Username AS custom_name
FROM Utilizatori u1
JOIN Utilizatori u2 ON u1.id <> u2.id
GROUP BY u1.id, u2.id
HAVING COUNT(*) <= 4;

select * from Utilizatori
where user_id = 1


SELECT u.Nume AS NumeContact, u.Username AS UsernameContact
FROM Utilizatori u
JOIN Contacte c ON u.id = c.contact_id
JOIN Utilizatori u2 ON c.user_id = u2.id
WHERE u2.Username = 'rodion';


