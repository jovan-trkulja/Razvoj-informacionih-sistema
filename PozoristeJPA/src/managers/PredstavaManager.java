package managers;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import model.Izvodjenje;
import model.Mesto;
import model.Predstava;


public class PredstavaManager {

	public List<Izvodjenje> vratiIzvodjenja(String naslov){
		EntityManager em = JPAUtil.getEntityManager();
		List<Izvodjenje> izvodjenja = em.createQuery("select i from Izvodjenje i where i.predstava.naziv like :naslovP", Izvodjenje.class).
			setParameter("naslovP", naslov).getResultList();
		return izvodjenja;
	}
	
	public List<Predstava> vratiPredstaveGlumca(String ime, String prezime){
		EntityManager em = JPAUtil.getEntityManager();
		List<Predstava> predstave = em.createQuery("select p from Predstava p inner join p.ulogas g where "
				+ "g.glumac.ime like :ime and g.glumac.prezime like :prezime", Predstava.class).
			setParameter("ime", ime).setParameter("prezime", prezime).getResultList();
		return predstave;
	}
	
	public List<Date> prikaziDatumeIzvodjenjaPredstave(String naziv){
		EntityManager em = JPAUtil.getEntityManager();
		List<Date> datumi = em.createQuery("select i.datum from Izvodjenje i where i.predstava.naziv like :naziv",
				Date.class).setParameter("naziv", naziv).getResultList();
		return datumi;
	}
	
	public List<Mesto> prikaziSlobodnaMesta(String nazivPredstave, Date datumIzvodjenja){
		EntityManager em = JPAUtil.getEntityManager();
		List<Mesto> mesta = em.createQuery("select m from Mesto m inner join m.scena.izvodjenjes i where i.predstava.naziv "
				+ "like :naziv and i.datum = :datumIzv and "
				+ "not exists (select k.mesto from Karta k where k.mesto=m and "
				+ "k.izvodjenje=i)", Mesto.class)
				.setParameter("naziv", nazivPredstave)
				.setParameter("datumIzv", datumIzvodjenja)
				.getResultList();
		return mesta;
	}
	
		
	public static void main(String[] args) {
		PredstavaManager pm = new PredstavaManager();
		//2.
		List<Izvodjenje> izvodjenja = pm.vratiIzvodjenja("Laza i paralaza");
		System.out.println("Izvodjenja za predstavu sa naslovom Laza i paralaza:");
		for(Izvodjenje i:izvodjenja) {
			System.out.println("datum: "+i.getDatum()+", scena: "+i.getScena().getNaziv());
		}
		
		//3.
		List<Predstava> predstave = pm.vratiPredstaveGlumca("Mirjana", "Miric");
		System.out.println("Predstave Mirjane Miric: ");
		for(Predstava p:predstave) {
			System.out.println("Naziv:"+p.getNaziv()+", trajanje: "+p.getTrajanje()+", "
					+ "zanr: "+p.getZanr().getNaziv()+", reziser: "+p.getReziser().getIme()+" "+p.getReziser().getPrezime());
		}
		try {
			List<Date> datumi = pm.prikaziDatumeIzvodjenjaPredstave("Laza i paralaza");
			System.out.println("Datumi izvodjenja predstave Laza i paralaza su: ");
			for(Date d:datumi)
				System.out.println(d);
			
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//Date datum = sdf.parse("2019-03-10");
			if(datumi.size()>0) {
				List<Mesto> slobodnaMesta = pm.prikaziSlobodnaMesta("Laza i paralaza", datumi.get(0));
				System.out.println("Prikaz slobodnih mesta za predstavu Laza i paralaza, dana "+datumi.get(0)+":");
				for(Mesto m:slobodnaMesta)
					System.out.println(m.getBroj()+", red "+m.getRed()+",sekcija "+m.getSekcija());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}