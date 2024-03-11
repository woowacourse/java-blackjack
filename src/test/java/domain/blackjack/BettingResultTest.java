package domain.blackjack;

import domain.participant.Name;
import domain.participant.Participant;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

class BettingResultTest {

    @DisplayName("참가자가 승리했을 시 수익을 계산한다.")
    @Test
    void getPayoutWhenWin() {
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

    @DisplayName("참가자가 패배했을 시 수익을 계산한다.")
    @Test
    void getPayoutWhenLose() {
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

    @DisplayName("참가자가 블랙잭이고 딜러는 블랙잭이 아닐 시 수익을 계산한다.")
    @Test
    void getPayoutWhenBlackJack() {
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

    @DisplayName("참가자와 딜러 모두 블랙잭일 시 수익을 계산한다.")
    @Test
    void getPayoutWhenDraw() {
        LinkedHashMap<Participant, BetAmount> bet = new LinkedHashMap<>();
        Participant participant = new Participant(new Name("one"));
        LinkedHashMap<Participant, WinStatus> result = new LinkedHashMap<>();
        result.put(participant, WinStatus.DRAW);
        BlackJackResult blackJackResult = new BlackJackResult(result);
        bet.put(participant, new BetAmount(10_000));
        BettingResult bettingResult = new BettingResult(bet, blackJackResult);

        double payout = bettingResult.getPayout(participant);

        Assertions.assertThat(payout).isZero();
    }

    @DisplayName("딜러의 수익을 계산한다.")
    @Test
    void getDealerPayout() {
        LinkedHashMap<Participant, BetAmount> bet = new LinkedHashMap<>();
        LinkedHashMap<Participant, WinStatus> result = new LinkedHashMap<>();
        Participant one = new Participant(new Name("one"));
        Participant two = new Participant(new Name("one"));
        Participant three = new Participant(new Name("one"));
        bet.put(one, new BetAmount(10_000));
        result.put(one, WinStatus.WIN);
        bet.put(two, new BetAmount(15_000));
        result.put(two, WinStatus.LOSE);
        bet.put(three, new BetAmount(10_000));
        result.put(three, WinStatus.BLACKJACK);
        BlackJackResult blackJackResult = new BlackJackResult(result);
        BettingResult bettingResult = new BettingResult(bet, blackJackResult);

        double payout = bettingResult.getDealerPayout();

        Assertions.assertThat(payout).isEqualTo(-10_000);
    }
}
