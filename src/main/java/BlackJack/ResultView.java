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
	public void findResult(Player player) {
		Calculate calculate;
		System.out.print(player.getName() + "카드 : ");
		printCard(player.getCards());
		calculate = new Calculate(player.getCards());
		System.out.println(" - 결과 : " + calculate.sum());
	}
	
	
	public void printResult(Dealer dealer, List<Player> players) {
		findResult(dealer);
		for(Player player : players) {
			findResult(player);
		}
	}
	
	
	
	public int findWin(Dealer dealer, Player player) {
		Calculate calculate;
		calculate = new Calculate(dealer.getCards());
		int dealerSum = calculate.sum();
		calculate = new Calculate(player.getCards());
		int playerSum = calculate.sum();
		
		if(Math.abs(dealerSum - 21)< Math.abs(playerSum-21)) {
			return 1;
		}
		
		return 0;
	}
	
	public void printWinLoss(Dealer dealer, Player player) {
		if(findWin(dealer, player)== 0) {
			System.out.println(player.getName() + " : 승");
		}
		if(findWin(dealer, player)== 1) {
			System.out.println(player.getName() + " : 패");
		}
	}
	
	public int dealerWinLoss(Dealer dealer, List<Player> players) {
		int total = 0;
		for(Player player : players) {
			total += findWin(dealer, player);
		}
		
		return total;
	}
	
	public void finalWinOrLoss(Dealer dealer, List<Player> players) {
		int win = dealerWinLoss(dealer, players);
		int loss = players.size()- dealerWinLoss(dealer, players);
		System.out.println("## 최종 승패");
		System.out.println(dealer.getName() + " : " + win + "승 " + loss + "패");
		
		for(Player player : players) {
			printWinLoss(dealer, player);
		}
	}
	
}
