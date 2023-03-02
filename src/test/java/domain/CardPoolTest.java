package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CardPoolTest {

    @Test
    @DisplayName("카드 풀에 카드가 저장된다")
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
    @DisplayName("카드가 잘 들어가는지 테스트한다.")
    void addTest() {
        CardPool cardPool = new CardPool(Collections.emptyList());
        Card card = new Card(CardType.CLOVER, CardNumber.FOUR);

        cardPool.add(card);

        assertThat(cardPool.getCards()).contains(card);
    }
}