import static view.InputView.*;
import static view.OutputView.*;

import java.util.List;

import domain.BlackJack;
import domain.Player;
import domain.Users;

public class Controller {

	private BlackJack blackJack;

	public void run() {
		try {
			ready();
			play();
			end();
		} catch (Exception e) {
			printErrorMessage(e.getMessage());
		}
	}

	private void ready() {
		List<String> playerNames = askPlayerNames();
		Users players = Users.from(playerNames);
		blackJack = BlackJack.of(players);
		printInitMessage(players);
		printDealerCardHidden(blackJack.getDealerCard());
		printPlayerCards(blackJack.getPlayerToCard());
	}

	private void play() {
		List<Player> hittablePlayers = blackJack.getHittablePlayers();
		for (Player player : hittablePlayers) {
			askPlayerHitCommand(player);
		}
		giveCardToDealer();
	}

	private void askPlayerHitCommand(final Player player) {
		while (player.isHittable()) {
			boolean isHit = askIfHit(player);
			if (isHit) {
				blackJack.giveCard(player.getName());
			}
			printEachPlayerCards(player.getName(), player.getCards());
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
