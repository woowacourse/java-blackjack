package blackjack.controller;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.DrawOpinion;
import blackjack.domain.card.Deck;
import blackjack.domain.result.Report;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {
	private static final int INITIAL_DRAW_NUMBER = 2;

	private final Deck deck;
	private final Dealer dealer;
	private final List<Player> players;

	public BlackjackController(Deck deck, Dealer dealer, List<Player> players) {
		this.deck = deck;
		this.dealer = dealer;
		this.players = players;
	}

	public void playGame() {
		List<User> users = generateUsers();
		drawInitialCardsEachUser(users);
		drawCardsEachUsers();
		printUsersCardsAndScore(users);

		Report blackJackReport = Report.from(dealer, players);
		OutputView.printBlackjackReport(blackJackReport);
	}

	private List<User> generateUsers() {
		List<User> users = new ArrayList<>();
		users.add(dealer);
		users.addAll(players);
		return users;
	}

	private void drawInitialCardsEachUser(List<User> users) {
		users.forEach(user -> user.draw(deck, INITIAL_DRAW_NUMBER));
		OutputView.printUsersInitialDraw(INITIAL_DRAW_NUMBER, users);
	}

	private void drawCardsEachUsers() {
		players.forEach(this::drawCardsByOpinion);
		drawCardsDealer();
	}

	private void drawCardsByOpinion(Player player) {
		while (player.canDraw()) {
			DrawOpinion drawOpinion = DrawOpinion.of(InputView.inputDrawOpinion(player));

			if (DrawOpinion.NO.equals(drawOpinion)) {
				break;
			}
			player.draw(deck);
			OutputView.printUserHand(player, player.getHand());
		}
	}

	private void drawCardsDealer() {
		while (dealer.canDraw()) {
			dealer.draw(deck);
			OutputView.printDealerDrawCard();
		}
	}

	private void printUsersCardsAndScore(List<User> users) {
		for (User user : users) {
			OutputView.printUserHandAndScore(user);
		}
	}
}
