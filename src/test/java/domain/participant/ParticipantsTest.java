package domain.participant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ParticipantsTest {

    private Dealer dealer;
    private Players players;

    @BeforeEach
    void init() {
        dealer = Dealer.createDealer();
        players = Players.create(List.of("pobi", "crong"));
    }

    @Test
    @DisplayName("create()는 호출하면, 참가자들을 생성한다")
    void create_whenCall_thenSuccess() {
        final Participants participants = assertDoesNotThrow(() -> Participants.create(dealer, players.getPlayers()));
        assertThat(participants).isExactlyInstanceOf(Participants.class);
    }
}
