package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.Izvodjenje;
import model.Karta;
import model.Mesto;
import model.Posetilac;
import model.Predstava;
import util.JPAUtil;

public class Controller {
	
	private static EntityManager em = null;
	static {
		
		em = JPAUtil.getEntityManager();
		
	}
	
	private static EntityTransaction et = null;
	
	public static Posetilac dodajPosetioca(Posetilac p) {
		
		et = em.getTransaction();
		
		try {
			
			et.begin();
			
			em.persist(p);
			
			em.flush();
			
			et.commit();
			
			return p;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			et.rollback();
			
			return null;
			
		} 
	}
	
	public static List<Izvodjenje> prikaz(String predstava) {
		
		et = em.getTransaction();
		
		try {
			
			et.begin();
			
			String upit = "SELECT i FROM Izvodjenje i WHERE i.predstava.naziv like :naziv";
			
			List<Izvodjenje> lista = em.createQuery(upit, Izvodjenje.class).setParameter("naziv", predstava).getResultList();
			
			et.commit();
			
			return lista;
			
		} catch(Exception e) {
			e.printStackTrace();
			et.rollback();
			return null;
		} 
	
	}
	
	public static List<Predstava> svePredstave(String ime, String prezime){
		
		et = em.getTransaction();
		
		try {
			
			et.begin();
			
			String upit = "SELECT p FROM Predstava p INNER JOIN p.ulogas u WHERE u.glumac.ime like :ime and u.glumac.prezime like :prezime";
			
			Query q = em.createQuery(upit, Predstava.class).setParameter("ime", ime).setParameter("prezime", prezime);
			
			List<Predstava> lista =	q.getResultList();
			
			et.commit();
			
			return lista;
			
		} catch(Exception e) {
			et.rollback();
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean rezervisiKartu(int idP, int idI, int idM) {
		
		et = em.getTransaction();
		
		try {
			
			et.begin();
			
			String upit = "select k from Karta k where k.mesto.idMesto = :idM and k.izvodjenje.idIzvodjenje = :idI";
			
			Query q = em.createQuery(upit).setParameter("idM", idM).setParameter("idI", idI);
			
			List<Karta> k = q.getResultList();
			
			if(k.isEmpty()) {
				
				Posetilac p = em.find(Posetilac.class, idP);
				
				Karta karta = new Karta();
				karta.setPosetilac(p);
				
				Izvodjenje i = em.find(Izvodjenje.class, idI);
				karta.setIzvodjenje(i);
				
				Mesto m = em.find(Mesto.class, idM);
				karta.setMesto(m);
				
				em.persist(karta);
				
				em.flush();
				
				et.commit();
				
				return true;
			} else {
				
				et.rollback();
				return false;
				
			} 
		} catch(Exception e) {
			e.printStackTrace();
			et.rollback();
			return false;
		}
	}
	
	public static List<Mesto> slobodnaMesta(int id){
		
		et = em.getTransaction();
		
		try {
			
			String upit = "select m from Mesto m "
						+ "inner join m.scena.izvodjenjes i "
						+ "where i.idIzvodjenja = :id and not exist(select k.mesto from Karta k where k.mesto = m "
						+ "and k.izvodjenje=i)";
			
			List<Mesto> lista = em.createQuery(upit, Mesto.class).setParameter("id", id).getResultList();
			
			et.commit();
			
			return lista;
			
		} catch(Exception e) {
			
			et.commit();
			return null;
		}
	}
	
	public static Predstava getPredstava(String naz) {
		
		try {
			
			String upit = "select p from Predstava p where p.naziv like :naz";
			
			Query q = em.createQuery(upit, Predstava.class).setParameter("naz", naz);
			
			List<Predstava> lista = q.getResultList();
			
			return lista.get(0);
			
		} catch(Exception e) {
			
			return null;
		}
	}

}
