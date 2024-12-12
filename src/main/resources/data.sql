INSERT INTO users (id, username, password, nickname)
VALUES (1, 'testuser', 'password', '테스트유저'),
       (2, 'testuser2', 'password', '테스트유저2');

INSERT INTO settlements (id, name, owner_id)
VALUES (1, '강릉 여행', 1),
       (2, '망년회', 2);

INSERT INTO expenses (id, name, amount, date, settlement_id, user_id)
VALUES (1, '기차표 예매', 80000, '2023-08-01', 1, 1),
       (2, '숙소 값', 200000, '2023-08-02', 1, 2),
       (3, '첫날 저녁', 50000, '2023-08-01', 1, 1);

