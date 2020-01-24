package com.polygonetic.gui;

public enum MemoryAmount {

	NO_LIMIT("NO LIMIT"),
	GB_2("2GB MEM"),
	GB_4("4GB MEM"),
	GB_6("6GB MEM"),
	GB_8("8GB MEM"),
	GB_16("16GB MEM");

	MemoryAmount(final String amount) {
		this.amount = amount;
	}

	private String amount;

	public String getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return amount;
	}


}
