create database phoenix_db;

create user if not exists 'phoenix_user'@'localhost' identified by 'pass123';
grant all privileges  on phoenix_db.* to 'phoenix_user'@'localhost';
flush privileges;