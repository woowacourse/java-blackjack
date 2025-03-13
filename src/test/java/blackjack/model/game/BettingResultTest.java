package blackjack.model.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Participants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BettingResultTest {

    private Dealer dealer;
    private Participant participant1;
    private Participant participant2;
    private Participants participants;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        participant1 = new Participant("벡터");
        participant2 = new Participant("한스");
        participants = new Participants(List.of(participant1, participant2));
        participant1.setBetting(10000);
        participant2.setBetting(1000);
    }

    @Test
    void 참가자가_승_이면_배팅금액만큼_얻고_패면_배팅금액을_잃는다() {
        // given

        Map<Participant, ParticipantResult> winLoseResult = new HashMap<>();
        winLoseResult.put(participant1, ParticipantResult.WIN);
        winLoseResult.put(participant2, ParticipantResult.LOSE);

        BettingResult bettingResult = new BettingResult(winLoseResult);

        int participant1Result = bettingResult.getBettingResult().get(participant1);
        int participant2Result = bettingResult.getBettingResult().get(participant2);
        // then
        assertThat(participant1Result).isEqualTo(10000);
        assertThat(participant2Result).isEqualTo(-1000);
    }

    @Test
    void 참가자가_블랙잭이면_1_5배_얻는다() {
        // given

        Map<Participant, ParticipantResult> winLoseResult = new HashMap<>();
        winLoseResult.put(participant1, ParticipantResult.BLACKJACK);
        winLoseResult.put(participant2, ParticipantResult.LOSE);

        BettingResult bettingResult = new BettingResult(winLoseResult);

        int participant1Result = bettingResult.getBettingResult().get(participant1);
        int participant2Result = bettingResult.getBettingResult().get(participant2);
        // then
        assertThat(participant1Result).isEqualTo(15000);
    }

    @Test
    void 참가자가_무승부면_0원을_얻는다() {
        // given

        Map<Participant, ParticipantResult> winLoseResult = new HashMap<>();
        winLoseResult.put(participant1, ParticipantResult.DRAW);
        winLoseResult.put(participant2, ParticipantResult.LOSE);

        BettingResult bettingResult = new BettingResult(winLoseResult);

        int participant1Result = bettingResult.getBettingResult().get(participant1);
        int participant2Result = bettingResult.getBettingResult().get(participant2);
        // then
        assertThat(participant1Result).isEqualTo(0);
    }

}
