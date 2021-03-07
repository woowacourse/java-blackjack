package blakcjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardsTest {
	private Cards cards = new Cards();

	@DisplayName("스코어를 제대로 계산 해주는지")
	@Test
	void calculateScore() {
		cards.add(Card.of(CardSymbol.SPADE, CardNumber.ACE));
		cards.add(Card.of(CardSymbol.HEART, CardNumber.ACE));
		cards.add(Card.of(CardSymbol.DIAMOND, CardNumber.ACE));
		cards.add(Card.of(CardSymbol.CLUB, CardNumber.ACE));
		cards.add(Card.of(CardSymbol.CLUB, CardNumber.FIVE));

		assertThat(cards.calculateScore()).isEqualTo(19);
	}
}