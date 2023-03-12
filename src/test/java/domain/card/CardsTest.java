package domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

	@Test
	@DisplayName("카드를 추가하는 것을 테스트")
	public void testAddCard() {
		//given
		Cards cards = Cards.of(new Card(Suit.DIAMOND, Denomination.ACE));
		Card card = new Card(Suit.SPADE, Denomination.ACE);

		//when
		cards.add(card);

		//then
		assertThat(cards.count(Denomination.ACE)).isEqualTo(2);
	}

	@Test
	@DisplayName("특정 문자 카드 보유 개수를 테스트")
	public void testCount() {
		//given
		Cards cards = Cards.of(
			new Card(Suit.CLOVER, Denomination.ACE),
			new Card(Suit.DIAMOND, Denomination.ACE),
			new Card(Suit.SPADE, Denomination.ACE));

		//when
		int result1 = cards.count(Denomination.ACE);
		int result2 = cards.count(Denomination.TWO);

		//then
		assertThat(result1).isEqualTo(3);
		assertThat(result2).isEqualTo(0);
	}
}
