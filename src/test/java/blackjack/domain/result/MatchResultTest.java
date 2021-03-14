package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.money.Profits;
import blackjack.domain.state.Bust;
import blackjack.domain.state.State;
import blackjack.domain.state.Stay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchResultTest {

    @Test
    @DisplayName("승패 확인")
    void checkPlayerMatchResult() {
        State playerStay = new Stay(new Cards(new Card(Shape.HEART, Denomination.TEN)));
        State dealerStay = new Stay(new Cards(new Card(Shape.HEART, Denomination.EIGHT)));

        State playerBust = new Bust(new Cards(
                new Card(Shape.HEART, Denomination.TEN),
                new Card(Shape.DIAMOND, Denomination.KING),
                new Card(Shape.DIAMOND, Denomination.TWO)
        ));
        State dealerStay2 = new Stay(new Cards(new Card(Shape.HEART, Denomination.TEN)));

        assertThat(MatchResult.getPlayerMatchResult(playerStay, dealerStay)).isEqualTo(MatchResult.WIN);
        assertThat(MatchResult.getPlayerMatchResult(playerBust, dealerStay)).isEqualTo(MatchResult.LOSE);
        assertThat(MatchResult.getPlayerMatchResult(playerStay, dealerStay2)).isEqualTo(MatchResult.DRAW);
    }

    @Test
    @DisplayName("결과에 따른 수익 변화")
    void finalProfitsByResult() {
        Profits profits = new Profits(new BigDecimal("1000"));

        assertThat(MatchResult.WIN.finalProfitByEachStatus(profits)).isEqualTo(new Profits(new BigDecimal("1000")));
        assertThat(MatchResult.LOSE.finalProfitByEachStatus(profits)).isEqualTo(new Profits(new BigDecimal("-1000")));
        assertThat(MatchResult.DRAW.finalProfitByEachStatus(profits)).isEqualTo(new Profits(BigDecimal.ZERO));
    }
}
