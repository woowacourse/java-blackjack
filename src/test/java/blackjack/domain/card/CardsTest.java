package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

	@Test
	@DisplayName("K클로버와 2하트가 손에 있으면 12를 반환한다")
	void sumOfCards_K_2_12() {
		Cards cards = new Cards();
		cards.addCard(new Card(CardSymbol.CLOVER, CardValue.KING));
		cards.addCard(new Card(CardSymbol.HEART, CardValue.TWO));
		assertThat(cards.sum()).isEqualTo(12);
	}

	@Test
	@DisplayName("총 합이 11 이하이면, ACE 를 11 로 계산한다")
	void useAceAs_11() {
		Cards cards = new Cards();
		cards.addCard(new Card(CardSymbol.CLOVER, CardValue.ACE));
		cards.addCard(new Card(CardSymbol.HEART, CardValue.TWO));
		cards.addCard(new Card(CardSymbol.SPADE, CardValue.SEVEN));
		assertThat(cards.sum()).isEqualTo(20);
	}

	@Test
	@DisplayName("총 합이 12 이상이면, ACE 를 1 로 계산한다")
	void useAceAs_1() {
		Cards cards = new Cards();
		cards.addCard(new Card(CardSymbol.CLOVER, CardValue.ACE));
		cards.addCard(new Card(CardSymbol.HEART, CardValue.EIGHT));
		cards.addCard(new Card(CardSymbol.SPADE, CardValue.SEVEN));
		assertThat(cards.sum()).isEqualTo(16);
	}
}
