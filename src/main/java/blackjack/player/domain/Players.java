package blackjack.player.domain;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.card.domain.CardDeck;
import blackjack.player.domain.report.GameReports;

public class Players {
	private final List<Player> players;

	public Players(List<Player> players) {
		validate(players);
		this.players = players;
	}

	private void validate(List<Player> players) {
		if (players == null || players.isEmpty()) {
			throw new IllegalArgumentException("게임에 플레이어가 존재하지 않습니다.");
		}
	}

	public void drawCard(CardDeck cardDeck) {
		players.forEach(player -> player.addCard(cardDeck.draw()));
	}

	public GameReports getReports() {
		Player dealer = findDealer();
		List<Player> gamblers = findGamblers();

		return gamblers.stream()
			.map(dealer::createReport)
			.collect(Collectors.collectingAndThen(toList(), GameReports::new));
	}

	public List<Player> findGamblers() {
		return players.stream()
			.filter(player ->
				Gambler.class == player.getClass())
			.collect(Collectors.collectingAndThen(toList(), Collections::unmodifiableList));
	}

	public Player findDealer() {
		return players.stream()
			.filter(player ->
				Dealer.class == player.getClass())
			.findFirst()
			.orElseThrow(AssertionError::new);
	}

	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}
}
