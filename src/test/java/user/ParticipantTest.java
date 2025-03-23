package user;

import card.CardDeck;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {
    @DisplayName("카드를 뽑았을 때 21을 넘기면 버스트된다")
    @Test
    void test3() {
        // given
        Participant participant = new Player("수양");
        CardDeck cardDeck = new CardDeck();
        participant.drawCard(cardDeck.drawCard());
        participant.drawCard(cardDeck.drawCard());
        for (int i = 0; i < 12; i++) {
            participant.drawCard(cardDeck.drawCard());
        }

        // when && then
        Assertions.assertThat(participant.isBust()).isEqualTo(true);
    }
}