INSERT INTO users (username, password, nickname) VALUES ('testuser', 'password', '테스트유저');

INSERT INTO settlements (name, owner_id) VALUES ('강릉 여행', 1), ('망년회', 2);

INSERT INTO expenses (name, amount, date, settlement_id, user_id)
VALUES ('기차표 예매', 80000, '2023-08-01', 1, 1),
       ('숙소 값', 200000, '2023-08-02', 1, 2),
       ('첫날 저녁', 50000, '2023-08-01', 1, 1);
