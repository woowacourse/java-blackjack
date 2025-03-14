package blackjack.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CardsTest {

    private Cards cards;
    private CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        cardDeck = CardDeck.initialize();
        cards = Cards.empty();
    }

    @Test
    @DisplayName("카드 한 장을 추가할 수 있다")
    void canAddSingleCard() {
        // given
        Card card = CardFixture.createCard(CardSuit.HEART, CardRank.KING);

        // when
        cards.add(card);

        // then
        assertThat(cards.size()).isEqualTo(1);
        assertThat(cards.getCards()).contains(card);
    }

    @Test
    @DisplayName("카드 일급 컬렉션을 추가할 수 있다")
    void canAddMultipleCards() {
        // given
        Cards newCards = Cards.from(List.of(
                CardFixture.createCard(CardSuit.CLUB, CardRank.TWO),
                CardFixture.createCard(CardSuit.DIAMOND, CardRank.THREE)
        ));

        // when
        cards.add(newCards);

        // then
        assertThat(cards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드 덱을 추가할 수 있다")
    void canAddCardDeck() {
        // when
        cards.add(cardDeck);

        // then
        assertThat(cards.size()).isEqualTo(52);
    }

    @Test
    @DisplayName("카드를 섞을 수 있다")
    void canShuffle() {
        // given
        cards.add(cardDeck);
        List<Card> beforeShuffle = List.copyOf(cards.getCards());

        // when
        cards.shuffle();

        // then
        List<Card> afterShuffle = cards.getCards();
        assertThat(afterShuffle).isNotEqualTo(beforeShuffle);
    }

    @Test
    @DisplayName("카드를 한 장 뽑을 수 있다")
    void canDraw() {
        // given
        cards.add(cardDeck);
        int initialSize = cards.size();

        // when
        Card drawnCard = cards.draw();

        // then
        assertThat(drawnCard).isNotNull();
        assertThat(cards.size()).isEqualTo(initialSize - 1);
    }

    @Test
    @DisplayName("카드들의 합계를 룰에 유리하게 구할 수 있다")
    void shouldCalculateSum() {
        // given
        cards.add(CardFixture.createCard(CardSuit.HEART, CardRank.KING)); // 10
        cards.add(CardFixture.createCard(CardSuit.SPADE, CardRank.FIVE)); // 5
        cards.add(CardFixture.createCard(CardSuit.CLUB, CardRank.ACE)); // 11 or 1

        // when
        int totalSum = cards.calculateSum(); // 26 or 16, 16 betterThan 26

        // then
        assertThat(totalSum).isEqualTo(16);
    }
}
