package blackjack.domain;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;

public class Gamers {

	private final List<Gamer> gamers;

	public Gamers(final List<Gamer> gamers) {
		this.gamers = gamers;
	}

	public static Gamers createGamers(final List<String> names) {
		checkDuplicateName(names);
		List<Gamer> gamers = names.stream()
			.map(Gamer::new)
			.collect(Collectors.toList());
		return new Gamers(gamers);
	}

	private static void checkDuplicateName(final List<String> names) {
		Set<String> removalDuplicateNames = new HashSet<>(names);
		if (names.size() != removalDuplicateNames.size()) {
			throw new IllegalArgumentException("[ERROR] 중복된 이름은 입력할 수 없습니다.");
		}
	}

	public Map<Gamer, GameResult> calculateFinalResultBoard(final Player dealer) {
		int dealerResult = dealer.calculateResult();
		return gamers.stream()
			.collect(Collectors.toMap(gamer -> gamer,
				gamer -> GameResult.findResult(dealerResult, gamer.calculateResult()),
				(e1, e2) -> e1, LinkedHashMap::new));
	}

	public List<Gamer> getGamers() {
		return gamers;
	}
}
