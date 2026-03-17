package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Hand;
import blackjack.domain.participant.Player;
import blackjack.exception.ExceptionMessage;
import java.util.List;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Test
    void 중복된_이름이_존재하면_예외를_반환한다() {
        // given
        Dealer dealer = new Dealer(new Hand(List.of()));
        List<Player> players = List.of(new Player("딜러", new Hand(List.of())));
        // when & then
        assertThatThrownBy(() -> new Participants(dealer, players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.DUPLICATED_PARTICIPANT_NAME.getMessage());
    }
}
