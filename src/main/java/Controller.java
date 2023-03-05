import java.util.List;

import domain.BlackJack;
import domain.Player;
import domain.Users;
import view.InputView;
import view.OutputView;

public class Controller {

	private final InputView inputView;
	private final OutputView outputView;
	private BlackJack blackJack;

	public Controller(InputView inputView, OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void run() {
		try {
			ready();
			play();
			end();
		} catch (Exception e) {
			outputView.printErrorMessage(e.getMessage());
		}
	}

	private void ready() {
		List<String> playerNames = inputView.askPlayerNames();
		Users players = Users.from(playerNames);
		blackJack = BlackJack.of(players);
		outputView.printInitMessage(players);
		outputView.printDealerCardHidden(blackJack.getDealerCard());
		outputView.printPlayerCards(blackJack.getPlayerToCard());
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
			boolean isHit = inputView.askIfHit(player);
			if (isHit) {
				blackJack.giveCard(player.getName());
			}
			outputView.printEachPlayerCards(player.getName(), player.getCards());
		}
	}

	private void giveCardToDealer() {
		while (blackJack.isDealerHittable()) {
			blackJack.giveCardToDealer();
			outputView.printDealerHitMessage();
		}
	}

	private void end() {
		outputView.printDealerCards(blackJack.getDealerCards(), blackJack.getDealerScore());
		outputView.printPlayerCards(blackJack.getPlayerToCard(), blackJack.getPlayerToScore());
		outputView.printGameResult(blackJack.calculateDealerResult(), blackJack.calculatePlayerResults());
	}
}
