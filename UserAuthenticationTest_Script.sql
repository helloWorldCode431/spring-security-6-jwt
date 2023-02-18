CREATE DATABASE user_authentication_test_db;
USE user_authentication_test_db;

CREATE TABLE Users (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  Username VARCHAR(15) NOT NULL UNIQUE,
  Email varchar(254) NOT NULL UNIQUE, 
  Password VARCHAR(254) NOT NULL,
  Enabled BOOLEAN NOT NULL,
  DateCreated BIGINT NOT NULL DEFAULT(unix_timestamp()),
  VerifiedEmail bit NOT NULL DEFAULT(0)  
);

CREATE TABLE Roles (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  RoleName VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE UserRoles (
  UserID INT NOT NULL,
  RoleID INT NOT NULL,
  PRIMARY KEY (UserID, RoleID),
  FOREIGN KEY (UserID) REFERENCES Users(ID),
  FOREIGN KEY (RoleID) REFERENCES Roles(ID)
);

CREATE TABLE Posts(
	ID int NOT NULL PRIMARY KEY auto_increment,
    UserID int NOT NULL,
    Title varchar(250) NOT NULL,     
    Body varchar(2000) NOT NULL,        
    FOREIGN KEY (UserID) REFERENCES Users(ID)
);

INSERT INTO Roles (RoleName) VALUES ('user');
INSERT INTO Roles (RoleName) VALUES ('admin');


CREATE PROCEDURE addPost(IN _userID int, IN _title varchar(250), IN _body varchar(2000))
	INSERT INTO Posts(UserID, Title, Body)
    VALUES(_userID, _title, _body);

DELIMITER //
CREATE PROCEDURE addUser(IN _email varchar(254), IN _pass varchar(255), IN _username varchar(15), IN _roleName varchar(50))
BEGIN
  START TRANSACTION;

  INSERT INTO Users (Email, Password, Username, Enabled) 
  VALUES (_email, _pass, _username, 1);

  SET @user_id = LAST_INSERT_ID();

  SET @role_id = (SELECT ID FROM Roles WHERE RoleName = _roleName);

  INSERT INTO UserRoles (UserID, RoleID) 
  VALUES (@user_id, @role_id);

  COMMIT;
END//


CALL addUser('admin@example.com', 'helloworld', 'administrator', 'admin');
CALL addUser('user@gmail.com', 'helloworld', 'user123', 'user');

CALL addPost(2, "This is a test title", "This is a test body");
CALL addPost(2, "This is a test title", "This is a test body");
CALL addPost(2, "This is a test title", "This is a test body");



	
