package domain;

import static domain.card.Rank.ACE;
import static domain.card.Rank.EIGHT;
import static domain.card.Rank.FIVE;
import static domain.card.Rank.FOUR;
import static domain.card.Rank.JACK;
import static domain.card.Rank.NINE;
import static domain.card.Rank.SEVEN;
import static domain.card.Rank.TWO;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;

import domain.card.Card;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    final Hands sum20Size2 = new Hands(
            List.of(new Card(NINE, SPADE), new Card(ACE, SPADE)));
    final Hands sum20Size3 = new Hands(
            List.of(new Card(FOUR, SPADE), new Card(FIVE, SPADE),
                    new Card(ACE, SPADE)));
    final Hands sum21Size2 = new Hands(
            List.of(new Card(JACK, HEART),
                    new Card(ACE, SPADE)));
    final Hands sum22Size3 = new Hands(
            List.of(new Card(NINE, HEART), new Card(EIGHT, SPADE),
                    new Card(FIVE, HEART)));
    final Hands blackJack = new Hands(
            List.of(new Card(JACK, HEART), new Card(ACE, SPADE)));

    @DisplayName("카드 합이 같고 카드 갯수가 같으면 무승부이다.")
    @Test
    void isTie() {
        // given
        final Hands dealerSum10Size3 = new Hands(
                List.of(new Card(SEVEN, SPADE), new Card(TWO, SPADE),
                        new Card(ACE, SPADE)));
        final Hands playerSum10Size3 = new Hands(
                List.of(new Card(FOUR, SPADE), new Card(FIVE, SPADE),
                        new Card(ACE, SPADE)));

        // when && then
        Assertions.assertThat(dealerSum10Size3.calculateResult(playerSum10Size3)).isEqualTo(Result.TIE);
    }

    @DisplayName("카드 합이 같은데 카드 갯수가 더 적으면 승리이다.")
    @Test
    void isWinBySize() {
        Assertions.assertThat(sum20Size2.calculateResult(sum20Size3)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("카드 합이 21이하이면서 21에 가까운 카드가 승리한다.")
    void isWin() {
        Assertions.assertThat(sum21Size2.calculateResult(sum20Size2)).isEqualTo(Result.WIN);
        Assertions.assertThat(sum20Size2.calculateResult(sum21Size2)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("카드 합이 21초과이면 패배한다.")
    void isLoseWhenCardSumGreater21() {
        Assertions.assertThat(sum22Size3.calculateResult(sum20Size2)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("blackjack이 이긴다.")
    void isWinBlackJack() {
        Assertions.assertThat(blackJack.calculateResult(sum20Size2)).isEqualTo(Result.WIN);
        Assertions.assertThat(sum20Size2.calculateResult(blackJack)).isEqualTo(Result.LOSE);
    }
}
