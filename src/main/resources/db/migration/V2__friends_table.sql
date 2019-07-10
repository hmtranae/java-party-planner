CREATE TABLE friends (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  party_id int NOT NULL REFERENCES parties(id)
);
