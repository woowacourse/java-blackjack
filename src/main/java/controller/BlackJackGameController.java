package controller;

import java.util.Map;

import domain.Dealer;
import domain.Deck;
import domain.Player;
import domain.Players;
import domain.Result;
import domain.ResultType;
import view.InputView;
import view.OutputView;

public class BlackJackGameController {
	private final InputView inputView;
	private final OutputView outputView;

	public BlackJackGameController(InputView inputView, OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void gameStart() {
		Players players = new Players(inputView.inputPlayerNames());
		Dealer dealer = new Dealer();
		Deck deck = new Deck();
		dealer.addTwoCards(deck);
		players.addCardToAllPlayers(deck);
		outputView.displayFirstDistribution(players.getPlayers());
		outputView.displayOneCard(dealer.getCards().get(0));
		for (Player player : players.getPlayers()){
			outputView.displayAllCard(player.getName(), player.getCards());
		}
		for (Player player : players.getPlayers()) {
			while (true) {
				String decision = inputView.inputYesOrNo(player.getName());
				if (decision.equals("N") || decision.equals("n")) {
					break;
				}
				player.addCard(deck.distributeCard());

				if (player.isBurst()) {
					break;
				}
				outputView.displayAllCard(player.getName(), player.getCards());
			}
		}
		while (true) {
			if (!dealer.isHit()) {
				break;
			}
			outputView.displayDealerUnderSevenTeen();
			dealer.addCard(deck.distributeCard());
			if (dealer.isBurst()) {
				break;
			}
		}
		outputView.displayAllCardAndScore("딜러", dealer.getScore(), dealer.getCards());
		for (Player player : players.getPlayers()) {
			outputView.displayAllCardAndScore(player.getName(), player.getScore(), player.getCards());
		}
		Result result = new Result();
		Map<Player, ResultType> gameResult = result.getResult(players.getPlayers(), dealer);
		outputView.displayResult(gameResult);
	}

}
