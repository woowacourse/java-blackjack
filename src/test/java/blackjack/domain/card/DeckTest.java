package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
	@Test
	@DisplayName("카드를 분배받는 기능을 실행하면 카드 객체가 반환되는지 확인")
	void distribute_Card() {
		Deck deck = new RealDeck();
		assertThat(deck.distributeCard()).isInstanceOf(Card.class);
	}

	@Test
	@DisplayName("카드가 모두 소진되었을 때 카드를 분배하면 에러 발생")
	void check_deck_empty_size() {
		//given
		Deck deck = new RealDeck();
		//when
		for (int i = 0; i < 52; i++) {
			deck.distributeCard();
		}
		//then
		assertThatThrownBy(() -> deck.distributeCard())
			.isInstanceOf(IllegalStateException.class)
			.hasMessageContaining(RealDeck.EMPTY_DECK_EXCEPTION);
	}
}
