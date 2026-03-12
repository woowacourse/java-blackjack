package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Nested
    class GetPlayerTest {

        @Nested
        class Success {

            @Test
            void 이름에_해당하는_플레이어를_반환해야_한다() {

                // given
                Participants participants = createParticipants();
                Player expected = participants.players().getPlayer("jacob");

                // when
                Player actual = participants.getPlayer("jacob");

                // then
                assertThat(actual).isSameAs(expected);
            }

            @Test
            void 해당_이름의_플레이어가_없으면_null을_반환해야_한다() {

                // given
                Participants participants = createParticipants();

                // when
                Player actual = participants.getPlayer("brown");

                // then
                assertThat(actual).isNull();
            }
        }
    }

    private Participants createParticipants() {
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("jacob", "seoye"));
        return new Participants(dealer, players);
    }
}
