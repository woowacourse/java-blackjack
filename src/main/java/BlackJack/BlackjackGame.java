package BlackJack;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {
	
	public static int findBlackJack(Dealer dealer, Money m) {
		Calculate c;
		c = new Calculate(dealer.getCards());
		int dealerSum = c.sum();
		
		c = new Calculate(m.getPlayer().getCards());
		int playerSum = c.sum();

		if(dealerSum == 21 && playerSum == 21) {
			m.setResult(m.getMoney());
			return 0;
		}
		if(dealerSum != 21 && playerSum == 21) {
			m.setResult((int)(m.getMoney()*1.5));
			return 1;
		}
		return 0;
		
	}
	
	public static void lessTwentyone(String answer,Calculate calculate,Deal deal, Player player, ResultView result) {
		if(calculate.sum()<21&&answer.equals("y")) {
			deal.dealOutCard(player);
		}
	}

	public static void addCards(Calculate calculate,Deal deal, Player player, InputView input, ResultView result) {
		String answer;
		do {
			answer= input.getPlayerChoice(player.getName());
			lessTwentyone(answer,calculate, deal, player, result);
			result.printOwnCard(player);
			System.out.println();
		}while(answer.equals("y")&&calculate.sum()<21);
		
	}

	public static void main(String[] args) {
		
		InputView input = new InputView();
		String playerNamesInput = input.getPlayerNames();
		String[] playerNames = playerNamesInput.split(",");
		
		List<Player> players = new ArrayList<>();
		  for(String name : playerNames) {
			  players.add(new Player(name));
		}
		  
		List<Money> dividends = new ArrayList<>();
		for(Player player : players) {
			int n=input.getMoney(player);
			dividends.add(new Money(player,n));
		}
		  
		Dealer dealer = new Dealer();
		Deal deal = new Deal(players, dealer);
		
		ResultView result = new ResultView();
		
		result.printFirst(playerNamesInput);
		deal.dealOutCard(dealer);
		deal.dealOutCard(dealer);
		for(Player player : players) {
			deal.dealOutCard(player);
			deal.dealOutCard(player);
		}
		
		
		result.printPlayerCard(dealer, players);
		System.out.println();

		int bj = 0;
		for(Money m  : dividends) {
			bj += findBlackJack(dealer, m);
		}
		if(bj!=0) {
			result.printResult(dealer, players);
			result.finalMoney(dividends, dealer);
			return;
		}
		
		for(Player player : players) {
			Calculate calculate = new Calculate(player.getCards());
			System.out.println();
			addCards(calculate, deal, player, input, result);
			
		}
		
		System.out.println();
		result.addDealer(dealer, deal);
		System.out.println();
		
		result.printResult(dealer, players);
		
		/* 1단계 
		result.finalWinOrLoss(dealer, players);
		*/
		
		result.finalMoney(dividends, dealer);
	}

}
