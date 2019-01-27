DROP DATABASE IF EXISTS mall;
CREATE DATABASE mall CHARACTER SET UTF8;
USE mall;
DROP TABLE IF EXISTS user;
CREATE TABLE  user(
--     id INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL AUTO_INCREMENT ,
    user_name VARCHAR (50) NOT NULL,
    user_password VARCHAR (32) NOT NULL,
    PRIMARY KEY (user_id)
)ENGINE=InnoDB;
INSERT  INTO user(user_name,user_password) VALUES ('buyer','37254660e226ea65ce6f1efd54233424');
INSERT  INTO user(user_name,user_password) VALUES ('seller','981c57a5cfb0f868e064904b8745766f');

DROP TABLE IF EXISTS goods;
CREATE TABLE goods(
  gid INT NOT NULL AUTO_INCREMENT,
  title VARCHAR (80) NOT NULL unique,
  sellPrice FLOAT NOT NULL,
  buyPrice FLOAT NOT NULL,
  summary VARCHAR(140),
  detail VARCHAR (1000),
  image VARCHAR (100),
#   buyTime datetime DEFAULT  CURRENT_TIMESTAMP ,
#   time datetime DEFAULT  CURRENT_TIMESTAMP ,
  buyTime VARCHAR (100),
  time VARCHAR (100),
  totalCount int DEFAULT 0,
  sellCount int DEFAULT 0,
  sell boolean,
  buyCount int DEFAULT 0,
  buy boolean,
  PRIMARY KEY (gid)
)ENGINE=InnoDB;

DROP TABLE IF EXISTS user_goods;
CREATE TABLE user_goods(
  uid int NOT NULL,
  gid int NOT NULL,
  CONSTRAINT fk__user_id FOREIGN KEY(uid) REFERENCES user(user_id),
  CONSTRAINT fk_goods_id FOREIGN KEY(gid) REFERENCES goods(gid)
  ON DELETE CASCADE
  ON UPDATE RESTRICT
)ENGINE=InnoDB;

DROP TABLE IF EXISTS buy_goods;
CREATE TABLE  buy_goods(
  id INT  NOT NULL AUTO_INCREMENT,
  gid INT NOT NULL ,
  num INT NOT NULL,
  buyPrice FLOAT NOT NULL,
#   time datetime DEFAULT  CURRENT_TIMESTAMP ,
  time VARCHAR (100),
  PRIMARY KEY (id)
)ENGINE=InnoDB;

DROP TABLE IF EXISTS bgs;
CREATE TABLE bgs(
  uid int NOT NULL,
  id int NOT NULL,
  CONSTRAINT fk__id FOREIGN KEY(uid) REFERENCES user(user_id),
  CONSTRAINT fk_gid FOREIGN KEY(id) REFERENCES buy_goods(id)
  ON DELETE CASCADE
  --   ON UPDATE RESTRICT;
)ENGINE=InnoDB;








