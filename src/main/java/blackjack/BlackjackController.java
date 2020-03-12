package blackjack;

import java.util.List;

import blackjack.card.CardBundle;
import blackjack.card.CardFactory;
import blackjack.player.Dealer;
import blackjack.player.Player;
import blackjack.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {
	private final CardFactory cardFactory;
	private final InputView inputView;

	public BlackjackController(CardFactory cardFactory, InputView inputView) {
		this.cardFactory = cardFactory;
		this.inputView = inputView;
	}

	public void run() {
		Player dealer = new Dealer(new CardBundle());
		Players players = new Players(makePlayers(dealer));

		drawStartingCards(players);
		if (dealer.isNotBurst()) {
			drawGambler(players);
			drawDealer(dealer);
		}

		OutputView.showReports(players);
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
		OutputView.showCards(players);
	}

	private void drawGambler(Players players) {
		for (Player player : players.findGamblers()) {
			drawEachGambler(player);
		}
	}

	private void drawEachGambler(Player gambler) {
		while (gambler.isDrawable() && inputView.inputDrawRequest(gambler).isDraw()) {
			gambler.addCard(cardFactory.drawCard());
			OutputView.showCardInfo(gambler);
		}
	}

	private void drawDealer(Player dealer) {
		while (dealer.isDrawable()) {
			dealer.addCard(cardFactory.drawCard());
			OutputView.showDealerDrawMessage();
		}
	}
}
