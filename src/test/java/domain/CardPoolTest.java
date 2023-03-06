package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardType;
import domain.user.CardPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class CardPoolTest {

    @Test
    @DisplayName("카드가 하나씩 잘 저장된다")
    void addTest() {
        CardPool cardPool = new CardPool(Collections.emptyList());
        Card card = new Card(CardType.CLOVER, CardNumber.FOUR);

        cardPool.add(card);

        assertThat(cardPool.getCards()).contains(card);
    }

    @Test
    @DisplayName("카드가 한번에 잘 저장된다")
    void makeCardPoolTest() {
        List<Card> cards = List.of(
                new Card(CardType.CLOVER, CardNumber.EIGHT),
                new Card(CardType.DIAMOND, CardNumber.ACE),
                new Card(CardType.HEART, CardNumber.KING)
                );

        CardPool cardPool = new CardPool(cards);

        assertThat(cardPool.getCards())
                .isEqualTo(cards);
    }

    @Test
    @DisplayName("카드가 없을때에는 합이 0이다")
    void sumCardPoolWhenCardsNotExist() {
        List<Card> cards = Collections.emptyList();

        CardPool cardPool = new CardPool(cards);

        assertThat(cardPool.sumCardNumbers())
                .isEqualTo(0);
    }

    @Test
    @DisplayName("카드가 있을때에는 합이 카드 값에 따라 결정된다")
    void sumCardPoolWhenCardsExist() {
        List<Card> cards = List.of(
                new Card(CardType.CLOVER, CardNumber.FIVE),
                new Card(CardType.HEART, CardNumber.EIGHT)
        );

        CardPool cardPool = new CardPool(cards);

        assertThat(cardPool.sumCardNumbers())
                .isEqualTo(13);
    }

    @Test
    @DisplayName("에이스카드는 나머지 카드의 합이 10보다 크면 1로 결정된다")
    void decideAceSumOver() {
        List<Card> cards = List.of(
                new Card(CardType.CLOVER, CardNumber.FIVE),
                new Card(CardType.HEART, CardNumber.EIGHT),
                new Card(CardType.CLOVER, CardNumber.ACE)
        );

        CardPool cardPool = new CardPool(cards);

        assertThat(cardPool.sumCardNumbers())
                .isEqualTo(14);
    }

    @Test
    @DisplayName("에이스카드가 먼저 뽑혀도 나머지 카드의 합이 10보다 크면 1로 결정된다")
    void decideAceSumOverWhenFirstDraw() {
        List<Card> cards = List.of(
                new Card(CardType.CLOVER, CardNumber.ACE),
                new Card(CardType.CLOVER, CardNumber.FIVE),
                new Card(CardType.HEART, CardNumber.EIGHT)

        );

        CardPool cardPool = new CardPool(cards);

        assertThat(cardPool.sumCardNumbers())
                .isEqualTo(14);
    }

    @Test
    @DisplayName("에이스카드는 나머지 카드의 합이 10보다 작으면 11로 결정된다")
    void decideAceSumUnder() {
        List<Card> cards = List.of(
                new Card(CardType.CLOVER, CardNumber.FIVE),
                new Card(CardType.HEART, CardNumber.FOUR),
                new Card(CardType.CLOVER, CardNumber.ACE)
        );

        CardPool cardPool = new CardPool(cards);

        assertThat(cardPool.sumCardNumbers())
                .isEqualTo(20);
    }

    @Test
    @DisplayName("카드 숫자의 합이 21이하이면 false를 반환한다.")
    void isSumExceedWhenUnderCardPointLimit() {
        List<Card> cards = List.of(
                new Card(CardType.CLOVER, CardNumber.ACE)
        );

        CardPool cardPool = new CardPool(cards);

        assertThat(cardPool.isSumExceedLimit())
                .isFalse();
    }

    @Test
    @DisplayName("카드 숫자의 합이 21을 넘으면 true를 반환한다.")
    void isSumExceedWhenOverCardPointLimit() {
        List<Card> cards = List.of(
                new Card(CardType.CLOVER, CardNumber.JACK),
                new Card(CardType.CLOVER, CardNumber.JACK),
                new Card(CardType.CLOVER, CardNumber.JACK)
        );

        CardPool cardPool = new CardPool(cards);

        assertThat(cardPool.isSumExceedLimit())
                .isTrue();
    }
}
