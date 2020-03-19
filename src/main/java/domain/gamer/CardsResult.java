package domain.gamer;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CardsResult {
	private Map<Gamer, Integer> gamersCardResult;

	public CardsResult(Gamers gamers) {
		this.gamersCardResult = gamers.getPlayers().stream()
			.collect(Collectors.toMap(gamer -> gamer, Gamer::calculateScore,
				(a, b) -> a, LinkedHashMap::new));

		Dealer dealer = gamers.getDealer();
		gamersCardResult.put(dealer, dealer.calculateScore());
	}

	public Map<Gamer, Integer> getGamersCardResult() {
		return gamersCardResult;
	}
}
