package blackjack.domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import blackjack.domain.card.Deck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantsTest {

    @Test
    @DisplayName("참가자들은 딜러와 플레이어들로 구성된다.")
    void createParticipants() {

        Participants participants = Participants.from(List.of("마루", "엔젤앤지"));

        assertThat(participants.getParticipants().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("플레이어가 1명 미만일 시 오류 발생")
    void createPlayersNumberException() {
        List<String> players = List.of();

        assertThatThrownBy(() -> Participants.from(players))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어가 8명 초과일 시 오류 발생")
    void createPlayersNumberException2() {
        List<String> players = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9");

        assertThatThrownBy(() -> Participants.from(players))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("참가자들에게 카드를 2장씩 나눠준다.")
    void handOutInitialCards() {
        List<String> players = List.of("앤지", "엔젤");
        Participants participants = Participants.from(players);
        Deck deck = new Deck();

        participants.handOutInitialCards(deck);
        int playerCardSize = participants.getPlayers().get(0).getCards().size();

        assertThat(playerCardSize).isEqualTo(2);
    }

}
