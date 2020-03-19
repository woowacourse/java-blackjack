package controller;

import java.util.Arrays;

import domain.YesOrNo;
import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.money.Money;
import view.InputView;
import view.OutputView;
import view.dto.DealerDto;
import view.dto.PlayerDto;
import view.dto.PlayersDto;

/**
 *   class controller 클래스입니다.
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class Controller {
	private static final int INITIAL_DRAW_NUMBER = 2;

	public static void run() {
		Players players = new Players(InputView.inputPlayersName(),
			Arrays.asList(Money.of("10000"), Money.of("10000")));
		Dealer dealer = new Dealer();
		Deck deck = new Deck();

		initialize(players, dealer, deck);
		progress(players, dealer, deck);
		end(players, dealer);
	}

	private static void initialize(Players players, Dealer dealer, Deck deck) {
		try {
			initialDraw(players, dealer, deck);
			OutputView.printInitial(PlayersDto.from(players), DealerDto.from(dealer));
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e);
			initialize(players, dealer, deck);
		}
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
			OutputView.printCards(PlayerDto.from(player));
		}
	}

	private static void progressDealer(Dealer dealer, Deck deck) {
		while (dealer.canDraw()) {
			OutputView.printDealerDraw();
			dealer.draw(deck.deal());
		}
	}

	private static boolean isContinue(Player player) {
		return !player.isBust() && getYesOrNo(player).isYes();
	}

	private static YesOrNo getYesOrNo(Player player) {
		try {
			String choice = InputView.inputMoreCard(PlayerDto.from(player));
			return YesOrNo.getChoice(choice);
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e);
			return getYesOrNo(player);
		}
	}

	private static void end(Players players, Dealer dealer) {
		PlayersDto playersDto = PlayersDto.from(players);
		DealerDto dealerDto = DealerDto.from(dealer);

		OutputView.printResult(playersDto, dealerDto);
		OutputView.printMatchResult(playersDto, dealerDto);
	}
}
