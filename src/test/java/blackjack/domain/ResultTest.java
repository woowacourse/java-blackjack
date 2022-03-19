package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    private Participants participants;

    @BeforeEach
    void setGame() {
        List<String> playerNames = Arrays.asList("a", "b");
        BlackJack blackJack = BlackJack.createFrom(playerNames);
        participants = blackJack.getParticipants();
        List<Participant> players = participants.getPlayers();
        Participant dealer = participants.getDealer();

        players.get(0).bet(1000);
        players.get(1).bet(1200);
        participants.handOutCardTo(dealer, new Card("하트8", 8));
        participants.handOutCardTo(dealer, new Card("하트9", 9));
        participants.handOutCardTo(players.get(0), new Card("클로버8", 8));
        participants.handOutCardTo(players.get(0), new Card("하트10", 10));
        participants.handOutCardTo(players.get(1), new Card("클로버7", 7));
        participants.handOutCardTo(players.get(1), new Card("스페이드9", 9));
    }

    @Test
    @DisplayName("딜러의 수익을 잘 계산하는지")
    void Calculate_Dealer_Revenue() {
        Result result = new Result(participants);

        result.calculatePlayersRevenue();

        assertThat(result.getDealerRevenue()).isEqualTo(200);
    }

    @Test
    @DisplayName("플레이어가 승리한 경우 수익을 잘 계산하는지")
    void Calculate_Winning_Player_Revenue() {
        Result result = new Result(participants);

        result.calculatePlayersRevenue();

        assertThat(result.getPlayerRevenue(participants.getPlayers().get(0))).isEqualTo(1000);
    }

    @Test
    @DisplayName("플레이어가 패배한 경우 수익을 잘 계산하는지")
    void Calculate_Losing_Player_Revenue() {
        Result result = new Result(participants);

        result.calculatePlayersRevenue();
        
        assertThat(result.getPlayerRevenue(participants.getPlayers().get(1))).isEqualTo(-1200);
    }
}
