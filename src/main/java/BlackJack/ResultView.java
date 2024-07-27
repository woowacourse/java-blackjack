package BlackJack;

import java.util.List;

public class ResultView {

	public void printCard(Card card) {
		for (int i = 0; i < card.getCard().size()-1; i++) {
            System.out.print(card.getCard().get(i)+", ");
        }
		System.out.println(card.getCard().get(card.getCard().size()-1));
	}
	
	public void printFirst(String names) {
		System.out.println("딜러와 " + names +"에게 2장을 나누었습니다.");
	}
	
	public void printOwnCard(Player own) {
		System.out.print(own.getName() + ":");
		printCard(own.getCards());
	}
	
	public void printPlayerCard(Dealer dealer, List<Player> players) {
		printOwnCard(dealer);
		for(Player player : players) {
			printOwnCard(player);
		}
			
	}
		
	
}
