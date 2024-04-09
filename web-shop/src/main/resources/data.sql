INSERT INTO Proizvod (naziv, opis, slika, cena, datum_objavljivanja, ostavljenja_recenzija_od_strane_kupca, ostavljenja_recenzija_od_strane_prodavca, prodat) VALUES ('Komjuterski mis', 'stanje odlicno', '#', 250.0, '2023-02-05', 0, 0, 0);

INSERT INTO Kategorija (naziv) values ('Komjuterske periferije');

--CREATE TYPE TipKorosnika AS ENUM('Prodavac', 'Kupac', 'Administrator');
INSERT INTO Korisnik (ime, prezime, korisnicko_ime, mejl_adresa, broj_telefona, lozinka, datum_rodjenja, putanja_do_slike, opis, blokiran,uloga, prosena_ocena)
VALUES ('John', 'Doe', 'johndoe', 'john@example.com', '123456789', 'password', '1990-05-15', '/path/to/image', 'Description', false, 1, 0.0);

INSERT INTO Ponuda (Cena, kupac_id, proizvod_id) VALUES (150.0, 1, 1);


