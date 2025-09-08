CREATE TABLE IF NOT EXISTS restaurant (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name TEXT NOT NULL,
    address TEXT NOT NULL,
    cuisineType TEXT NOT NULL,
    rating INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS chef (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    firstName TEXT NOT NULL,
    lastName TEXT NOT NULL,
    expertise TEXT NOT NULL,
    experienceYears INTEGER NOT NULL,
    restaurantId INTEGER,
    FOREIGN KEY (restaurantId) REFERENCES restaurant(id)
);
