package domain.card;

import static domain.card.Rank.ACE;
import static domain.card.Rank.EIGHT;
import static domain.card.Rank.FIVE;
import static domain.card.Rank.FOUR;
import static domain.card.Rank.JACK;
import static domain.card.Rank.KING;
import static domain.card.Rank.NINE;
import static domain.card.Rank.QUEEN;
import static domain.card.Rank.SEVEN;
import static domain.card.Rank.SIX;
import static domain.card.Rank.TEN;
import static domain.card.Rank.THREE;
import static domain.card.Rank.TWO;
import static domain.card.Symbol.DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("성공: 점수 반환")
    void score_NoException() {
        Assertions.assertAll(
            () -> assertThat(new Card(KING, DIAMOND).score()).isEqualTo(10),
            () -> assertThat(new Card(QUEEN, DIAMOND).score()).isEqualTo(10),
            () -> assertThat(new Card(JACK, DIAMOND).score()).isEqualTo(10),
            () -> assertThat(new Card(TEN, DIAMOND).score()).isEqualTo(10),
            () -> assertThat(new Card(NINE, DIAMOND).score()).isEqualTo(9),
            () -> assertThat(new Card(EIGHT, DIAMOND).score()).isEqualTo(8),
            () -> assertThat(new Card(SEVEN, DIAMOND).score()).isEqualTo(7),
            () -> assertThat(new Card(SIX, DIAMOND).score()).isEqualTo(6),
            () -> assertThat(new Card(FIVE, DIAMOND).score()).isEqualTo(5),
            () -> assertThat(new Card(FOUR, DIAMOND).score()).isEqualTo(4),
            () -> assertThat(new Card(THREE, DIAMOND).score()).isEqualTo(3),
            () -> assertThat(new Card(TWO, DIAMOND).score()).isEqualTo(2),
            () -> assertThat(new Card(ACE, DIAMOND).score()).isEqualTo(1)
        );
    }
}
