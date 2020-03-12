package blackjack;

import java.util.List;

import blackjack.player.Dealer;
import blackjack.player.Player;
import blackjack.player.Players;
import blackjack.player.card.CardBundle;
import blackjack.player.card.CardFactory;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackGame {
	private static final String ACCEPT = "y";
	private final CardFactory cardFactory;
	private final InputView inputView;
	private final OutputView outputView;

	public BlackjackGame(CardFactory cardFactory, InputView inputView, OutputView outputView) {
		this.cardFactory = cardFactory;
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void run() {
		Player dealer = new Dealer(new CardBundle());
		Players players = new Players(makePlayers(dealer));

		drawStartingCards(players);
		if (dealer.isNotBurst()) {
			drawGambler(players);
			drawDealer(dealer);
		}
		outputView.showPlayersCards(players);
		outputView.showReports(players.getReports());
	}

	private List<Player> makePlayers(Player dealer) {
		List<Player> players = inputView.inputPlayNames()
			.toPlayers();
		players.add(dealer);
		return players;
	}

	private void drawStartingCards(Players players) {
		for (int i = 0; i < 2; i++) {
			players.drawCard(cardFactory);
		}
		outputView.showCards(players);
	}

	private void drawGambler(Players players) {
		for (Player player : players.findGamblers()) {
			drawEachGambler(player);
		}
	}

	private void drawEachGambler(Player gambler) {
		while (gambler.isDrawable() && inputView.inputDrawRequest(gambler).isDraw()) {
			gambler.addCard(cardFactory.drawCard());
			outputView.showCards(gambler);
		}
	}

	private void drawDealer(Player dealer) {
		while (dealer.isDrawable()) {
			dealer.addCard(cardFactory.drawCard());
			outputView.showDealerCard(dealer);
		}
	}
}
