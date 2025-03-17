package user;

import card.CardDeck;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.Dealer;
import user.Participants;
import user.Player;
import user.User;

class ParticipantsTest {
    @DisplayName("모든 참여자가 한 장의 카드를 배부받는다.")
    @Test
    void test() {
        // given
        List<User> users = List.of(new Player("수양"), new Player("뫄뫄"), new Dealer());
        Participants participants = new Participants(users);
        CardDeck cardDeck = new CardDeck();

        // when
        participants.drawCardAllParticipant(cardDeck);

        // then
        Assertions.assertThat(participants.getDealer().getSize()).isEqualTo(1);
        for (User player : participants.getPlayers()) {
            Assertions.assertThat(player.getSize()).isEqualTo(1);
        }
    }
}