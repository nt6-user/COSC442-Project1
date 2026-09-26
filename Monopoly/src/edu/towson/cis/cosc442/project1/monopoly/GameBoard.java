package edu.towson.cis.cosc442.project1.monopoly;

import java.util.ArrayList;
import java.util.Hashtable;

public class GameBoard {

	private ArrayList<Cell> cells = new ArrayList<Cell>();
    private ArrayList<Card> chanceCards = new ArrayList<Card>();
	//the key of colorGroups is the name of the color group.
	private Hashtable<String, Integer> colorGroups = new Hashtable<String, Integer>();
	private ArrayList<Card> communityChestCards = new ArrayList<Card>();
	/**
	 * Initializes the game board and adds the starting Go cell.
	 */
	/**
	 * Initializes the game board and adds the starting Go cell.
	 */
	public GameBoard() {
		Cell go = new GoCell();
		addCell(go);
	}

    /**
     * Adds a Card to the appropriate deck based on its type (Chance or Community Chest).
     * @param card the Card object to be added
     */
    /**
     * Adds a Card to the appropriate deck based on its type (Chance or Community Chest).
     * @param card the Card object to be added
     */
    /** 
	 * @param card
	 */
	public void addCard(Card card) {
        if(card.getCardType() == Card.TYPE_CC) {
            communityChestCards.add(card);
        } else {
            chanceCards.add(card);
        }
    }
	
	/**
	 * Adds a general Cell to the game board.
	 * @param cell the Cell object to be added
	 */
	/**
	 * Adds a general Cell to the game board.
	 * @param cell the Cell object to be added
	 */
	/** 
	 * @param cell
	 */
	public void addCell(Cell cell) {
		cells.add(cell);
	}
	
	/**
	 * Adds a PropertyCell to the game board and updates the count of properties for its color group.
	 * @param cell the PropertyCell object to be added
	 */
	/**
	 * Adds a PropertyCell to the game board and updates the count of properties for its color group.
	 * @param cell the PropertyCell object to be added
	 */
	/** 
	 * @param cell
	 */
	public void addCell(PropertyCell cell) {
		String colorGroup = cell.getColorGroup();
		int propertyNumber = getPropertyNumberForColor(colorGroup);
		colorGroups.put(colorGroup, propertyNumber + 1);
        cells.add(cell);
	}

    /**
     * Draws the top Community Chest card, returns it, then places it at the bottom of the deck.
     * @return the drawn Community Chest Card
     */
    /**
     * Draws the top Community Chest card, returns it, then places it at the bottom of the deck.
     * @return the drawn Community Chest Card
     */
    /** 
	 * @return Card
	 */
	public Card drawCCCard() {
        Card card = (Card)communityChestCards.get(0);
        communityChestCards.remove(0);
        addCard(card);
        return card;
    }

    /**
     * Draws the top Chance card, returns it, then places it at the bottom of the deck.
     * @return the drawn Chance Card
     */
    /**
     * Draws the top Chance card, returns it, then places it at the bottom of the deck.
     * @return the drawn Chance Card
     */
    /** 
	 * @return Card
	 */
	public Card drawChanceCard() {
        Card card = (Card)chanceCards.get(0);
        chanceCards.remove(0);
        addCard(card);
        return card;
    }

	/**
	 * Returns the Cell at the specified index on the board.
	 * @param newIndex the index of the desired Cell
	 * @return the Cell at the specified index
	 */
	/**
	 * Returns the Cell at the specified index on the board.
	 * @param newIndex the index of the desired Cell
	 * @return the Cell at the specified index
	 */
	/** 
	 * @param newIndex
	 * @return Cell
	 */
	public Cell getCell(int newIndex) {
		return (Cell)cells.get(newIndex);
	}
	
	/**
	 * Returns the total number of cells present on the game board.
	 * @return the number of cells on the board
	 */
	/**
	 * Returns the total number of cells present on the game board.
	 * @return the number of cells on the board
	 */
	/** 
	 * @return int
	 */
	public int getCellNumber() {
		return cells.size();
	}
	
	/**
	 * Returns an array of all PropertyCells belonging to a specified color group (monopoly).
	 * @param color the color group name to query
	 * @return an array of PropertyCells in the specified monopoly group
	 */
	/**
	 * Returns an array of all PropertyCells belonging to a specified color group (monopoly).
	 * @param color the color group name to query
	 * @return an array of PropertyCells in the specified monopoly group
	 */
	/** 
	 * @param color
	 * @return PropertyCell[]
	 */
	public PropertyCell[] getPropertiesInMonopoly(String color) {
		PropertyCell[] monopolyCells = 
			new PropertyCell[getPropertyNumberForColor(color)];
		int counter = 0;
		for (int i = 0; i < getCellNumber(); i++) {
			Cell c = getCell(i);
			if(c instanceof PropertyCell) {
				PropertyCell pc = (PropertyCell)c;
				if(pc.getColorGroup().equals(color)) {
					monopolyCells[counter] = pc;
					counter++;
				}
			}
		}
		return monopolyCells;
	}
	
	/**
	 * Returns the count of properties for the given color group.
	 * @param name the name of the color group
	 * @return the number of properties in the color group
	 */
	/**
	 * Returns the count of properties for the given color group.
	 * @param name the name of the color group
	 * @return the number of properties in the color group
	 */
	/** 
	 * @param name
	 * @return int
	 */
	public int getPropertyNumberForColor(String name) {
		Integer number = (Integer)colorGroups.get(name);
		if(number != null) {
			return number.intValue();
		}
		return 0;
	}

	/**
	 * Searches for and returns the Cell with the specified name.
	 * @param string the name of the cell to find
	 * @return the Cell matching the name or null if not found
	 */
	/**
	 * Searches for and returns the Cell with the specified name.
	 * @param string the name of the cell to find
	 * @return the Cell matching the name or null if not found
	 */
	/** 
	 * @param string
	 * @return Cell
	 */
	public Cell queryCell(String string) {
		for(int i = 0; i < cells.size(); i++){
			Cell temp = (Cell)cells.get(i); 
			if(temp.getName().equals(string)) {
				return temp;
			}
		}
		return null;
	}
	
	/**
	 * Returns the index of the Cell with the specified name.
	 * @param string the name of the cell to find
	 * @return the index of the matching Cell or -1 if not found
	 */
	/**
	 * Returns the index of the Cell with the specified name.
	 * @param string the name of the cell to find
	 * @return the index of the matching Cell or -1 if not found
	 */
	/** 
	 * @param string
	 * @return int
	 */
	public int queryCellIndex(String string){
		for(int i = 0; i < cells.size(); i++){
			Cell temp = (Cell)cells.get(i); 
			if(temp.getName().equals(string)) {
				return i;
			}
		}
		return -1;
	}

    /**
     * Removes all Community Chest cards from the deck by clearing it.
     */
    /**
     * Removes all Community Chest cards from the deck by clearing it.
     */
    public void removeCards() {
        communityChestCards.clear();
    }
}
