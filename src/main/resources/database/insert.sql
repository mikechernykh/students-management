INSERT INTO groups (id, name, faculty_name)
VALUES (1, 'IVT-11', 'FIT'),
       (2, 'IVT-12', 'FIT'),
       (3, 'SE-11', 'FIT'),
       (4, 'SE-12', 'FIT');

INSERT INTO students (id, l_name, f_name, m_name, dob, group_id)
VALUES (1, 'Doe', 'John', '', '1991-04-12', 3),
       (2, 'Иванов', 'Иван', 'Иванович', '1992-09-21', 1);