package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import blackjack.card.domain.CardBundle;
import blackjack.card.domain.CardDeck;
import blackjack.player.domain.Dealer;
import blackjack.player.domain.Gambler;
import blackjack.player.domain.Player;
import blackjack.player.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {
	private final CardDeck cardDeck;
	private final InputView inputView;

	public BlackjackController(CardDeck cardDeck, InputView inputView) {
		checkCardDeck(cardDeck);
		this.cardDeck = cardDeck;
		this.inputView = inputView;
	}

	private void checkCardDeck(CardDeck cardDeck) {
		if (cardDeck == null) {
			throw new IllegalArgumentException("카드덱이 유효하지 않습니다.");
		}
	}

	public void run() {
		Player dealer = new Dealer(new CardBundle());
		Players players = new Players(makePlayers(dealer));

		drawStartingCards(players);
		turnGamblers(players);
		turnDealer(dealer);

		OutputView.showReports(players);
	}

	private List<Player> makePlayers(Player dealer) {
		List<Player> players = new ArrayList<>();
		List<Player> gamblers = inputView.inputPlayNames().toPlayers();
		players.add(dealer);
		players.addAll(gamblers);
		return players;
	}

	private void drawStartingCards(Players players) {
		BlackjackRule.drawStartingCard(players, cardDeck);
		OutputView.showCards(players);
	}

	private void turnGamblers(Players players) {
		for (Player player : players.findGamblers()) {
			turnGambler(player);
		}
	}

	private void turnGambler(Player gambler) {
		Consumer<Gambler> consumer = OutputView::showCardInfo;
		while (gambler.isHit() && inputView.inputDrawRequest(gambler).isDraw()) {
			gambler.addCard(cardDeck.draw(), consumer);
			// OutputView.showCardInfo(gambler);
		}
	}

	private void turnDealer(Player dealer) {
		while (dealer.isHit()) {
			dealer.addCard(cardDeck.draw(), OutputView::showDealerDrawMessage);
			// OutputView.showDealerDrawMessage();
		}
	}
}
