package blackjack.domain.process;

import blackjack.domain.betting.BettingToken;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {
	private static final int INIT_DISTRIBUTE_AMOUNT = 2;
	private static final int EACH_TURN_DISTRIBUTE_AMOUNT = 1;

	private final Deck deck;

	public BlackJackGame(Deck deck) {
		this.deck = deck;
	}

	public void drawTo(Gamer gamer) {
		gamer.addCards(this.deck.distributeCards(EACH_TURN_DISTRIBUTE_AMOUNT));
	}

	public void initDrawTo(Gamer gamer) {
		gamer.addCards(this.deck.distributeCards(INIT_DISTRIBUTE_AMOUNT));
	}

	public void shuffleDeck() {
		this.deck.shuffle();
	}

	public List<Name> generateNames(List<String> inputNames) {
		return inputNames.stream()
			.map(Name::new)
			.collect(Collectors.toList());
	}

	public Players generatePlayers(List<Name> names, List<BettingToken> bettingTokens) {
		List<Player> players = new ArrayList<>();
		for (int i = 0; i < names.size(); i++) {
			players.add(new Player(bettingTokens.get(i), names.get(i)));
		}
		return new Players(players);
	}
}
