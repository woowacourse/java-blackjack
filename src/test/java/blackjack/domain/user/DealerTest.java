package blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.MockDeck;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

public class DealerTest {
	@Test
	@DisplayName("딜러의 스코어가 더 높은지 확인하는 기능이 정상 작동하는지 확인")
	void dealer_score_higher_than_player_score() {
		//given
		Dealer dealer = new Dealer(new MockDeck(List.of(Card.of(Denomination.NINE, Suit.DIAMOND),
			Card.of(Denomination.NINE, Suit.CLOVER)
		)));
		Player player = new Player("pobi", 1000,
			new MockDeck(List.of(Card.of(Denomination.NINE, Suit.DIAMOND),
			Card.of(Denomination.FIVE, Suit.CLOVER))));
		//when
		//then
		assertThat(dealer.compare(player.state.getCards())).isGreaterThan(0);
	}

	@Test
	@DisplayName("딜러의 스코어가 더 낮은지 확인하는 기능이 정상 작동하는지 확인")
	void dealer_score_lower_than_player_score() {
		//given
		Dealer dealer = new Dealer(new MockDeck(List.of(Card.of(Denomination.NINE, Suit.DIAMOND),
			Card.of(Denomination.NINE, Suit.CLOVER))));
		Player player = new Player("pobi", 1000,
			new MockDeck(List.of(Card.of(Denomination.JACK, Suit.DIAMOND),
				Card.of(Denomination.NINE, Suit.CLOVER))));
		//when
		//then
		assertThat(dealer.compare(player.state.getCards())).isLessThan(0);
	}

	@Test
	@DisplayName("딜러와 플레이어의 스코어가 같은지 확인하는 기능이 정상 작동하는지 확인")
	void dealer_score_equal_to_player_score() {
		//given
		Dealer dealer = new Dealer(new MockDeck(List.of(Card.of(Denomination.NINE, Suit.DIAMOND),
			Card.of(Denomination.NINE, Suit.CLOVER))));
		Player player = new Player("pobi", 1000,
			new MockDeck(List.of(Card.of(Denomination.NINE, Suit.DIAMOND),
				Card.of(Denomination.NINE, Suit.CLOVER))));
		//when
		dealer.addCard(Card.of(Denomination.NINE, Suit.CLOVER));
		player.addCard(Card.of(Denomination.NINE, Suit.HEART));
		//then
		assertThat(dealer.compare(player.state.getCards())).isEqualTo(0);
	}
}
