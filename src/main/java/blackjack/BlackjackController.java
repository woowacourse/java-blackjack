package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import blackjack.card.domain.CardBundle;
import blackjack.card.domain.CardDeck;
import blackjack.player.domain.Dealer;
import blackjack.player.domain.Gambler;
import blackjack.player.domain.Player;
import blackjack.player.domain.Players;
import blackjack.player.domain.component.Money;
import blackjack.player.domain.component.PlayerInfo;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {
	private final CardDeck cardDeck;
	private final InputView inputView;

	public BlackjackController(CardDeck cardDeck, InputView inputView) {
		Objects.requireNonNull(cardDeck, "카드덱이 존재하지 않습니다.");
		this.cardDeck = cardDeck;
		this.inputView = inputView;
	}

	public void run() {
		List<String> names = receivePlayerNames();
		Dealer dealer = new Dealer(new CardBundle());
		Players players = createPlayers(names, dealer);

		drawStartingCards(players);
		turnGamblers(players);
		turnDealer(dealer);

		OutputView.showReports(players);
	}

	private List<String> receivePlayerNames() {
		return inputView.inputPlayNames();
	}

	private Players createPlayers(List<String> names, Dealer dealer) {
		List<Player> players = new ArrayList<>();
		players.add(dealer);
		for (String name : names) {
			Money bettingMoney = Money.create(inputView.inputBettingMoney(name));
			Gambler gambler = new Gambler(new CardBundle(), new PlayerInfo(name, bettingMoney));
			players.add(gambler);
		}
		return new Players(players);
	}

	private void drawStartingCards(Players players) {
		players.drawStartingCard(cardDeck);
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
