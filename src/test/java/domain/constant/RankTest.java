package domain.constant;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @Test
    void ACE_랭크는_기본값으로_1점을_갖는다() {
        int score = Rank.ACE.getScore();

        assertThat(score).isEqualTo(1);
    }

    @Test
    void 랭크_숫자에_해당하는_점수를_갖는다() {
        List<Rank> scores = List.of(Rank.THREE, Rank.TEN);

        assertThat(scores)
                .extracting(Rank::getScore)
                .containsExactly(3, 10);
    }

    @Test
    void J_Q_K_랭크는_10점을_갖는다() {
        List<Rank> scores = List.of(Rank.JACK, Rank.QUEEN, Rank.KING);

        assertThat(scores)
                .extracting(Rank::getScore)
                .containsOnly(10);
    }

    @Test
    void 랭크에_해당하는_이름을_반환한다() {
        List<String> rankNames = Arrays.stream(Rank.values())
                .map(Rank::getName)
                .toList();

        assertThat(rankNames).containsExactly(
                "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"
        );
    }
}