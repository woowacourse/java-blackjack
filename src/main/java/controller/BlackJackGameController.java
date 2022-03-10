package controller;

import java.util.List;
import java.util.Map;

import domain.Dealer;
import domain.Deck;
import domain.Player;
import domain.Players;
import domain.Result;
import domain.ResultType;
import view.InputView;

public class BlackJackGameController {
	private final InputView inputView;

	public BlackJackGameController(InputView inputView) {
		this.inputView = inputView;
	}

	public void gameStart() {
		Players players = new Players(inputView.inputPlayerNames());
		Dealer dealer = new Dealer();
		Deck deck = new Deck();
		dealer.addTwoCards(deck);
		players.addCardToAllPlayers(deck);
		for (Player player : players.getPlayers()) {
			while (true) {
				String decision = inputView.inputYesOrNo();
				if (decision.equals("N") || decision.equals("n")) {
					break;
				}
				player.addCard(deck.distributeCard());
				if (player.isBurst()) {
					break;
				}
				if (player.hasAce()) {
					player.calculateAceSum();
				}
			}
		}
		while (true) {
			if (!dealer.isHit()) {
				break;
			}
			dealer.addCard(deck.distributeCard());
			if (dealer.isBurst()) {
				break;
			}
			if (dealer.hasAce()) {
				dealer.calculateAceSum();
			}
		}
		Result result = new Result();
		Map<Player, ResultType> result1 = result.getResult(players.getPlayers(), dealer);

	}

}
