DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals(description, datetime, calories, userid) VALUES
  ('Админ ланч',make_timestamp(2015, 6, 1, 14, 0, 0), 510, 100001),
  ('Админ ужин',make_timestamp(2015, 6, 1, 21, 0, 0), 1500, 100001),
  ('Завтрак',make_timestamp(2015, 5, 30, 10, 0, 0), 500, 100000),
  ('Обед',make_timestamp(2015, 5, 30, 13, 0, 0), 1000, 100000),
  ('Ужин',make_timestamp(2015, 5, 30, 20, 0, 0), 500, 100000),
  ('Завтрак',make_timestamp(2015, 5, 10, 14, 0, 0), 1000, 100000),
  ('Обед',make_timestamp(2015, 5, 31, 13, 0, 0), 500, 100000),
  ('Ужин',make_timestamp(2015, 5, 31, 20, 0, 0), 510, 100000);