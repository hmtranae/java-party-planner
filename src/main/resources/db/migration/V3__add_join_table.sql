CREATE TABLE parties_friends (
  party_id int references parties (id) NOT NULL,
  friend_id int references friends (id) NOT NULL,
  PRIMARY KEY (party_id, friend_id),
  FOREIGN KEY (party_id) REFERENCES parties (id) ON UPDATE CASCADE,
  FOREIGN KEY (friend_id) REFERENCES friends (id) ON UPDATE CASCADE
);