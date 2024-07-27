package BlackJack;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {

	public static void main(String[] args) {
		
		String playerNamesInput = InputView.getPlayerNames();
		String[] playerNames = playerNamesInput.split(",");
		
		List<Player> players = new ArrayList<>();
		  for(String name : playerNames) {
			  players.add(new Player(name));
		}
		  
		Dealer dealer = new Dealer();
		Deal deal = new Deal(players, dealer);
		
		System.out.println(deal.PickCards(dealer));

	}

}
