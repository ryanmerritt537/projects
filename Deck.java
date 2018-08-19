package ch6projects;

public class Deck {
	
	private Card[] deck;
	
	
	public Deck(){
		initDeck();
	}
	
	//Initializes the deck of cards with appropriate suite and value
	public void initDeck(){
		deck = new Card[52];
		for(int i = 0; i < deck.length/4; i++){
			deck[i] = new Card("club", i);
		}
		for(int i = deck.length/4; i < deck.length/2; i++){
			deck[i] = new Card("diamond", i);
		}
		for(int i = deck.length/2; i < deck.length * (3/4); i++){
			deck[i] = new Card("heart", i);
		}
		for(int i = deck.length * (3/4); i < deck.length; i++){
			deck[i] = new Card("spade", i);
		}
	}
	
	//Randomizes the current deck. Think of various algorithms to do this. 
	//Perfect shuffle?
	public void shuffle(){
		//some cards will be swapped twice or even
		//swapped to the same place with this code 
		//but that means it is completely random.
		int rnd = (int)(Math.random() * 52) + 1;
		for(int i = 0; i < deck.length; i++){
			Card temp = new Card(deck[rnd].getSuit(), deck[rnd].getFaceValue());
			deck[rnd] = new Card(deck[i].getSuit(), deck[i].getFaceValue());
			deck[i] = new Card(temp.getSuit(), temp.getFaceValue());
			rnd = (int)(Math.random() * 52) + 1;
		}
	}
	
	// will deal numCards number of cards from the deck and update deck accordingly
	public void deal(int numCards){
		for(int i = deck.length-1; i >= deck.length-numCards-1; i--){
			Card[] temp = new Card[deck.length-numCards-1];
			for(int j = 0; j < temp.length; j ++){
				temp[j] = deck[j];
			}
			deck = temp;
		}
	}
	
	//Should display the current deck using Card's to String method
	public void displayDeck(){
		for(int i = 1; i < deck.length + 1; i ++){
			s.p("Card #"+i+" of the deck: "+deck[i]);
		}
		
	}

}