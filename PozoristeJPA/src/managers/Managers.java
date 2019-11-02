package managers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Glumac;
import model.Predstava;
import model.Uloga;

public class Managers {

	private static EntityManager em = null;
	private static EntityTransaction et = null;

	static {

		em = JPAUtil.getEntityManager();

	}

	public static Glumac saveGlumac(String ime, String prez, String jmbg) {

		et = em.getTransaction();

		try {

			et.begin();

			Glumac g = new Glumac();

			g.setIme(ime);
			g.setPrezime(prez);
			g.setJmbg(jmbg);

			em.persist(g);

			em.flush();

			et.commit();

			return g;

		} catch (Exception e) {
			et.rollback();
			e.printStackTrace();
			return null;
		}

	}

	public static List<Predstava> getPredstave() {

		return em.createQuery("select p from Predstava p", Predstava.class).getResultList();

	}

	public static Predstava findPredstava(Integer id) {

		return em.find(Predstava.class, id);	

	}
	
	public static Uloga saveUloga(String naziv, Glumac g, Predstava p) {

		et = em.getTransaction();

		try {

			et.begin();

			Uloga u = new Uloga();
			
			u.setNaziv(naziv);
			u.setGlumac(g);
			u.setPredstava(p);

			em.persist(u);

			em.flush();

			et.commit();

			return u;

		} catch (Exception e) {
			et.rollback();
			e.printStackTrace();
			return null;
		}

	}

}
