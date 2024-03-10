package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class CardDeckTest {

    @DisplayName("카드 덱에서 카드를 한 장 빼내어 반환한다.")
    @Test
    void testPopCard() {
        // given
        List<Card> cards = new ArrayList<>();
        Card card = new Card(CardRank.ACE, CardSuit.HEART);
        cards.add(card);

        CardDeck cardDeck = new CardDeck(cards);

        // when
        Card actual = cardDeck.popCard();

        // then
        assertAll(
                () -> assertThat(actual).isEqualTo(card),
                () -> assertThatThrownBy(cardDeck::popCard)
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @DisplayName("카드 덱에서 카드를 지정한 개수만큼 빼내어 반환한다.")
    @Test
    void testPopCardsFromDeck() {
        // given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardShape.HEART, CardNumber.TWO));
        cards.add(new Card(CardShape.CLUB, CardNumber.THREE));
        cards.add(new Card(CardShape.DIAMOND, CardNumber.FOUR));

        CardDeck cardDeck = new CardDeck(cards);

        // when
        List<Card> actual = cardDeck.popCards(3);

        // then
        assertThat(actual).hasSize(3);
    }

    @DisplayName("카드 덱이 비었는데 카드를 빼내서 반환하려고 하면 예외가 발생한다.")
    @Test
    void testPopCardWithEmptyDeck() {
        // given
        CardDeck cardDeck = new CardDeck(new ArrayList<>());

        // when & then
        assertThatThrownBy(cardDeck::popCard)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("모든 카드 랭크와 모양의 조합으로 이루어진 52장의 카드를 가지고 카드 덱을 생성한다.")
    @Test
    void testCreateFullCardDeck() {
        // given
        CardDeck shuffledFullCardDeck = CardDeck.createShuffledFullCardDeck();

        // when
        List<Card> cards = shuffledFullCardDeck.popCards(52);

        // then
        assertThat(cards)
                .doesNotHaveDuplicates()
                .hasSize(52);
    }
}
