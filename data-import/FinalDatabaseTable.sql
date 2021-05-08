CREATE TABLE User_ (
UserID VARCHAR (100) PRIMARY KEY,
username VARCHAR(100),
password VARCHAR(100),
firstName VARCHAR(100),
lastName VARCHAR(100)
);

CREATE TABLE Session_(
SessionID VARCHAR (100) PRIMARY KEY,
PointID VARCHAR (100),
VehicleID VARCHAR (100),
ProviderID VARCHAR (100),
OperatorID VARCHAR (100),
StartedOn DATETIME,
FinishedOn DATETIME,
Protocol VARCHAR (100),
EnergyDelivered FLOAT(3),
Payment VARCHAR (100)
);

CREATE TABLE Point_(
PointID VARCHAR (100) PRIMARY KEY,
StationID VARCHAR (100),
ChargingRate FLOAT(3)
);

CREATE TABLE Station(
StationID VARCHAR (100) PRIMARY KEY,
latitude Decimal(8,6),
longtitude Decimal(9,6)
);

CREATE TABLE Operator (
OperatorID VARCHAR (100) PRIMARY KEY,
username VARCHAR(100),
password VARCHAR(100),
brandName VARCHAR(100)
);

CREATE TABLE Provider(
ProviderID VARCHAR (100) PRIMARY KEY,
username VARCHAR(100),
password VARCHAR(100),
brandName VARCHAR(100),
PricePolicyRef VARCHAR(100),
CostPerKWh FLOAT(3)
);

CREATE TABLE EV(
VehicleID VARCHAR (100) PRIMARY KEY,
VehicleType VARCHAR(100),
Capacity FLOAT(3)
);

CREATE TABLE PointProviderRelation(
    PointID VARCHAR (100) NOT NULL,
    ProviderID VARCHAR (100) NOT NULL,
    FOREIGN KEY (PointID) REFERENCES Point_(PointID), 
    FOREIGN KEY (ProviderID) REFERENCES Provider(ProviderID),
    UNIQUE (PointID, ProviderID)
);

CREATE TABLE PointOperatorRelation(
    PointID VARCHAR (100) NOT NULL,
    OperatorID VARCHAR (100) NOT NULL,
    FOREIGN KEY (PointID) REFERENCES Point_(PointID), 
    FOREIGN KEY (OperatorID) REFERENCES Operator(OperatorID),
    UNIQUE (PointID, OperatorID)
);


CREATE TABLE UserEVRelation(
    UserID VARCHAR (100) NOT NULL,
    VehicleID VARCHAR (100) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES User_(UserID), 
    FOREIGN KEY (VehicleID) REFERENCES EV(VehicleID),
    UNIQUE (UserID, VehicleID)
);

CREATE TABLE PointEVRelation(
    PointID VARCHAR (100) NOT NULL,
    VehicleID VARCHAR (100) NOT NULL,
    FOREIGN KEY (PointID) REFERENCES Point_(PointID), 
    FOREIGN KEY (VehicleID) REFERENCES EV(VehicleID),
    UNIQUE (PointID, VehicleID)
);


CREATE TABLE StationProviderRelation(
    StationID VARCHAR (100) NOT NULL,
    ProviderID VARCHAR (100) NOT NULL,
    FOREIGN KEY (StationID) REFERENCES Station(StationID), 
    FOREIGN KEY (ProviderID) REFERENCES Provider(ProviderID),
    UNIQUE (StationID, ProviderID)
);

CREATE TABLE StationOperatorRelation(
    StationID VARCHAR (100) NOT NULL,
    OperatorID VARCHAR (100) NOT NULL,
    FOREIGN KEY (StationID) REFERENCES Station(StationID), 
    FOREIGN KEY (OperatorID) REFERENCES Operator(OperatorID),
    UNIQUE (StationID, OperatorID)
);

CREATE TABLE LoggedUser(
token VARCHAR (100),
username VARCHAR(100) PRIMARY KEY
);