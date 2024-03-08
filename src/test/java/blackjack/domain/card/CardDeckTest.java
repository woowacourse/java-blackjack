package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("총 카드 덱 테스트")
class CardDeckTest {

    @DisplayName("덱에서 카드를 뽑을 수 있다")
    @Test
    void testPopCardFromDeck() {
        List<Card> cards = new ArrayList<>();
        Card card1 = new Card(CardShape.HEART, CardNumber.TWO);
        Card card2 = new Card(CardShape.CLUB, CardNumber.THREE);
        Card card3 = new Card(CardShape.DIAMOND, CardNumber.FOUR);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        CardDeck cardDeck = new CardDeck(cards);
        Card popped = cardDeck.popCard();
        assertThat(popped).isEqualTo(card1);
    }

    @DisplayName("덱에서 횟수만큼 카드를 뽑을 수 있다")
    @Test
    void testPopCardsFromDeck() {
        List<Card> cards = new ArrayList<>();
        Card card1 = new Card(CardShape.HEART, CardNumber.TWO);
        Card card2 = new Card(CardShape.CLUB, CardNumber.THREE);
        Card card3 = new Card(CardShape.DIAMOND, CardNumber.FOUR);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        CardDeck cardDeck = new CardDeck(cards);
        List<Card> popped = cardDeck.popCards(3);

        assertThat(popped).hasSize(3);
    }

    @DisplayName("덱에서 카드를 하나 뽑는 경우 카드가 부족하면 예외가 발생한다")
    @Test
    void testInvalidPopInsufficientDeckCount() {
        List<Card> cards = new ArrayList<>();
        CardDeck cardDeck = new CardDeck(cards);

        assertThatThrownBy(() -> cardDeck.popCard())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 남아있는 카드가 부족하여 카드를 뽑을 수 없습니다");
    }

    @DisplayName("덱에서 카드를 여러개 뽑는 경우 카드가 부족하면 예외가 발생한다")
    @Test
    void testInvalidPopCardsInsufficientDeckCount() {
        List<Card> cards = new ArrayList<>();
        Card card1 = new Card(CardShape.HEART, CardNumber.TWO);
        Card card2 = new Card(CardShape.CLUB, CardNumber.THREE);
        Card card3 = new Card(CardShape.DIAMOND, CardNumber.FOUR);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        CardDeck cardDeck = new CardDeck(cards);
        assertThatThrownBy(() -> cardDeck.popCards(4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 남아있는 카드가 부족하여 카드를 뽑을 수 없습니다");
    }
}
