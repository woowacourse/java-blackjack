package blackjack;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.player.Dealer;
import blackjack.player.Gambler;
import blackjack.player.Player;
import blackjack.player.Players;
import blackjack.player.card.CardBundle;
import blackjack.player.card.CardFactory;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackGame {
	private final CardFactory cardFactory;
	private final InputView inputView;
	private final OutputView outputView;

	public BlackjackGame(CardFactory cardFactory, InputView inputView, OutputView outputView) {
		this.cardFactory = cardFactory;
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void run() {
		List<Player> players = inputView.inputPlayNames().stream()
			.map(name -> new Gambler(new CardBundle(), name))
			.collect(Collectors.toList());
		players.add(new Dealer(new CardBundle()));
		Players players2 = new Players(players);

		for (int i = 0; i < 2; i++) {
			players2.drawCard(cardFactory);
		}
		outputView.showCards(players2);

		if (players2.isDealerBlackjack()) {
			List<GameReport> gameReports = players2.getReports();
			outputView.showReports(gameReports);
			return;
		}

		for (Player player : players2.findGamblers()) {
			while (!player.isBurst() && "y".equals(inputView.inputDrawRequest())) {
				player.playerDrawCard();
				outputView.showCards(player);
			}
		}

		while (players2.canDraw()) {
			players2.dealerDrawCard();
			outputView.showDealerCard(players2);
		}

		List<GameReport> gameReports = players2.getReports();
		outputView.showReports(gameReports);
	}
}
