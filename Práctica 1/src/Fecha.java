

public class Fecha {
	private int dd;
	private int mm;
	private int aa;

	public Fecha(int dd, int mm, int aa) {
		this.dd = dd;
		this.mm = mm;
		this.aa = aa;
	}

	// ToString

	@Override
	public String toString() {
		return dd + "/" + mm + "/" + aa;
	}

	// Gets y sets

	public int getAa() {
		return aa;
	}

	public int getDd() {
		return dd;
	}

	public int getMm() {
		return mm;
	}

	public void setAa(int aa) {
		this.aa = aa;
	}

	public void setDd(int dd) {
		this.dd = dd;
	}

	public void setMm(int mm) {
		this.mm = mm;
	}
}
