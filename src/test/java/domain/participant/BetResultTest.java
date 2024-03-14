package domain.participant;

import domain.Bet.BetAmount;
import domain.Bet.BetResult;
import domain.blackjack.WinStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.*;

class BetResultTest {

    @DisplayName("게임 시작시 배팅 금액을 정한다.")
    @Test
    void startBet() {
        Participant participant = new Participant(new Name("rush"));
        BetAmount betAmount = BetAmount.from(1000);

        LinkedHashMap<Participant, BetAmount> betAmountByParticipant = new LinkedHashMap<>();
        betAmountByParticipant.put(participant, betAmount);
        BetResult betResult = new BetResult(betAmountByParticipant);

        BetAmount findBetAmount = betResult.findByParticipant(participant);
        assertThat(findBetAmount).isEqualTo(betAmount);
    }

    @DisplayName("플레이어가 승리시, 베팅 금액을 얻는다.")
    @Test
    void profitWhenWin() {
        Participant participant = new Participant(new Name("rush"));
        BetAmount betAmount = BetAmount.from(1000);

        LinkedHashMap<Participant, BetAmount> betAmountByParticipant = new LinkedHashMap<>();
        betAmountByParticipant.put(participant, betAmount);
        BetResult betResult = new BetResult(betAmountByParticipant);

        LinkedHashMap<Participant, WinStatus> winStatusByParticipant = new LinkedHashMap<>();
        winStatusByParticipant.put(participant, WinStatus.WIN);

        betResult.updateToProfit(winStatusByParticipant);
        BetAmount findProfit = betResult.findByParticipant(participant);
        assertThat(findProfit.getValue()).isEqualTo(1000);
    }

    @DisplayName("플레이어가 패배시, 배팅 금액을 모두 잃는다.")
    @Test
    void profitWhenLose() {
        Participant participant = new Participant(new Name("rush"));
        BetAmount betAmount = BetAmount.from(1000);

        LinkedHashMap<Participant, BetAmount> betAmountByParticipant = new LinkedHashMap<>();
        betAmountByParticipant.put(participant, betAmount);
        BetResult betResult = new BetResult(betAmountByParticipant);

        LinkedHashMap<Participant, WinStatus> winStatusByParticipant = new LinkedHashMap<>();
        winStatusByParticipant.put(participant, WinStatus.LOSE);

        betResult.updateToProfit(winStatusByParticipant);
        BetAmount findProfit = betResult.findByParticipant(participant);
        assertThat(findProfit.getValue()).isEqualTo(-1000);
    }

    @DisplayName("처음 두장의 카드가 블랙잭일 경우, 배팅 금액의 1.5배를 딜러에게 받는다.")
    @Test
    void profitWhenBlackJack() {
        Participant participant = new Participant(new Name("rush"));
        BetAmount betAmount = BetAmount.from(1000);

        LinkedHashMap<Participant, BetAmount> betAmountByParticipant = new LinkedHashMap<>();
        betAmountByParticipant.put(participant, betAmount);
        BetResult betResult = new BetResult(betAmountByParticipant);

        LinkedHashMap<Participant, WinStatus> winStatusByParticipant = new LinkedHashMap<>();
        winStatusByParticipant.put(participant, WinStatus.BLACKJACK);

        betResult.updateToProfit(winStatusByParticipant);
        BetAmount findProfit = betResult.findByParticipant(participant);
        assertThat(findProfit.getValue()).isEqualTo(1500);
    }

    @DisplayName("참가자와 딜러의 점수가 같을 경우 베팅한 돈을 돌려받는다. ")
    @Test
    void profitWhenPush() {
        Participant participant = new Participant(new Name("rush"));
        BetAmount betAmount = BetAmount.from(1000);

        LinkedHashMap<Participant, BetAmount> betAmountByParticipant = new LinkedHashMap<>();
        betAmountByParticipant.put(participant, betAmount);
        BetResult betResult = new BetResult(betAmountByParticipant);

        LinkedHashMap<Participant, WinStatus> winStatusByParticipant = new LinkedHashMap<>();
        winStatusByParticipant.put(participant, WinStatus.PUSH);

        betResult.updateToProfit(winStatusByParticipant);
        BetAmount findProfit = betResult.findByParticipant(participant);
        assertThat(findProfit.getValue()).isZero();
    }
}
