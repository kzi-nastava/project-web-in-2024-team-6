package com.webshop;

import com.webshop.model.Kategorija;
import com.webshop.model.Korisnik;
import com.webshop.model.Ponuda;
import com.webshop.model.Proizvod;
import com.webshop.repository.KategorijaRepository;
import com.webshop.repository.KorisnikRepository;
import com.webshop.repository.PonudaRepository;
import com.webshop.repository.ProizvodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/*
   @SpringBootApplication anotacija nastala je od @EnableAutoConfiguration anotacije koja
   upravlja konfiguracijom aplikacije.
 */
@SpringBootApplication
public class WebShopApplication implements CommandLineRunner {

	@Autowired
	private KorisnikRepository korisnikRepository;

	@Autowired
	private KategorijaRepository kategorijaRepository;

	@Autowired
	private ProizvodRepository proizvodRepository;

	@Autowired
	private PonudaRepository ponudaRepository;

	@Override
	public void run(String... args) {

		Korisnik k1 = new Korisnik();
		k1.setIme("Dejan");
		k1.setPrezime("Dejanic");
		k1.setKorisnickoIme("dekideki");
		k1.setMejlAdresa("dejan22@gmail.com");
		k1.setBrojTelefona("064536213");
		k1.setDatumRodjenja(new Date(1990, 2, 30));
		k1.setOpis("posten");
		k1.setBlokiran(false);
		k1.setProsenaOcena(4.9);
		k1.setUloga(Korisnik.TipKorisnika.Prodavac);
		k1.setLozinka("dejanceprodaja");

		this.korisnikRepository.save(k1);

		Korisnik k2 = new Korisnik();
		k2.setIme("Slavko");
		k2.setPrezime("Slavkic");
		k2.setKorisnickoIme("slavislavi");
		k2.setMejlAdresa("slavkic222@gmail.com");
		k2.setBrojTelefona("0645362511");
		k2.setDatumRodjenja(new Date(1980, 2, 30));
		k2.setOpis("posten");
		k2.setBlokiran(false);
		k2.setProsenaOcena(4.5);
		k2.setUloga(Korisnik.TipKorisnika.Kupac);
		k2.setLozinka("slavkokupuje");



		Kategorija kat1 = new Kategorija();
		kat1.setNaziv("Kuca");

		this.kategorijaRepository.save(kat1);

		Proizvod p1 = new Proizvod();
		p1.setNaziv("Metla");
		p1.setCena(500);
		p1.setOpis("metalna drska");
		p1.setKategorija(kat1);
		p1.setDatumObjavljivanja(new Date());
		p1.setProdat(false);
		p1.setTipProdaje(Proizvod.tipprodaje.aukcija);
		p1.setOstavljenaRecenzijaOdStraneKupca(false);
		p1.setOstavljenaRecenzijaOdStraneProdavca(false);
		p1.setProdavac(k1);

		Proizvod p2 = new Proizvod();
		p2.setNaziv("kanta");
		p2.setCena(600);
		p2.setOpis("metalna");
		p2.setKategorija(kat1);
		p2.setDatumObjavljivanja(new Date());
		p2.setProdat(true);
		p2.setTipProdaje(Proizvod.tipprodaje.aukcija);
		p2.setOstavljenaRecenzijaOdStraneKupca(false);
		p2.setOstavljenaRecenzijaOdStraneProdavca(false);
		p2.setProdavac(k1);
		p2.setKupac(k2);

		Set<Ponuda> ponude = new HashSet<Ponuda>();

		this.proizvodRepository.save(p1);


		Set<Proizvod> proizvodi = new HashSet<Proizvod>();
		proizvodi.add(p1);

		Set<Proizvod> listaProizvoda = new HashSet<>();
		listaProizvoda.add(p2);



		this.korisnikRepository.save(k2);

		kat1.setProizvod(proizvodi);

		Ponuda pon1 = new Ponuda();
		pon1.setCena(600);
		pon1.setProizvod(p2);
		pon1.setKupacKojiJeDaoPonudu(k2);

		ponude.add(pon1);
		p2.setPonude(ponude);


		this.proizvodRepository.save(p2);
		this.ponudaRepository.save(pon1);


	}

	public static void main(String[] args) {
		SpringApplication.run(WebShopApplication.class, args);
	}

}
