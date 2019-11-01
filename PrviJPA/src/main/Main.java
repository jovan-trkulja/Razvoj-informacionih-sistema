package main;

import controller.Controller;
import model.Posetilac;

public class Main {

	public static void main(String[] args) {
		
		/*Posetilac p = new Posetilac();
		
		p.setIme("Andreja");
		p.setPrezime("Simic");
		
		Posetilac o = Controller.dodajPosetioca(p);
		
		if(o != null) {
			System.out.println("Uspesno dodat posetilac -> " + o.toString());
		} else {
			System.err.println("Greska prilikom dodavanja posetioca");
		}
		*/
		System.out.println();
		
		
		Controller.prikaz("Laza i paralaza").forEach(System.out::println);
		
		System.out.println();
		
		Controller.svePredstave("Mirjana", "Miric").forEach(System.out::println);
			
		System.out.println();
		
		if(Controller.rezervisiKartu(2, 2, 1)) {
			System.out.println("Uspesno ste rezervisali kartu!");
		} else {
			System.err.println("Greska prilikom rezervisanja karte");
		}
	}

}
