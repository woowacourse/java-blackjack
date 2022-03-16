package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Rank;
import domain.card.Suit;

public class DealerTest {

	private Card card1;
	private Card card2;

	@BeforeEach
	void setup() {
		card1 = new Card(Rank.RANK_JACK, Suit.SPADE);
		card2 = new Card(Rank.RANK_ACE, Suit.CLOVER);
	}

	@Test
	@DisplayName("딜러가 손패를 더 받아야 하는 경우")
	void isEnoughCard_False() {
		Card card = new Card(Rank.RANK_FIVE, Suit.HEART);
		Dealer dealer = new Dealer(new Hand(List.of(card, card1, card2)));
		assertThat(dealer.isEnoughCard()).isFalse();
	}

	@Test
	@DisplayName("딜러가 손패를 더 안 받아야 하는 경우")
	void isEnoughCard_True() {
		Card card = new Card(Rank.RANK_SIX, Suit.HEART);
		Dealer dealer = new Dealer(new Hand(List.of(card, card1, card2)));
		assertThat(dealer.isEnoughCard()).isTrue();
	}
}
