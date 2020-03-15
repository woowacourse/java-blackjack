package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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

	//todo: 검증로직 구현
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
		if (BlackjackRule.checkEarlyTermination(dealer)) {
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
		BlackjackRule.drawStartingCard(players, cardDeck);
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
			Consumer<CardDeck> c = cardDeck1 -> cardDeck.drawCard();
			c.accept(cardDeck);
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
