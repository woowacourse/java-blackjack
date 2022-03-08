package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
	@Test
	@DisplayName("딜러가 손패를 더 받아야 하는 경우")
	void isEnoughCard_False() {
		Card card1 = new Card(Rank.RANK_5, Suit.HEART);
		Card card2 = new Card(Rank.RANK_J, Suit.SPADE);
		Card card3 = new Card(Rank.RANK_A, Suit.CLOVER);
		Dealer dealer = new Dealer(new ArrayList<>(List.of(card1, card2, card3)));
		assertThat(dealer.isEnoughCard()).isFalse();
	}

	@Test
	@DisplayName("딜러가 손패를 더 안 받아야 하는 경우")
	void isEnoughCard_True() {
		Card card1 = new Card(Rank.RANK_6, Suit.HEART);
		Card card2 = new Card(Rank.RANK_J, Suit.SPADE);
		Card card3 = new Card(Rank.RANK_A, Suit.CLOVER);
		Dealer dealer = new Dealer(new ArrayList<>(List.of(card1, card2, card3)));
		assertThat(dealer.isEnoughCard()).isTrue();
	}
}
