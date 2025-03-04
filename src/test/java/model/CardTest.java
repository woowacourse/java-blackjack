package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    @DisplayName("카드 객체 생성이 잘 되는 지")
    void newCard() {

        // given
        final SuitType suit = SuitType.HEARTS;
        final RankType rank = RankType.FIVE;

        // when
        final Card card = new Card(suit, rank);

        // then
        assertAll(
                () -> Assertions.assertThat(card.getSuit()).isEqualTo(suit),
                () -> Assertions.assertThat(card.getRank()).isEqualTo(rank)
        );
    }
}