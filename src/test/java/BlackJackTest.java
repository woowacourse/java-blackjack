import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import model.BlackJack;
import model.Card;
import model.participant.Participant;
import model.Participants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BlackJackTest {
    BlackJack blackJack;
    Participants participants;

    @BeforeEach
    void setUp() {
        participants = Participants.of("pobi,jason");
        Participant dealer = participants.getDealer();
        dealer.draw(Card.of("스페이드", 1));
        dealer.draw(Card.of("스페이드", 3));

        Participant player1 = participants.getPlayers().get(0);
        player1.draw(Card.of("하트", 1));
        player1.draw(Card.of("하트", 2));
        Participant player2 = participants.getPlayers().get(1);
        player2.draw(Card.of("하트", 5));
        player2.draw(Card.of("하트", 6));

        Map<Participant, Integer> betAmount = new HashMap<>();
        betAmount.put(player1, 10000);
        betAmount.put(player2, 10000);

        blackJack = BlackJack.from(participants, betAmount);
    }

    @Test
    void 전체_참여자들의_승패를_계산한다() {
        // when
        Map<String, Integer> dealerResult = blackJack.calculateDealerResult();
        Map<String, Boolean> playerResult = blackJack.calculatePlayerResult();

        assertThat(dealerResult.get("승")).isEqualTo(1);
        assertThat(dealerResult.get("패")).isEqualTo(1);

        assertThat(playerResult.get("pobi")).isEqualTo(Boolean.FALSE);
        assertThat(playerResult.get("jason")).isEqualTo(Boolean.TRUE);
    }

    @Test
    void 딜러와_참가자들의_최종_수익을_계산한다() {
        // given
        // when
        Map<String, Integer> calculatedRevenue = blackJack.calculateRevenue();

        // then

    }
}
