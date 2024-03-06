package controller;

import java.util.ArrayList;
import java.util.List;

import domain.Card;
import domain.Dealer;
import domain.Player;
import domain.Players;
import domain.Rank;
import domain.Suit;
import view.InputView;
import view.OutputView;

public class BlackjackController {
	private final InputView inputView;
	private final OutputView outputView;

	public BlackjackController(InputView inputView, OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public Players createPlayers() {
		return new Players(inputView.readPlayerNames());
	}

	public Dealer createDealer() {
		List<Card> deck = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				deck.add(new Card(suit, rank));
			}
		}

		return new Dealer(deck, new ArrayList<>());
	}

	public void dealInitCards(Dealer dealer, Players players) {
		for (Player player : players.getPlayers()) {
			player.receiveInitCards(dealer.dealInit());
		}
		dealer.receiveInitCards(dealer.dealInit());

		outputView.printInitCardStatus(dealer, players);
	}
}
