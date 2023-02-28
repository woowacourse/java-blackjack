package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.util.List;
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
        int score = cards.getScore();

        // then
        assertThat(score)
                .isEqualTo(12);
    }

    @ParameterizedTest
    @DisplayName("Cards에 ACE가 있을때 정확히 점수를 계산할 수 있어야 한다.")
    @CsvSource(value = {"1,21", "2,12", "3,13", "4,14"})
    void getScore_haveAce(int input, int expect) {
        // given
        Cards cards = new Cards();
        cards.addCard(new Card(Suit.DIAMOND, Rank.QUEEN));

        for (int i = 0; i < input; i++) {
            cards.addCard(new Card(Suit.DIAMOND, Rank.ACE));
        }

        // when
        int score = cards.getScore();

        // then
        assertThat(score)
                .isEqualTo(expect);
    }

    @Test
    @DisplayName("카드를 뽑을 때 첫 번째 카드를 뽑아서 반환한다.")
    void drawCard_success() {
        // given
        Cards cards = new Cards();
        cards.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        cards.addCard(new Card(Suit.HEART, Rank.FOUR));
        cards.addCard(new Card(Suit.SPADE, Rank.SIX));

        // when
        Card card = cards.drawCard();

        // then
        assertThat(card)
                .isEqualTo(new Card(Suit.DIAMOND, Rank.THREE));
    }

    @Test
    @DisplayName("카드를 뽑을 때 카드가 없으면 예외가 발생해야 한다.")
    void drawCard_empty() {
        // given
        Cards cards = new Cards();

        // expect
        assertThatIllegalStateException()
                .isThrownBy(cards::drawCard)
                .withMessage("[ERROR] 남은 카드가 없습니다.");
    }
}
