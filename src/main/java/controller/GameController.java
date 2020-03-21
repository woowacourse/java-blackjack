package controller;

import static java.util.stream.Collectors.*;

import domain.card.CardsFactory;
import domain.card.Deck;
import domain.result.Answer;
import domain.result.CardsResult;
import domain.gamer.Dealer;
import domain.result.GameResult;
import domain.gamer.Gamers;
import domain.gamer.Player;
import utils.InputUtils;
import view.InputView;
import view.OutputView;

public class GameController {
	public void run() {
		Gamers gamers = generateGamers();
		Deck deck = new Deck(CardsFactory.getCards());

		gamers.initCard(deck);
		OutputView.printInitCardGuide(gamers);
		OutputView.printGamersCard(gamers);
		addCardAtGamers(gamers, deck);
		OutputView.printCardsResultAndScore(new CardsResult(gamers));

		GameResult gameResult = gamers.generateGameResults();
		OutputView.printTotalEarningResult(gameResult);
	}

	private Gamers generateGamers() {
		return InputUtils.splitAsDelimiter(InputView.inputAsPlayerName())
			.stream()
			.map(name -> new Player(name, InputView.inputAsBettingMoney(name)))
			.collect(collectingAndThen(toList(), players -> new Gamers(players, new Dealer())));
	}

	private void addCardAtGamers(Gamers gamers, Deck deck) {
		addCardAtPlayers(gamers, deck);
		gamers.getDealer().addCardAtDealer(deck);
		OutputView.printAddCardAtDealer();
	}

	private void addCardAtPlayers(Gamers gamers, Deck deck) {
		gamers.getPlayers()
			.forEach(player -> drawCardOfPlayer(deck, player));
	}

	private void drawCardOfPlayer(Deck deck, Player player) {
		while (player.isDrawable()
			&& Answer.findAnswer(InputView.inputAsDrawable(player)).isYes()) {
			player.addCard(deck.popCard());
			OutputView.printGamerCard(player);
		}
	}
}
