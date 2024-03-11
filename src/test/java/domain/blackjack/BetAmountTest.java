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
        LinkedHashMap<Participant, WinStatus> result = new LinkedHashMap<>();
        result.put(participant, WinStatus.WIN);
        BlackJackResult blackJackResult = new BlackJackResult(result);
        bet.put(participant, 10_000);
        BetAmount betAmount = new BetAmount(bet, blackJackResult);
        double payout = betAmount.getPayout(participant);

        Assertions.assertThat(payout).isEqualTo(10_000);
    }

    @Test
    void name1() {
        LinkedHashMap<Participant, Integer> bet = new LinkedHashMap<>();
        Participant participant = new Participant(new Name("one"));
        LinkedHashMap<Participant, WinStatus> result = new LinkedHashMap<>();
        result.put(participant, WinStatus.LOSE);
        BlackJackResult blackJackResult = new BlackJackResult(result);
        bet.put(participant, 10_000);
        BetAmount betAmount = new BetAmount(bet, blackJackResult);
        double payout = betAmount.getPayout(participant);

        Assertions.assertThat(payout).isEqualTo(-10_000);
    }

    @Test
    void name2() {
        LinkedHashMap<Participant, Integer> bet = new LinkedHashMap<>();
        Participant participant = new Participant(new Name("one"));
        LinkedHashMap<Participant, WinStatus> result = new LinkedHashMap<>();
        result.put(participant, WinStatus.BLACKJACK);
        BlackJackResult blackJackResult = new BlackJackResult(result);
        bet.put(participant, 10_000);
        BetAmount betAmount = new BetAmount(bet, blackJackResult);
        double payout = betAmount.getPayout(participant);

        Assertions.assertThat(payout).isEqualTo(15_000);
    }

    @Test
    void name3() {
        LinkedHashMap<Participant, Integer> bet = new LinkedHashMap<>();
        LinkedHashMap<Participant, WinStatus> result = new LinkedHashMap<>();
        Participant one = new Participant(new Name("one"));
        Participant two = new Participant(new Name("one"));
        Participant three = new Participant(new Name("one"));
        result.put(one, WinStatus.WIN);
        bet.put(one, 10_000);
        result.put(two, WinStatus.LOSE);
        bet.put(two, 15_000);
        result.put(three, WinStatus.BLACKJACK);
        bet.put(three, 10_000);
        BlackJackResult blackJackResult = new BlackJackResult(result);
        BetAmount betAmount = new BetAmount(bet, blackJackResult);
        double payout = betAmount.getDealerPayout();

        Assertions.assertThat(payout).isEqualTo(-10_000);
    }
}
