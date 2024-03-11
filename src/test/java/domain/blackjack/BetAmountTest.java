package domain.blackjack;

import domain.participant.Name;
import domain.participant.Participant;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

class BetAmountTest {
    @Test
    void name() {
        LinkedHashMap<Participant, Integer> bet = new LinkedHashMap<>();
        Participant participant = new Participant(new Name("one"));
        bet.put(participant, 10_000);
        BetAmount betAmount = new BetAmount(bet);
        double payout = betAmount.getPayout(participant, WinStatus.WIN);

        Assertions.assertThat(payout).isEqualTo(10_000);
    }

    @Test
    void name1() {
        LinkedHashMap<Participant, Integer> bet = new LinkedHashMap<>();
        Participant participant = new Participant(new Name("one"));
        bet.put(participant, 10_000);
        BetAmount betAmount = new BetAmount(bet);
        double payout = betAmount.getPayout(participant, WinStatus.LOSE);

        Assertions.assertThat(payout).isEqualTo(-10_000);
    }

    @Test
    void name2() {
        LinkedHashMap<Participant, Integer> bet = new LinkedHashMap<>();
        Participant participant = new Participant(new Name("one"));
        bet.put(participant, 10_000);
        BetAmount betAmount = new BetAmount(bet);
        double payout = betAmount.getPayout(participant, WinStatus.BLACKJACK);

        Assertions.assertThat(payout).isEqualTo(15_000);
    }
}
