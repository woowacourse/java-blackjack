package controller;

import java.util.ArrayList;
import java.util.List;

import domain.GameResult;
import domain.YesOrNo;
import domain.card.Deck;
import domain.exception.InvalidChoiceException;
import domain.gamer.Dealer;
import domain.gamer.Name;
import domain.gamer.Names;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.money.Money;
import view.InputView;
import view.OutputView;
import view.dto.GamerDto;
import view.dto.PlayersDto;

/**
 *   class controller 클래스입니다.
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class Controller {
	private static final int INITIAL_DRAW_NUMBER = 2;

	public static void run() {
		Players players = createPlayers();
		Dealer dealer = new Dealer();
		Deck deck = new Deck();

		initialize(players, dealer, deck);
		progress(players, dealer, deck);
		end(players, dealer);
	}

	private static Players createPlayers() {
		try {
			Names names = inputPlayersName();
			return new Players(names, inputBettingMoneys(names.getNames()));
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e);
			return createPlayers();
		}
	}

	private static Names inputPlayersName() {
		try {
			return new Names(InputView.inputPlayersName());
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e);
			return inputPlayersName();
		}
	}

	private static List<Money> inputBettingMoneys(List<Name> playersName) {
		List<Money> moneys = new ArrayList<>();

		for (Name name : playersName) {
			moneys.add(askBettingMoney(name));
		}
		return moneys;
	}

	private static Money askBettingMoney(Name name) {
		try {
			OutputView.printInputBettingMoney(name);
			return Money.of(InputView.inputBettingMoney());
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e);
			return askBettingMoney(name);
		}
	}

	private static void initialize(Players players, Dealer dealer, Deck deck) {
		initialDraw(players, dealer, deck);
		OutputView.printInitial(PlayersDto.from(players), GamerDto.from(dealer));
	}

	private static void initialDraw(Players players, Dealer dealer, Deck deck) {
		for (int i = 0; i < INITIAL_DRAW_NUMBER; i++) {
			dealer.draw(deck.deal());
			players.draw(deck);
		}
	}

	private static void progress(Players players, Dealer dealer, Deck deck) {
		if (dealer.isBlackjack()) {
			OutputView.printDealerBlackjack();
			return;
		}
		progressPlayers(players, deck);
		progressDealer(dealer, deck);
	}

	private static void progressPlayers(Players players, Deck deck) {
		for (Player player : players.getPlayers()) {
			askMoreCard(player, deck);
		}
	}

	private static void askMoreCard(Player player, Deck deck) {
		if (player.isBlackjack()) {
			return;
		}
		while (isContinue(player)) {
			player.draw(deck.deal());
			OutputView.printCards(GamerDto.from(player));
		}
	}

	private static void progressDealer(Dealer dealer, Deck deck) {
		while (dealer.canHit()) {
			OutputView.printDealerDraw();
			dealer.draw(deck.deal());
		}
	}

	private static boolean isContinue(Player player) {
		return !player.isBust() && getYesOrNo(player).isYes();
	}

	private static YesOrNo getYesOrNo(Player player) {
		try {
			String choice = InputView.inputMoreCard(GamerDto.from(player));
			return YesOrNo.getChoice(choice);
		} catch (InvalidChoiceException e) {
			OutputView.printErrorMessage(e);
			return getYesOrNo(player);
		}
	}

	private static void end(Players players, Dealer dealer) {
		players.changeProfitStrategy(dealer.scoreHands());
		GameResult gameResult = GameResult.of(players, dealer);
		PlayersDto playersDto = PlayersDto.from(players);
		GamerDto dealerDto = GamerDto.from(dealer);

		OutputView.printResult(playersDto, dealerDto);
		OutputView.printMatchResult(gameResult);
	}
}
