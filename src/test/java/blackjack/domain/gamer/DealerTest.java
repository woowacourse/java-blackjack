package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.card.UnShuffledDeckGenerator;

public class DealerTest {
	private Deck deck;

	@BeforeEach
	void init() {
		UnShuffledDeckGenerator deckGenerator = UnShuffledDeckGenerator.getInstance();

		deck = deckGenerator.generate();
	}

	@DisplayName("딜러는 생성 시에 중복되지 않은 52장의 덱을 갖는다.")
	@Test
	void hasDeckTest() {
		// given & when
		Dealer dealer = Dealer.newInstance(deck);

		// then
		assertThat(dealer.deckSize()).isEqualTo(52);
	}

	@DisplayName("딜러는 플레이어들에게 초기 카드를 배분한다.")
	@Test
	void dealInitCardsTest() {
		// given
		Dealer dealer = Dealer.newInstance(deck);

		// when
		List<Card> cards = dealer.dealInit();

		// then
		assertThat(cards).hasSize(2);
	}

	@DisplayName("딜러는 자신에게 초기 카드를 배분한다.")
	@Test
	void dealInitCardsSelfTest() {
		// given
		Dealer dealer = Dealer.newInstance(deck);

		// when
		dealer.receiveInitCards(dealer.dealInit());

		// then
		assertThat(dealer.getCardHand()).hasSize(2);
	}

	@DisplayName("딜러는 카드 1장을 분배한다.")
	@Test
	void dealCardTest() {
		// given
		Dealer dealer = Dealer.newInstance(deck);

		// when
		Card card = dealer.dealCard();

		// then
		assertThat(dealer)
			.extracting("deck")
			.extracting("cards")
			.isNotIn(card);
	}

	@DisplayName("딜러 카드 패의 총 합이 16 이하라면 Hit한다.")
	@Test
	void dealerHitTest() {
		// given
		Dealer dealer = Dealer.of(deck,
			new ArrayList<>(List.of(new Card(Suit.HEART, Rank.TEN), new Card(Suit.HEART, Rank.SIX))));
		int beforeDeckSize = dealer.deckSize();

		// when & then
		Assertions.assertAll(
			() -> assertThat(dealer.tryHit()).isTrue(),
			() -> assertThat(dealer.deckSize()).isEqualTo(beforeDeckSize - 1)
		);
	}

	@DisplayName("딜러 카드 패의 총 합이 16 초과라면 Stand한다.")
	@Test
	void dealerStandTest() {
		// given
		Dealer dealer = Dealer.of(deck,
			new ArrayList<>(List.of(new Card(Suit.HEART, Rank.TEN), new Card(Suit.HEART, Rank.SEVEN))));
		int beforeDeckSize = dealer.deckSize();

		// when & then
		Assertions.assertAll(
			() -> assertThat(dealer.tryHit()).isFalse(),
			() -> assertThat(dealer.deckSize()).isEqualTo(beforeDeckSize)
		);
	}
}
