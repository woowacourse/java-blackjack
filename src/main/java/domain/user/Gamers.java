package domain.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import domain.card.CardDeck;
import domain.result.ScoreBoards;
import domain.result.UserResults;
import util.DrawResponse;

public class Gamers {
	private static final int FIRST_DRAW_COUNT = 2;

	private final List<Player> players;
	private final Dealer dealer;
	private final CardDeck deck;

	public Gamers(List<Player> players, Dealer dealer, CardDeck deck) {
		this.players = Collections.unmodifiableList(new ArrayList<>(Objects.requireNonNull(players)));
		this.dealer = Objects.requireNonNull(dealer);
		this.deck = Objects.requireNonNull(deck);
	}

	public void drawFirstTime(Consumer<User> firstDrawResultConsumer) {
		for (int i = 0; i < FIRST_DRAW_COUNT; i++) {
			Stream.concat(players.stream(), Stream.of(dealer))
				.forEach(user -> user.draw(deck));
		}
		Stream.concat(players.stream(), Stream.of(dealer))
			.forEach(firstDrawResultConsumer);
	}

	public void drawPlayersAdditional(Function<String, String> nameToResponseFunction, Consumer<Player> outputPrinter) {
		for (Player player : players) {
			drawSinglePlayerAdditional(nameToResponseFunction, outputPrinter, player);
		}
	}

	private void drawSinglePlayerAdditional(Function<String, String> nameToResponseFunction,
		Consumer<Player> outputPrinter, Player player) {
		while (player.isDrawable() && isYes(player, nameToResponseFunction)) {
			player.draw(deck);
			outputPrinter.accept(player);
		}
	}

	private boolean isYes(Player player, Function<String, String> nameToResponseFunction) {
		return DrawResponse.isYes(nameToResponseFunction.apply(player.getName()));
	}

	public void drawDealerAdditional(Consumer<Dealer> outputPrinter) {
		if (dealer.isDrawable()) {
			dealer.draw(deck);
			outputPrinter.accept(dealer);
		}
	}

	public ScoreBoards calculateScoreBoards() {
		return ScoreBoards.fromAllUsers(players, dealer);
	}

	public UserResults calculatePrizeResults(ScoreBoards scoreBoards) {
		return scoreBoards.calculateUsersResult();
	}
}
