package blakcjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardsTest {
	private final Cards cards = new Cards();

	@BeforeEach
	void setUp() {
		cards.add(Card.of(CardSymbol.SPADE, CardNumber.ACE));
		cards.add(Card.of(CardSymbol.HEART, CardNumber.ACE));
		cards.add(Card.of(CardSymbol.DIAMOND, CardNumber.ACE));
		cards.add(Card.of(CardSymbol.CLUB, CardNumber.ACE));
		cards.add(Card.of(CardSymbol.CLUB, CardNumber.FIVE));
	}

	@DisplayName("스코어를 제대로 계산 해주는지")
	@Test
	void calculateScore() {
		assertThat(cards.calculateScore()).isEqualTo(19);
	}

	@DisplayName("카드들 중에서 첫번째 인덱스의 카드를 제대로 뽑아 내는지")
	@Test
	void getFirstCard_multipleCards_returnCardAtFirstPosition() {
		assertThat(cards.getFirstCard()).isEqualTo(Card.of(CardSymbol.SPADE, CardNumber.ACE));
	}
}