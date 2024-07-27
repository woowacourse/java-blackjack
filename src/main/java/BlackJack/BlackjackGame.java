package BlackJack;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {

	public static void addCards(Deal deal, Player player, InputView input, ResultView result) {
		String answer = input.getPlayerChoice(player.getName());
		while(answer.equals("y")){
			deal.dealOutCard(player);
			result.printOwnCard(player);
			answer = input.getPlayerChoice(player.getName());
		}
		result.printCard(player.getCards());
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

		for(Player player : players) {
			addCards(deal, player, input, result);
			
		}
		
		
		
	}

}
