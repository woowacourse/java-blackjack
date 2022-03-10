package blackjack.domain;

import static blackjack.domain.Player.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BlackJackGame {

	private final Player dealer;
	private final List<Gamer> gamers;

	public BlackJackGame(final Player dealer, final List<Gamer> gamers) {
		this.dealer = dealer;
		this.gamers = gamers;
	}

	public static BlackJackGame initializeSetting(final List<String> names, final Deck deck) {
		Player dealer = new Dealer();
		List<Gamer> gamers = createGamers(names);
		setInitialCards(dealer, deck);
		for (Gamer gamer : gamers) {
			setInitialCards(gamer, deck);
		}
		return new BlackJackGame(dealer, gamers);
	}

	private static List<Gamer> createGamers(List<String> names) {
		checkDuplicateName(names);
		return names.stream()
			.map(Gamer::new)
			.collect(Collectors.toList());
	}

	private static void checkDuplicateName(final List<String> names) {
		Set<String> removalDuplicateNames = new HashSet<>(names);
		if (names.size() != removalDuplicateNames.size()) {
			throw new IllegalArgumentException("[ERROR] 중복된 이름은 입력할 수 없습니다.");
		}
	}

	private static void setInitialCards(final Player player, final Deck deck) {
		for (int i = 0; i < PLAYER_ALREADY_CARD_SIZE; i++) {
			player.receiveCard(deck.draw());
		}
	}

	public Map<Gamer, Result> calculateResultBoard() {
		int dealerResult = dealer.calculateResult();
		return gamers.stream()
			.collect(Collectors.toMap(gamer -> gamer,
				gamer -> Result.findResult(dealerResult, gamer.calculateResult()),
				(e1, e2) -> e1, LinkedHashMap::new));
	}

	public Player getDealer() {
		return dealer;
	}

	public List<Gamer> getGamers() {
		return List.copyOf(gamers);
	}
}
