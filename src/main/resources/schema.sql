CREATE TABLE author
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE category
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE book
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES category (id)
);

CREATE TABLE book_author
(
    book_id   INT,
    author_id INT,
    FOREIGN KEY (book_id) REFERENCES book (id),
    FOREIGN KEY (author_id) REFERENCES author (id)
);

INSERT INTO category (name)
VALUES ('Science Fiction'),
       ('Mystery'),
       ('Romance'),
       ('Biography');

INSERT INTO author (name)
VALUES ('Isaac Asimov'),
       ('Agatha Christie'),
       ('Jane Austen'),
       ('Walter Isaacson');

INSERT INTO book (title, category_id)
VALUES ('Foundation', 1),
       ('I, Robot', 1),
       ('Murder on the Orient Express', 2),
       ('And Then There Were None', 2),
       ('Pride and Prejudice', 3),
       ('Sense and Sensibility', 3),
       ('Steve Jobs', 4),
       ('Leonardo da Vinci', 4);

INSERT INTO book_author (book_id, author_id)
VALUES (1, 1),
       (2, 1),
       (3, 2),
       (4, 2),
       (5, 3),
       (6, 3),
       (7, 4),
       (8, 4);