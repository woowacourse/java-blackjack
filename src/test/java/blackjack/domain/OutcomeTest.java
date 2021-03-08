package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OutcomeTest {

    @DisplayName("딜러가 버스터가 아닐때, 스코어가 높거나, 플레이어가 버스터가 되면 딜러가 이긴다")
    @Test
    void dealerWinTest() {
        //given
        int dealerScore = 14;
        int playerScore = 13;
        int busterPlayerScore = 24;

        //when
        Outcome firstOutcome = Outcome.findOutcome(dealerScore, playerScore);
        Outcome secondOutCome = Outcome.findOutcome(dealerScore, busterPlayerScore);

        //then
        assertThat(firstOutcome).isEqualTo(Outcome.WIN);
        assertThat(secondOutCome).isEqualTo(Outcome.WIN);
    }

    @DisplayName("딜러가 버스터일 때, 플레이어가 버스터가 되면 딜러가 이긴다")
    @Test
    void dealerWinWhenBusterTest() {
        //given
        int dealerScore = 25;
        int busterPlayerScore = 24;

        //when
        Outcome firstOutcome = Outcome.findOutcome(dealerScore, busterPlayerScore);

        //then
        assertThat(firstOutcome).isEqualTo(Outcome.WIN);
    }

    @DisplayName("플레이어가 버스터가 아닐때, 스코어가 높거나, 딜러가 버스터가 되면 딜러가 이긴다")
    @Test
    void playerWinTest() {
        //given
        int playerScore = 14;
        int dealerScore = 13;
        int BusterDealerScore = 24;

        //when
        Outcome firstOutcome = Outcome.getPlayerOutcome(Outcome.findOutcome(dealerScore, playerScore));
        Outcome secondOutCome = Outcome.getPlayerOutcome(Outcome.findOutcome(BusterDealerScore, playerScore));

        //then
        assertThat(firstOutcome).isEqualTo(Outcome.WIN);
        assertThat(secondOutCome).isEqualTo(Outcome.WIN);
    }

    @DisplayName("플레이어, 딜러가 버스터가 아닐때, 스코어가 동일하면 무승부를 출력한")
    @Test
    void playerDrawTest() {
        //given
        int playerScore = 14;
        int dealerScore = 14;

        //when
        Outcome firstOutcome = Outcome.getPlayerOutcome(Outcome.findOutcome(dealerScore, playerScore));

        //then
        assertThat(firstOutcome).isEqualTo(Outcome.DRAW);
    }

    @DisplayName("플레이어, 딜러가 버스터일 때, 스코어가 동일하면 딜러가 이긴다.한")
    @Test
    void playerBusterDrawTest() {
        //given
        int dealerScore = 24;
        int playerScore = 24;

        //when
        Outcome firstOutcome = Outcome.findOutcome(dealerScore, playerScore);

        //then
        assertThat(firstOutcome).isEqualTo(Outcome.WIN);
    }
}
