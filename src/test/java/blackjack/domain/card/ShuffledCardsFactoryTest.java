package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ShuffledCardsFactoryTest {
	ShuffledCardsFactory deckFactory;

	@BeforeEach
	void setUp() {
		deckFactory = new ShuffledCardsFactory();
	}

	@DisplayName("ShuffledDeckFactory()가 인스턴스를 반환하는지 테스트")
	@Test
	void ShuffledDeckFactory_IsNotNull() {
		assertThat(deckFactory).isNotNull();
	}

	@DisplayName("create()가 중복되지 않는 52개의 카드를 반환하는지 테스트")
	@Test
	void create_ReturnNotDuplicatedFiftyTwoCards() {
		List<Card> cards = deckFactory.create();

		Set<Card> set = new HashSet<>(cards);

		assertThat(set.size()).isEqualTo(52);
	}

	@DisplayName("create()가 생성될 때 마다 다른 카드 리스트를 반환하는지 테스트")
	@Test
	void create_ReturnDifferentOrderWhenever() {
		List<Card> cards1 = deckFactory.create();
		List<Card> cards2 = deckFactory.create();

		assertThat(cards1).isNotEqualTo(cards2);
	}
}