package model;

public class StockCode {
	private String name;
	private float change; // +/- giá.
	private float changepercent; // +/- %.
	private float closeprice;
	private float price; // giá tham chiếu.
	private float openprice;
	private float hprice; // h~highest -> giá cao nhất.
	private float lprice; // l~lowest -> giá thấp nhất.
	private int volume;

	public StockCode(String name, float change, float changepercent, float closeprice, float price, float openprice,
			float hprice, float lprice, int volume) {
		this.name = name;
		this.change = change;
		this.changepercent = changepercent;
		this.closeprice = closeprice;
		this.price = price;
		this.openprice = openprice;
		this.hprice = hprice;
		this.lprice = lprice;
		this.volume = volume;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getChange() {
		return change;
	}

	public void setChange(float change) {
		this.change = change;
	}

	public float getChangePercent() {
		return changepercent;
	}

	public void setChangePercent(float changepercent) {
		this.changepercent = changepercent;
	}

	public float getCloseprice() {
		return closeprice;
	}

	public float getOpenprice() {
		return openprice;
	}

	public float getHprice() {
		return hprice;
	}

	public float getLprice() {
		return lprice;
	}

	@Override
	public String toString() {
		return getName() + ", " + getChange() + ", " + getChangePercent() + "% " + getCloseprice() + ", " + getPrice()
				+ ", " + getOpenprice() + ", " + getHprice() + ", " + getLprice() + ", " + getVolume();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof StockCode) {
			return ((StockCode) obj).name.equals(name) && ((StockCode) obj).volume == volume
					&& ((StockCode) obj).change == change && ((StockCode) obj).changepercent == changepercent;
		} else
			return false;
	}
}
