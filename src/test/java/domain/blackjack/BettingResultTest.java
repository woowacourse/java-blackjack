package domain.blackjack;

import domain.participant.Name;
import domain.participant.Participant;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

class BettingResultTest {
    @Test
    void name() {
        LinkedHashMap<Participant, BetAmount> bet = new LinkedHashMap<>();
        Participant participant = new Participant(new Name("one"));
        LinkedHashMap<Participant, WinStatus> result = new LinkedHashMap<>();
        result.put(participant, WinStatus.WIN);
        BlackJackResult blackJackResult = new BlackJackResult(result);
        bet.put(participant, new BetAmount(10_000));
        BettingResult bettingResult = new BettingResult(bet, blackJackResult);
        double payout = bettingResult.getPayout(participant);

        Assertions.assertThat(payout).isEqualTo(10_000);
    }

    @Test
    void name1() {
        LinkedHashMap<Participant, BetAmount> bet = new LinkedHashMap<>();
        Participant participant = new Participant(new Name("one"));
        LinkedHashMap<Participant, WinStatus> result = new LinkedHashMap<>();
        result.put(participant, WinStatus.LOSE);
        BlackJackResult blackJackResult = new BlackJackResult(result);
        bet.put(participant, new BetAmount(10_000));
        BettingResult bettingResult = new BettingResult(bet, blackJackResult);
        double payout = bettingResult.getPayout(participant);

        Assertions.assertThat(payout).isEqualTo(-10_000);
    }

    @Test
    void name2() {
        LinkedHashMap<Participant, BetAmount> bet = new LinkedHashMap<>();
        Participant participant = new Participant(new Name("one"));
        LinkedHashMap<Participant, WinStatus> result = new LinkedHashMap<>();
        result.put(participant, WinStatus.BLACKJACK);
        BlackJackResult blackJackResult = new BlackJackResult(result);
        bet.put(participant, new BetAmount(10_000));
        BettingResult bettingResult = new BettingResult(bet, blackJackResult);
        double payout = bettingResult.getPayout(participant);

        Assertions.assertThat(payout).isEqualTo(15_000);
    }

    @Test
    void name3() {
        LinkedHashMap<Participant, BetAmount> bet = new LinkedHashMap<>();
        LinkedHashMap<Participant, WinStatus> result = new LinkedHashMap<>();
        Participant one = new Participant(new Name("one"));
        Participant two = new Participant(new Name("one"));
        Participant three = new Participant(new Name("one"));
        result.put(one, WinStatus.WIN);
        bet.put(one, new BetAmount(10_000));
        result.put(two, WinStatus.LOSE);
        bet.put(two, new BetAmount(15_000));
        result.put(three, WinStatus.BLACKJACK);
        bet.put(three, new BetAmount(10_000));
        BlackJackResult blackJackResult = new BlackJackResult(result);
        BettingResult bettingResult = new BettingResult(bet, blackJackResult);
        double payout = bettingResult.getDealerPayout();

        Assertions.assertThat(payout).isEqualTo(-10_000);
    }
}
