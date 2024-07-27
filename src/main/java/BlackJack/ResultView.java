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
	
	
	
	public int findWin(Dealer dealer, Money money) {
		Calculate calculate;
		calculate = new Calculate(dealer.getCards());
		int dealerSum = calculate.sum();
		calculate = new Calculate(money.getPlayer().getCards());
		int playerSum = calculate.sum();
		
		if(dealerSum > 21) {
			return 0;
		}
		
		if(Math.abs(dealerSum - 21)< Math.abs(playerSum-21)) {
			
			return money.getMoney();
		}
		
		return 0;
	}
	
	
	public int dealerWinLoss(Dealer dealer, List<Money> dividends) {
		int total = 0;
		for(Money money : dividends) {
			total += findWin(dealer, money);
		}
		
		return total;
	}
	

	
	public void finalMoney(List<Money> dividends, Dealer dealer) {
		System.out.println("## 최종 수익");
		System.out.println(dealer.getName() + " : " + dealerWinLoss(dealer,dividends));
		for(Money m : dividends) {
			System.out.println(m.getName() + " : " + m.result(dealer, this));
		}
	}
	
}
