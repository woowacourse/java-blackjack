package blackJack.domain;

import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackGameTest {

    @Test
    @DisplayName("BlackJackGame 생성 테스트")
    void createValidDealer() {
        Participants participants = new Participants(new Dealer(), List.of(new Player("rookie")));

        assertThat(new BlackJackGame(participants)).isNotNull();
    }
}
