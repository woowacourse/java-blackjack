package model.cards;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerCardsTest {

    @DisplayName("첫 번째 순서의 카드를 반환한다.")
    @Test
    void test1() {
        DealerCards cards = new DealerCards(new ArrayList<>(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.FIVE, CardShape.SPADE),
                new Card(CardNumber.QUEEN, CardShape.SPADE)))
        );

        Card expected = new Card(CardNumber.KING, CardShape.SPADE);

        assertThat(cards.getFirstCard()).isEqualTo(expected);
    }

    @DisplayName("딜러가 추가로 뽑은 카드의 수를 반환한다.")
    @Test
    void test2() {
        DealerCards cards = new DealerCards(new ArrayList<>(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.FIVE, CardShape.SPADE),
                new Card(CardNumber.QUEEN, CardShape.SPADE)))
        );

        assertThat(cards.getAdditionalDrawCount()).isEqualTo(1);
    }
}
