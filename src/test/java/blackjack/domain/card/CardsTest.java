package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

	@Test
	@DisplayName("K클로버와 2하트가 손에 있으면 12를 반환한다")
	void sumOfCards_K_2_12() {
		Cards cards = new Cards(Arrays.asList(
			new Card(CardSymbol.CLOVER, CardNumber.KING),
			new Card(CardSymbol.CLOVER, CardNumber.TWO)));
		assertThat(cards.sum()).isEqualTo(12);
	}

	@Test
	@DisplayName("버스트가 나지 않는 한, ACE 를 11 로 계산한다")
	void useAceAs_11() {
		Cards cards = new Cards(Arrays.asList(
			new Card(CardSymbol.CLOVER, CardNumber.ACE),
			new Card(CardSymbol.CLOVER, CardNumber.TWO),
			new Card(CardSymbol.SPADE, CardNumber.SEVEN)));
		assertThat(cards.sum()).isEqualTo(20);
	}

	@Test
	@DisplayName("버스트가 날 수 있으면, ACE 를 1 로 계산한다")
	void useAceAs_1() {
		Cards cards = new Cards(Arrays.asList(
			new Card(CardSymbol.CLOVER, CardNumber.ACE),
			new Card(CardSymbol.CLOVER, CardNumber.EIGHT),
			new Card(CardSymbol.SPADE, CardNumber.SEVEN)));
		assertThat(cards.sum()).isEqualTo(16);
	}

	@Test
	@DisplayName("기본적으로는 ACE 를 11 로 계산한다")
	void sumWithoutCheckAce() {
		Cards cards = new Cards(Arrays.asList(
			new Card(CardSymbol.CLOVER, CardNumber.ACE),
			new Card(CardSymbol.CLOVER, CardNumber.EIGHT),
			new Card(CardSymbol.SPADE, CardNumber.SEVEN)));
		assertThat(cards.sumWithoutCheckAce()).isEqualTo(26);
	}
}
