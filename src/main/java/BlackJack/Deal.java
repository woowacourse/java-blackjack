package BlackJack;

import java.util.List;
import java.util.Random;

public class Deal {
	
	private List<Player> players;
	private Dealer dealer;
	private String[] shape= {"하트", "다이아몬드", "클로버", "스페이드"};
	private String[] rank = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	private Card Allcards;
	private Card main = new Card();
	
	public Deal(List<Player> players, Dealer dealer) {
		this.players = players;
		this.dealer = dealer;
	}
	
	public String PickCards(Player player) {
		Random random = new Random();
		int PickShape;
		PickShape = random.nextInt(shape.length);
		
		int PickRank;
		PickRank = random.nextInt(rank.length);
		
		//테스트를 위한 코드
		//PickShape = 0;
		//PickRank = 0;
		
		StringBuilder c = new StringBuilder();
		c.append(shape[PickShape]);
		c.append(rank[PickRank]);
		
		String card = c.toString();
		
		
		
		return card;
	}
	
	 public boolean checkSameCard(String card, Card cards) {
	        return cards.getCard().contains(card);
	    }
	
	public String dealOutCard(Player player) {
		String card = PickCards(player);
		while(checkSameCard(card, player.getCards())||checkSameCard(card, main)) {
			card = PickCards(player);
		}
		player.setCards(card);
		main.addCard(card);
		
		return card;
	}
	
}


