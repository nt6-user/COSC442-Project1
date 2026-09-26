package edu.towson.cis.cosc442.project1.monopoly;

public class PropertyCell extends Cell {
	private String colorGroup;
	private int housePrice;
	private int numHouses;
	private int rent;
	private int sellPrice;

	/**
	 * Returns the color group this property belongs to.
	 * @return The color group of the property as a string.
	 */
	/**
	 * Returns the color group this property belongs to.
	 * @return The color group of the property as a string.
	 */
	public String getColorGroup() {
		return colorGroup;
	}

	/**
	 * Returns the cost to build a house on this property.
	 * @return The price of a house on this property as an integer.
	 */
	/**
	 * Returns the cost to build a house on this property.
	 * @return The price of a house on this property as an integer.
	 */
	public int getHousePrice() {
		return housePrice;
	}

	/**
	 * Returns the current number of houses built on this property.
	 * @return The number of houses on the property as an integer.
	 */
	/**
	 * Returns the current number of houses built on this property.
	 * @return The number of houses on the property as an integer.
	 */
	public int getNumHouses() {
		return numHouses;
	}
    
    /**
     * Returns the selling price of this property.
     * @return The selling price of the property as an integer.
     */
    /**
     * Returns the selling price of this property.
     * @return The selling price of the property as an integer.
     */
    public int getPrice() {
		return sellPrice;
	}

	/**
	 * Calculates and returns the rent owed for this property based on ownership and houses.
	 * @return The amount of rent to charge as an integer.
	 */
	/**
	 * Calculates and returns the rent owed for this property based on ownership and houses.
	 * @return The amount of rent to charge as an integer.
	 */
	public int getRent() {
		int rentToCharge = rent;
		String [] monopolies = theOwner.getMonopolies();
		rentToCharge = calculateMonopoliesRent(rentToCharge, monopolies);
		if(numHouses > 0) {
			rentToCharge = rent * (numHouses + 1);
		}
		return rentToCharge;
	}

	/**
	 * Calculates rent considering if the property’s color group is part of the owner’s monopolies, doubling rent if true.
	 * @param rentToCharge The base rent to be potentially adjusted.
	 * @param monopolies Array of color groups owned as monopolies by the property owner.
	 * @return The adjusted rent value after monopoly considerations as an integer.
	 */
	/**
	 * Calculates rent considering if the property’s color group is part of the owner’s monopolies, doubling rent if true.
	 * @param rentToCharge The base rent to be potentially adjusted.
	 * @param monopolies Array of color groups owned as monopolies by the property owner.
	 * @return The adjusted rent value after monopoly considerations as an integer.
	 */
	private int calculateMonopoliesRent(int rentToCharge, String[] monopolies) {
		for(int i = 0; i < monopolies.length; i++) {
			if(monopolies[i].equals(colorGroup)) {
				rentToCharge = rent * 2;
			}
		}
		return rentToCharge;
	}

	/**
	 * Executes the action when a player lands on the property, charging rent if owned by another player.
	 */
	/**
	 * Executes the action when a player lands on the property, charging rent if owned by another player.
	 */
	public void playAction() {
		Player currentPlayer = null;
		if(!isAvailable()) {
			currentPlayer = GameMaster.instance().getCurrentPlayer();
			if(theOwner != currentPlayer) {
				currentPlayer.payRentTo(theOwner, getRent());
			}
		}
	}

	/**
	 * Sets the color group of this property.
	 * @param colorGroup The color group to assign to this property.
	 */
	/**
	 * Sets the color group of this property.
	 * @param colorGroup The color group to assign to this property.
	 */
	public void setColorGroup(String colorGroup) {
		this.colorGroup = colorGroup;
	}

	/**
	 * Sets the cost to build a house on this property.
	 * @param housePrice The house price to set.
	 */
	/**
	 * Sets the cost to build a house on this property.
	 * @param housePrice The house price to set.
	 */
	public void setHousePrice(int housePrice) {
		this.housePrice = housePrice;
	}

	/**
	 * Sets the number of houses currently built on this property.
	 * @param numHouses The number of houses to set.
	 */
	/**
	 * Sets the number of houses currently built on this property.
	 * @param numHouses The number of houses to set.
	 */
	public void setNumHouses(int numHouses) {
		this.numHouses = numHouses;
	}

	/**
	 * Sets the selling price of this property.
	 * @param sellPrice The selling price to set.
	 */
	/**
	 * Sets the selling price of this property.
	 * @param sellPrice The selling price to set.
	 */
	public void setPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

	/**
	 * Sets the base rent amount for this property.
	 * @param rent The rent amount to set.
	 */
	/**
	 * Sets the base rent amount for this property.
	 * @param rent The rent amount to set.
	 */
	public void setRent(int rent) {
		this.rent = rent;
	}
}
