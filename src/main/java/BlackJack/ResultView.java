package BlackJack;

import java.util.List;

public class ResultView {

	public void printCard(Card card) {
		for (int i = 0; i < card.getCard().size()-1; i++) {
            System.out.print(card.getCard().get(i)+", ");
        }
		int n =card.getCard().size()-1;
		System.out.print(card.getCard().get(n));
	}
	
	public void printFirst(String names) {
		System.out.println("\n딜러와 " + names +"에게 2장을 나누었습니다.");
	}
	
	public void printOwnCard(Player own) {
		System.out.print(own.getName() + ":");
		printCard(own.getCards());
	}
	
	public void printPlayerCard(Dealer dealer, List<Player> players) {
		printOwnCard(dealer);
		System.out.println();
		for(Player player : players) {
			printOwnCard(player);
			System.out.println();
		}
			
	}
	
	public void addDealer(Dealer dealer, Deal deal) {
		Calculate calculate = new Calculate(dealer.getCards());
		if(dealer.over(calculate, deal)) {
			System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
		}
	}
	
}
