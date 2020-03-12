package blackjack.controller;

import java.util.List;

import blackjack.domain.DrawOpinion;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {
	private static final int INITIAL_DRAW_NUMBER = 2;

	private final Deck deck;
	private final List<User> users;

	public BlackjackController(Deck deck, List<User> users) {
		this.deck = deck;
		this.users = users;
	}

	public void playGame() {
		users.forEach(user -> user.draw(deck, INITIAL_DRAW_NUMBER));
		OutputView.printUsersInitialDraw(INITIAL_DRAW_NUMBER, users);

		drawCardsEachPlayerByOpinion();

	}

	private void drawCardsEachPlayerByOpinion() {
		users.stream()
			.filter(user -> user.getClass().equals(Player.class))
			.forEach(this::drawCardsByOpinion);
	}

	private void drawCardsByOpinion(User player) {
		while (player.canDraw()) {
			DrawOpinion drawOpinion = DrawOpinion.of(InputView.inputDrawOpinion(player));

			if (DrawOpinion.NO.equals(drawOpinion)) {
				break;
			}
			player.draw(deck);
			OutputView.printUserHand(player, player.getHand());
		}
	}
}
