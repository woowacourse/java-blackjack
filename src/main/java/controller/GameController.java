package controller;

import static java.util.stream.Collectors.*;

import java.util.List;

import domain.card.CardFactory;
import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import utils.InputUtils;
import view.InputView;
import view.OutputView;

public class GameController {
	public void run() {
		Gamers gamers = new Gamers(GeneratePlayers(), new Dealer());
		Deck deck = new Deck(CardFactory.create());

		gamers.initCard(deck);

        OutputView.printInitCardGuide(gamers);
        OutputView.printPlayer(gamers);
	}

	private List<Player> GeneratePlayers() {
		return InputUtils.splitAsDelimiter(InputView.inputAsPlayerName())
			.stream()
			.map(Player::new)
			.collect(toList());
	}
}
