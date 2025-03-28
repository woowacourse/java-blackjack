package model.cards;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import model.deck.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerCardsGeneratorTest {

    @DisplayName("딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 한다.")
    @Test
    void test1() {
        Cards cards = new DealerCardsGenerator().generate(new Deck(
                new ArrayList<>(List.of(new Card(CardNumber.QUEEN, CardShape.SPADE),
                        new Card(CardNumber.EIGHT, CardShape.HEART),
                        new Card(CardNumber.EIGHT, CardShape.DIAMOND)
                ))
        ));

        assertThat(cards.getCards()).hasSize(3);
    }

    @DisplayName("딜러는 처음에 받은 2장의 합계가 17점 이상이면 카드를 추가로 받지 않는다.")
    @Test
    void test2() {
        Cards cards = new DealerCardsGenerator().generate(new Deck(
                new ArrayList<>(List.of(new Card(CardNumber.QUEEN, CardShape.SPADE),
                        new Card(CardNumber.KING, CardShape.HEART),
                        new Card(CardNumber.SEVEN, CardShape.DIAMOND)
                ))
        ));

        assertThat(cards.getCards()).hasSize(2);
    }

    @DisplayName("딜러가 에이스를 가진 상태로 합이 21을 초과한다면 에이스를 1로 계산한다")
    @Test
    void test3() {
        Cards cards = new DealerCardsGenerator().generate(new Deck(
                new ArrayList<>(List.of(
                        new Card(CardNumber.JACK, CardShape.SPADE),
                        new Card(CardNumber.KING, CardShape.SPADE),
                        new Card(CardNumber.QUEEN, CardShape.SPADE),
                        new Card(CardNumber.FIVE, CardShape.HEART),
                        new Card(CardNumber.ACE_ELEVEN, CardShape.DIAMOND)
                ))
        ));

        assertThat(cards.getCards()).hasSize(4);
    }
}
