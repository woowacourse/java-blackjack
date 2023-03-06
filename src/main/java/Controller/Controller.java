package Controller;

import static view.InputView.*;
import static view.OutputView.*;

import java.util.List;

import domain.BlackJack;
import domain.Player;
import domain.Users;

public class Controller {

	private BlackJack blackJack;

	public void run(){
		ready();
		play();
		end();
	}

	private void ready() {
		List<String> playerNames = askPlayerNames();
		Users players = Users.from(playerNames);
		blackJack = new BlackJack(players);
		printInitMessage(players);
		printDealerCardHidden(blackJack.getDealerCardHidden());
		printPlayerCards(blackJack.getPlayerToCard());
	}

	private void play() {
		for (Player player : blackJack.getPlayers()) {
			askPlayerHit(player);
		}
		giveCardToDealer();
	}

	private void askPlayerHit(final Player player) {
		while (player.isHittable() && askIfHit(player)) {
			blackJack.giveCard(player);
			printEachPlayerCards(player.getName(), player.getCardNames());
		}
	}

	private void giveCardToDealer() {
		while (blackJack.isDealerHittable()) {
			blackJack.giveCardToDealer();
			printDealerHitMessage();
		}
	}

	private void end() {
		printDealerCards(blackJack.getDealerCards(), blackJack.getDealerScore());
		printPlayerCards(blackJack.getPlayerToCard(), blackJack.getPlayerToScore());
		printGameResult(blackJack.calculateDealerResult(), blackJack.calculatePlayerResults());
	}
}
