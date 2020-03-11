package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class DealerTest {
	@Test
	@DisplayName("딜러가 카드를 뽑을수 있는 경우")
	public void isDrawableTest() {
		Dealer dealer = new Dealer();
		dealer.addCard(Arrays.asList(
				new Card(CardSuit.CLOVER, CardNumber.SIX),
				new Card(CardSuit.CLOVER, CardNumber.TEN))
		);
		assertThat(dealer.isDrawable()).isTrue();
	}

	@Test
	@DisplayName("딜러가 카드를 더이상 뽑을수 없는 경우")
	public void isNotDrawableTest() {
		Dealer dealer = new Dealer();
		dealer.addCard(Arrays.asList(
				new Card(CardSuit.CLOVER, CardNumber.SEVEN),
				new Card(CardSuit.CLOVER, CardNumber.TEN))
		);
		assertThat(dealer.isDrawable()).isFalse();
	}
}