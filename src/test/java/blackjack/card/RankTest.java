package blackjack.card;

import static card.Rank.ACE;
import static card.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    @DisplayName("ACE의 label A를 가져온다.")
    void getLabelOne() {
        assertThat(ACE.getLabel()).isEqualTo("A");
    }

    @Test
    @DisplayName("TWO의 label \"2\"를 가져온다.")
    void getLabelTwo() {
        assertThat(TWO.getLabel()).isEqualTo("2");
    }

    @Test
    @DisplayName("ACE의 value 1을 가져온다.")
    void getValueOne() {
        assertThat(ACE.getValue()).isEqualTo(1);
    }

    @Test
    @DisplayName("TWO의 value 1을 가져온다.")
    void getValueTwo() {
        assertThat(TWO.getValue()).isEqualTo(2);
    }
}
