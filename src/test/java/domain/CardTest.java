package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.constant.Rank.*;
import static domain.constant.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @Test
    void 카드의_점수를_제공한다() {
        List<Card> cards = List.of(
                new Card(ACE, SPADE),
                new Card(FOUR, HEART),
                new Card(TEN, DIAMOND),
                new Card(QUEEN, CLUB)
        );

        assertThat(cards)
                .extracting(Card::getScore)
                .containsExactly(1, 4, 10, 10);
    }

    @Test
    void 카드의_이름을_제공한다() {
        List<Card> cards = List.of(
                new Card(ACE, SPADE),
                new Card(FOUR, HEART),
                new Card(TEN, DIAMOND),
                new Card(QUEEN, CLUB)
        );

        assertThat(cards)
                .extracting(Card::getName)
                .containsExactly(
                        "A스페이드",
                        "4하트",
                        "10다이아몬드",
                        "Q클로버"
                );
    }

}