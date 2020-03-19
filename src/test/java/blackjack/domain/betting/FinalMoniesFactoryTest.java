package blackjack.domain.betting;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Playable;
import blackjack.domain.user.Players;
import blackjack.domain.user.Results;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FinalMoniesFactoryTest {

	private static Money zero;
	private static Money hundred;
	private static Money thousand;
	private static Money fiveThousand;
	private static List<Money> threeBettingMonies;
	private static Players players;
	private static Monies monies;
	private static Results results;

	private FinalMoniesFactory finalMoniesFactory;

	@BeforeAll
	static void beforeAll() {
		zero = Money.zero();
		hundred = Money.of("100");
		thousand = Money.of("1000");
		fiveThousand = Money.of("5000");
		threeBettingMonies = Arrays.asList(hundred, thousand, fiveThousand);
		players = Players.of("그니,포비,씨유");
		players.getPlayers().get(0).receiveCard(Card.of(Symbol.TEN, Type.DIAMOND));
		players.getPlayers().get(1).receiveCards(Arrays.asList(Card.of(Symbol.FIVE, Type.CLUB),
				Card.of(Symbol.SEVEN, Type.HEART)));
		players.getPlayers().get(2).receiveCard(Card.of(Symbol.TWO, Type.DIAMOND));

		monies = Monies.of(players, threeBettingMonies);

		Playable dealer = Dealer.dealer();
		dealer.receiveCard(Card.of(Symbol.ACE, Type.SPADE));

		results = Results.of(dealer, players);
	}

	@BeforeEach
	void setUp() {
		finalMoniesFactory = FinalMoniesFactory.of(monies, results);
	}

	@Test
	void of() {
		assertThat(finalMoniesFactory).isNotNull();
	}

	@Test
	void create() {
	}
}