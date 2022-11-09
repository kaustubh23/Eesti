CREATE TABLE IF NOT EXISTS items (
    id integer PRIMARY KEY,
    name varchar(50),
    price numeric (8,2),
	type varchar(50),
	quantity integer
);