use sip06;

insert into account values
    (1, 'juan', 'Juan', 'Cazares', 'juan.cazares@example.com', 'p@ssword', 1, 1, 1, null, null),
    (2, 'elvira', 'Elvira', 'Cazares', 'elvira.cazares@example.com', 'p@ssword', 1, 1, 1, null, null);

insert into role values
    (1, 'user'),
    (2, 'admin');

insert into account_role values
    (0, 1, 1),
    (0, 1, 2),
    (0, 2, 1);
