package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardFactoryTest {

	@Test
	@DisplayName("카드 1장 반환 확인")
	void pickCard() {
		CardFactory cardFactory = new CardFactory(Card.getCards());
		cardFactory.draw();
		assertThat(cardFactory.getSize()).isEqualTo(51);
	}

	@Test
	@DisplayName("카드가 없을 때 뽑으면 에러를 발생시킨다.")
	void validateEmptyDeck() {
		CardFactory cardFactory = new CardFactory(new LinkedList<>());
		assertThatThrownBy(() -> {
			cardFactory.draw();
		}).isInstanceOf(IllegalStateException.class)
			.hasMessageContaining("카드가 존재하지 않습니다.");
	}
}