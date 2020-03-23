package view.dto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Players;
import domain.user.User;

public class UserScoreDto {
	private static final String PLAYERS_DEALER_NULL_EXCEPTION_MESSAGE = "플레이어들, 딜러는 null이 될 수 없습니다.";

	private final String name;
	private final List<Card> cards;
	private final int score;

	public UserScoreDto(String name, List<Card> cards, int score) {
		this.name = name;
		this.cards = Objects.requireNonNull(cards);
		this.score = score;
	}

	public static List<UserScoreDto> createAllUsersDto(Players players, Dealer dealer) {
		validateNull(players, dealer);
		return Stream.concat(players.getPlayers().stream(), Stream.of(dealer))
			.map(user -> new UserScoreDto(user.getName(), user.getCards(), user.calculateScore().getScore()))
			.collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
	}

	private static void validateNull(Players players, Dealer dealer) {
		if (Objects.isNull(players) || Objects.isNull(dealer)) {
			throw new IllegalArgumentException(PLAYERS_DEALER_NULL_EXCEPTION_MESSAGE);
		}
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public List<Card> getCards() {
		return cards;
	}
}
