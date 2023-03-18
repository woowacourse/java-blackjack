package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class CardsTest {

    @Test
    @DisplayName("Cards는 자신의 점수를 정확히 계산할 수 있어야 한다.")
    void getScore_correct() {
        // given
        Cards cards = new Cards();
        cards.addCard(Card.of(Suit.DIAMOND, Rank.TWO));
        cards.addCard(Card.of(Suit.CLOVER, Rank.KING));

        // when
        int score = cards.getScore();

        // then
        assertThat(score)
                .isEqualTo(12);
    }

    @ParameterizedTest(name = "K다이아몬드와 에이스 {0}개면 점수가 {1}점이다.")
    @DisplayName("Cards에 ACE가 있을때 정확히 점수를 계산할 수 있어야 한다.")
    @CsvSource(value = {"1,21", "2,12", "3,13", "4,14"})
    void getScore_haveAce(int input, int expect) {
        // given
        Cards cards = new Cards();
        cards.addCard(Card.of(Suit.DIAMOND, Rank.QUEEN));

        for (int i = 0; i < input; i++) {
            cards.addCard(Card.of(Suit.DIAMOND, Rank.ACE));
        }

        // when
        int score = cards.getScore();

        // then
        assertThat(score)
                .isEqualTo(expect);
    }
}
