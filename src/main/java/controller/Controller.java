package controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import domain.Card;
import domain.Dealer;
import domain.Deck;
import domain.InitCards;
import domain.Players;
import view.InputView;
import view.OutputView;

public class Controller {

	public void run() {
		String[] split = InputView.inputNames().split(",");
		List<String> names = Arrays.stream(split).map(String::trim).collect(Collectors.toList());

		Deck deck = new Deck();
		Dealer dealer = new Dealer(new InitCards(deck).getIntiCards());
		List<List<Card>> initCardForPlayers = IntStream.range(0, names.size())
			.mapToObj(i -> new InitCards(deck).getIntiCards())
			.collect(Collectors.toList());
		Players players = new Players(names, initCardForPlayers);

		OutputView.printHand(names, dealer.showOneHand(), players.showHands());
	}
}
