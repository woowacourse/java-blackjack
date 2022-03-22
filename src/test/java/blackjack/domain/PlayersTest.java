package blackjack.domain;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

class PlayersTest {

	private final Map<String, Integer> nameAndBet = Stream.of("pobi", "jason")
		.collect(toMap(name -> name, name -> 10));
	private final Players players = new Players(nameAndBet);

	@Test
	@DisplayName("카드를 2장씩 나눠준다.")
	void handOutFirst() {
		players.handOutFirst(new Deck(Card.getCards()));

		List<Card> pobiCards = players.findCards("pobi");
		List<Card> jasonCards = players.findCards("jason");

		assertThat(pobiCards.size()).isEqualTo(2);
		assertThat(jasonCards.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("모든 플레이어의 이름을 찾아낸다.")
	void findPlayerNames() {
		List<String> names = players.findNames();
		assertThat(names).containsExactly("pobi", "jason");
	}
}