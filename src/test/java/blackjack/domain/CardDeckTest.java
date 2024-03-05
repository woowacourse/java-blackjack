package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("총 카드 덱 테스트")
class CardDeckTest {

    @DisplayName("덱에서 첫번째 카드를 건네받을 수 있다")
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

    @DisplayName("덱에서 횟수만큼 카드를 건네받을 수 있다")
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
    // TODO : 카드가 남아있지 않은 경우 카드를 뽑으려고 할 때가 있을 지 생각해보기
}
