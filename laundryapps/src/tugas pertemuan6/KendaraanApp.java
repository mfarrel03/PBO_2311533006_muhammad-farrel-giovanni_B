package com.informatika.application;

import com.informatika.data.Bus;
import com.informatika.data.Mobil;

public class KendaraanApp {
	
	public static void main(String[] args) {
		
		Mobil avanza = new Mobil("Toyota", "Avanza", 2021);
		avanza.tampilkanInfo();
		avanza.nyalakanMesin();
		System.out.println("Jenis bahan bakar " + avanza.jenisBahanBakar());
		avanza.infoKonsumsi();
		avanza.fiturMobil();
		
		System.out.println("");
		
		Bus busPariwisata = new Bus ("Mercedes-Benz", "Bus Pariwisata", 2021);
		busPariwisata.tampilkanInfo();
		busPariwisata.nyalakanMesin();
		System.out.println("Jenis bahan bakar " + busPariwisata.jenisBahanBakar());
		busPariwisata.infoKonsumsi();
		busPariwisata.fiturBus();
		
		Bus.jadwalPerjalanan jadwalBus = busPariwisata.new jadwalPerjalanan("Jakarta-Bandung"," 08.00");
		jadwalBus.tampilkanJadwal();
	}

}
