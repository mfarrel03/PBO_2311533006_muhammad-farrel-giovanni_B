package model;

public class Order {
	String id, nama_costumer, pembayaran, total, tanggal, tanggal_peng, status, status_pembayaran;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getpembayaran() {
		return pembayaran;
	}

	public void setpembayaran(String pembayaran) {
		this.pembayaran = pembayaran;
	}

	public String getNama_costumer() {
		return nama_costumer;
	}

	public void setNama_costumer(String nama_costumer) {
		this.nama_costumer = nama_costumer;
	}


	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getTanggal() {
		return tanggal;
	}

	public void setTanggal(String tanggal) {
		this.tanggal = tanggal.toString();
	}

	public String getTanggal_selesai() {
		return tanggal_peng;
	}

	public void setTanggal_selesai(String tanggal_selesai) {
		this.tanggal_peng = tanggal_selesai.toString();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus_pembayaran() {
		return status_pembayaran;
	}

	public void setStatus_pembayaran(String status_pembayaran) {
		this.status_pembayaran = status_pembayaran;
	}
	
}
