package domain.card;

import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.card.Rank.*;
import static domain.card.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class CardTest {

    @Test
    void 카드의_이름과_기본_점수를_제공한다() {
        List<Card> cards = List.of(
                new Card(ACE, SPADE),
                new Card(FOUR, HEART),
                new Card(TEN, DIAMOND),
                new Card(QUEEN, CLUB)
        );

        assertThat(cards)
                .extracting(
                        Card::getName,
                        Card::getScore)
                .containsExactly(
                        tuple("A스페이드", 1),
                        tuple("4하트", 4),
                        tuple("10다이아몬드", 10),
                        tuple("Q클로버", 10)
                );
    }
}