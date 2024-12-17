package com.informatika.data;

public abstract class Kendaraan {
	
	public String merk;
	public String model;
	public int tahunProduksi;
	
	public Kendaraan (String merk, String model, int tahunProduksi) {
		this.merk = merk;
		this.model = model;
		this.tahunProduksi = tahunProduksi;
	}
	
	abstract void nyalakanMesin();
	
	public final void tampilkanInfo() {
		System.out.println("Merk : " + merk);
		System.out.println("Model : " + model);
		System.out.println("Tahun Produksi : " + tahunProduksi);
	}

}
