package BlackJack;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {
	
	public static void lessTwentyone(Calculate calculate,Deal deal, Player player, ResultView result) {
		if(calculate.sum()<21) {
			deal.dealOutCard(player);
			result.printOwnCard(player);
			System.out.println();
		}
	}

	public static void addCards(Calculate calculate,Deal deal, Player player, InputView input, ResultView result) {
		String answer;
		do {
			answer= input.getPlayerChoice(player.getName());
			lessTwentyone(calculate, deal, player, result);
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
		
	}

}
