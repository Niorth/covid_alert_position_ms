CREATE TABLE IF NOT EXISTS suspiciousPosition (
positionId  INTEGER NOT NULL AUTO_INCREMENT,
latitude FLOAT(30) NOT NULL,
longitude FLOAT(30) NOT NULL,
positionDate TIMESTAMP NOT NULL,
PRIMARY KEY (positionId)
);