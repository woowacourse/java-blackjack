package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Betting;
import blackjack.domain.Outcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Pattern;
import blackjack.util.BlackjackTestUtil;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PlayerTest {

    @Test
    @DisplayName("플레이어는 처음에 모든 카드를 보여준다.")
    void showEveryCard() {
        // given
        Card card1 = Card.of(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = Card.of(Pattern.CLOVER, Denomination.THREE);
        List<Card> cards = List.of(card1, card2);

        Player player = new Player(new Name("Player"), cards, new Betting(1000));

        // when
        List<Card> actual = player.showInitialCards();

        // then
        assertThat(actual).containsOnly(card1, card2);
    }

    @ParameterizedTest
    @CsvSource(value = {"WIN_BLACKJACK,1500", "WIN,1000", "DRAW,0", "LOSE,-1000"})
    @DisplayName("승패 결과에 따라 수익을 계산한다.")
    void calculateProfit(String outcomeName, int expected) {
        // given
        Player player = BlackjackTestUtil.createPlayer(21);

        // when
        int profit = player.calculateProfit(Outcome.valueOf(outcomeName));

        // then
        assertThat(profit).isEqualTo(expected);
    }
}
