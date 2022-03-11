package blackjack.domain.card;

import static blackjack.domain.exceptionMessages.CardExceptionMessage.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
	@Test
	@DisplayName("카드를 분배받는 기능을 실행하면 카드 객체가 반환되는지 확인")
	void distribute_Card() {
		Deck deck = new Deck();
		assertThat(deck.distributeCard()).isInstanceOf(Card.class);
	}

	@Test
	@DisplayName("덱에서 카드 한장을 분배했을 때 사이즈가 올바르게 줄어들었는지 확인")
	void check_deck_size() {
		//given
		Deck deck = new Deck();
		//when
		deck.distributeCard();
		//then
		assertThat(deck.getCards().size()).isEqualTo(51);
	}

	@Test
	@DisplayName("카드가 모두 소진되었을 때 카드를 분배하면 에러 발생")
	void check_deck_empty_size() {
		//given
		Deck deck = new Deck();
		//when
		for (int i = 0; i < 52; i++) {
			deck.distributeCard();
		}
		//then
		assertThatThrownBy(() -> deck.distributeCard())
			.isInstanceOf(IllegalStateException.class)
			.hasMessageContaining(EMPTY_DECK_EXCEPTION.getMessage());
	}
}
