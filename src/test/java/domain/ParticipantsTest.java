package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.user.Dealer;
import domain.user.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @DisplayName("모든 참가자에게 초기 카드를 지급한다.")
    @Test
    void 초기_카드_지급() {
        // given
        Participants participants = new Participants(List.of("name1", "name2"));
        participants.drawInitialCardsEachParticipant(new Deck());
        // when
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();
        // then
        assertThat(dealer.getCards()).hasSize(2);
        players.forEach(player -> assertThat(player.getCards()).hasSize(2));
    }
}