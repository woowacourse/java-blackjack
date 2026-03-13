package domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class RankTest {

    @Test
    void 각_항목의_이름과_그에_매칭되는_기본_점수를_제공한다() {
        assertThat(Rank.values())
                .extracting(
                        Rank::getName,
                        Rank::getScore
                )
                .containsExactly(
                        tuple("A", 1),
                        tuple("2", 2),
                        tuple("3", 3),
                        tuple("4", 4),
                        tuple("5", 5),
                        tuple("6", 6),
                        tuple("7", 7),
                        tuple("8", 8),
                        tuple("9", 9),
                        tuple("10", 10),
                        tuple("J", 10),
                        tuple("Q", 10),
                        tuple("K", 10)
                );
    }
}