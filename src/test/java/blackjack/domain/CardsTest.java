package blackjack.domain;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardsTest {

    @Test
    @DisplayName("Cards는 자신의 점수를 정확히 계산할 수 있어야 한다.")
    void getScore_correct() {
        // given
        Cards cards = new Cards(List.of(
                new Card(Suit.DIAMOND, Rank.TWO),
                new Card(Suit.CLOVER, Rank.KING)
        ));

        // when
        Score score = cards.getScore();

        // then
        assertThat(score.getValue())
                .isEqualTo(12);
    }

    @ParameterizedTest
    @DisplayName("Cards에 ACE가 있을때 정확히 점수를 계산할 수 있어야 한다.")
    @CsvSource(value = {"1,11", "2,12", "3,13", "4,14"})
    void getScore_haveAce(int input, int expect) {
        // given
        Cards cards = IntStream.range(0, input)
                .mapToObj(v -> new Card(Suit.DIAMOND, Rank.ACE))
                .collect(collectingAndThen(toList(), Cards::new));

        // when
        Score score = cards.getScore();

        // then
        assertThat(score.getValue())
                .isEqualTo(expect);
    }
}
