package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Deck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {
    Deck deck = new Deck();

    @DisplayName("전체 참가자의 수가 2명 이상 8명 이하면 생성에 성공한다.")
    @Test
    void validSizeTest() {
        List<String> playerNames = List.of("깃짱", "망고");
        Participants participants = new Participants(playerNames, deck);

        assertThat(participants.getPlayerNames()).isEqualTo(playerNames);
    }

    @DisplayName("전체 참가자의 수가 2명보다 작으면 예외 처리한다.")
    @Test
    void invalidUnderSizeTest() {
        assertThatThrownBy(() -> new Participants(List.of(), deck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Participants.SIZE_ERROR_MESSAGE);
        assertThatThrownBy(() -> new Participants(List.of("깃짱", "망고", "저문", "이리내", "디노", "오잉", "체인저", "토리"), deck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Participants.SIZE_ERROR_MESSAGE);
    }

    @DisplayName("전체 참가자의 수가 8명보다 크면 예외 처리한다.")
    @Test
    void invalidOverSizeTest() {
        assertThatThrownBy(() -> new Participants(List.of("깃짱", "망고", "체인저", "토리", "베로", "포이", "글렌", "네오", "솔라"), deck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Participants.SIZE_ERROR_MESSAGE);
    }
}
