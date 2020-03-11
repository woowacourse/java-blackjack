package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
	@Test
	@DisplayName("딜러가 카드를 뽑을수 있는 경우")
	public void isDrawableTest() {
		Dealer dealer = new Dealer();

		assertThat(dealer.isDrawable()).isTrue();
	}

	@Test
	@DisplayName("딜러가 카드를 더이상 뽑을수 없는 경우")
	public void isNotDrawableTest() {
		Dealer dealer = new Dealer();

		assertThat(dealer.isDrawable()).isFalse();
	}

	//Arrays.asList(
	//			new Card(CardSuit.CLOVER, CardNumber.SEVEN),
	//			new Card(CardSuit.CLOVER, CardNumber.TEN))
	//
}