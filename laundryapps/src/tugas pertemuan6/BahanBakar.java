package com.informatika.data;

public interface BahanBakar {
	
	String jenisBahanBakar();
	
	default void infoKonsumsi() {
		System.out.println("Indo Konsumsi : Konsumsi Bahan Bakar tergantung kapasitas mesin");
	}

}
