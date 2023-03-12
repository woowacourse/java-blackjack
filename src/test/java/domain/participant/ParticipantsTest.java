package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Deck;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {
    Deck deck = new Deck();

    @DisplayName("전체 참가자의 수가 2명 이상 8명 이하면 생성에 성공한다.")
    @Test
    void validSizeTest() {
        Map<String, BettingMoney> players = new HashMap<>();
        players.put("깃짱", new BettingMoney(1000));
        players.put("망고", new BettingMoney(2000));
        Participants participants = new Participants(players, deck);

        assertThat(participants.getPlayers().size()).isEqualTo(2);
    }

    @DisplayName("전체 참가자의 수가 2명보다 작으면 예외 처리한다.")
    @Test
    void invalidUnderSizeTest() {
        assertThatThrownBy(() -> new Participants(new HashMap<>(), deck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Participants.SIZE_ERROR_MESSAGE);
    }

    @DisplayName("전체 참가자의 수가 8명보다 크면 예외 처리한다.")
    @Test
    void invalidOverSizeTest() {
        Map<String, BettingMoney> players = new HashMap<>();
        List<String> playerNames = List.of("깃짱", "망고", "체인저", "토리", "베로", "포이", "글렌", "네오", "솔라");
        playerNames.forEach(playerName -> players.put(playerName, new BettingMoney(1000)));
        assertThatThrownBy(() -> new Participants(players, deck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Participants.SIZE_ERROR_MESSAGE);
    }
}
