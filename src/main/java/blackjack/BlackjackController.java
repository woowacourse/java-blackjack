package blackjack;

import java.util.ArrayList;
import java.util.List;

import blackjack.card.domain.CardBundle;
import blackjack.card.domain.CardDeck;
import blackjack.player.domain.Dealer;
import blackjack.player.domain.Player;
import blackjack.player.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {
	private final CardDeck cardDeck;
	private final InputView inputView;

	public BlackjackController(CardDeck cardDeck, InputView inputView) {
		this.cardDeck = cardDeck;
		this.inputView = inputView;
	}

	public void run() {
		Player dealer = new Dealer(new CardBundle());
		Players players = new Players(makePlayers(dealer));

		drawStartingCards(players);
		if (dealer.isNotBlackjack()) {
			drawGambler(players);
			drawDealer(dealer);
		}

		OutputView.showReports(players);
	}

	private List<Player> makePlayers(Player dealer) {
		List<Player> players = new ArrayList<>();
		players.add(dealer);
		players.addAll(inputView.inputPlayNames()
			.toPlayers());
		return players;
	}

	private void drawStartingCards(Players players) {
		for (int i = 0; i < 2; i++) {
			players.drawCard(cardDeck);
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
			gambler.addCard(cardDeck.drawCard());
			OutputView.showCardInfo(gambler);
		}
	}

	private void drawDealer(Player dealer) {
		while (dealer.isDrawable()) {
			dealer.addCard(cardDeck.drawCard());
			OutputView.showDealerDrawMessage();
		}
	}
}
