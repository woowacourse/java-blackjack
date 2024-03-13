package domain.participant;

import domain.blackjack.BlackJackResult;
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
        //given
        Participant participant = new Participant(new Name("rush"));
        BetAmount betAmount = BetAmount.from(1000);

        Dealer dealer = new Dealer();

        LinkedHashMap<Participant, BetAmount> betAmountByParticipant = new LinkedHashMap<>();
        betAmountByParticipant.put(participant, betAmount);
        BetResult betResult = new BetResult(betAmountByParticipant);

        LinkedHashMap<Participant, WinStatus> winStatusByParticipant = new LinkedHashMap<>();
        winStatusByParticipant.put(participant, WinStatus.WIN);

        //when
        betResult.updateToProfit(winStatusByParticipant);
        //then
        BetAmount findProfit = betResult.findByParticipant(participant);
        assertThat(findProfit.getValue()).isEqualTo(1000);
    }

    @DisplayName("플레이어가 패배시, 배팅 금액을 모두 잃는다.")
    @Test
    void profitWhenLose() {
        //given
        Participant participant = new Participant(new Name("rush"));
        BetAmount betAmount = BetAmount.from(1000);

        Dealer dealer = new Dealer();

        LinkedHashMap<Participant, BetAmount> betAmountByParticipant = new LinkedHashMap<>();
        betAmountByParticipant.put(participant, betAmount);
        BetResult betResult = new BetResult(betAmountByParticipant);

        LinkedHashMap<Participant, WinStatus> winStatusByParticipant = new LinkedHashMap<>();
        winStatusByParticipant.put(participant, WinStatus.LOSE);

        //when
        betResult.updateToProfit(winStatusByParticipant);
        //then
        BetAmount findProfit = betResult.findByParticipant(participant);
        assertThat(findProfit.getValue()).isEqualTo(-1000);
    }

    @DisplayName("처음 두장의 카드가 블랙잭일 경우, 배팅 금액의 1.5배를 딜러에게 받는다.")
    @Test
    void profitWhenBlackJack() {
        //given
        Participant participant = new Participant(new Name("rush"));
        BetAmount betAmount = BetAmount.from(1000);

        Dealer dealer = new Dealer();

        LinkedHashMap<Participant, BetAmount> betAmountByParticipant = new LinkedHashMap<>();
        betAmountByParticipant.put(participant, betAmount);
        BetResult betResult = new BetResult(betAmountByParticipant);

        LinkedHashMap<Participant, WinStatus> winStatusByParticipant = new LinkedHashMap<>();
        winStatusByParticipant.put(participant, WinStatus.BLACKJACK);

        //when
        betResult.updateToProfit(winStatusByParticipant);
        //then
        BetAmount findProfit = betResult.findByParticipant(participant);
        assertThat(findProfit.getValue()).isEqualTo(1500);
    }

}
