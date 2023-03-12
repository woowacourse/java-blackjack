package blackjack.domain.card;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Score;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardsTest {

    @Test
    @DisplayName("Cards는 자신의 점수를 정확히 계산할 수 있어야 한다.")
    void calculateScore_correct() {
        // given
        Cards cards = new Cards(List.of(
                Card.of(Suit.DIAMOND, Rank.TWO),
                Card.of(Suit.CLOVER, Rank.KING)
        ));

        // when
        Score score = cards.calculateScore();

        // then
        assertThat(score.getScore())
                .isEqualTo(12);
    }

    @ParameterizedTest
    @DisplayName("Cards에 ACE가 있을때 정확히 점수를 계산할 수 있어야 한다.")
    @CsvSource(value = {"1,11", "2,12", "3,13", "4,14"})
    void calculateScore_haveAce(int input, int expect) {
        // given
        Cards cards = IntStream.range(0, input)
                .mapToObj(v -> Card.of(Suit.DIAMOND, Rank.ACE))
                .collect(collectingAndThen(toList(), Cards::new));

        // when
        Score score = cards.calculateScore();

        // then
        assertThat(score.getScore())
                .isEqualTo(expect);
    }

    @Test
    @DisplayName("카드가 2장이고, 21점이면 블랙잭이여야 한다.")
    void isBlackjack_twoCardsAnd21Score() {
        // given
        Cards cards = new Cards(List.of(
                Card.of(Suit.DIAMOND, Rank.ACE),
                Card.of(Suit.DIAMOND, Rank.KING)
        ));

        // expect
        assertThat(cards.isBlackjack())
                .isTrue();
    }

    @Test
    @DisplayName("카드가 2장이여도 21점이 아니면 블랙잭이 아니여야 한다.")
    void isBlackjack_false() {
        // given
        Cards cards = new Cards(List.of(
                Card.of(Suit.DIAMOND, Rank.KING),
                Card.of(Suit.DIAMOND, Rank.KING)
        ));

        // expect
        assertThat(cards.isBlackjack())
                .isFalse();
    }

    @Test
    @DisplayName("카드의 점수가 21점이어도 2장이 아니면 블랙잭이 아니여야 한다.")
    void isBlackjack_falseNotTwoCards() {
        // given
        Cards cards = new Cards(List.of(
                Card.of(Suit.DIAMOND, Rank.ACE),
                Card.of(Suit.DIAMOND, Rank.EIGHT),
                Card.of(Suit.DIAMOND, Rank.TWO)
        ));

        // expect
        assertThat(cards.isBlackjack())
                .isFalse();
    }
}
