package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.card.CardDeck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @DisplayName("참가자 이름중 중복되는 이름이 있으면 예외를 발생한다.")
    @Test
    void construct_duplicate_name() {
        List<String> names = List.of("리버", "포키", "크리스", "리버");

        assertThatThrownBy(() -> new Participants(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참가자 이름 중 중복되는 이름이 있습니다.");
    }

    @DisplayName("참가자들 모두가 카드를 두장 뽑으면 새로운 Participants를 반환한다.")
    @Test
    void drawcards_new_Participants() {
        CardDeck deck = new CardDeck();
        Participants participants = new Participants(List.of("리버", "포키", "크리스"));
        Participants otherParticipants = participants.drawCardsBy(deck);

        assertThat(participants).isNotEqualTo(otherParticipants);
    }
}