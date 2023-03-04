package domain.player.participant;

import domain.area.CardArea;
import domain.card.Card;
import domain.player.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.card.CardShape.CLOVER;
import static domain.card.CardShape.SPADE;
import static domain.card.CardValue.ACE;
import static domain.card.CardValue.TEN;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Participant 은")
class ParticipantTest {

    final CardArea cardArea = new CardArea(
            new Card(CLOVER, TEN),
            new Card(SPADE, TEN)
    );

    @Test
    @DisplayName("canHit() : 참여자는 21점 미만일 경우 카드를 더 받을 수 있다.")
    void test_canHit_underScore21() {
        // given
        final Participant participant = new Participant(new Name("player1"), cardArea);

        // when & then
        assertTrue(participant.canHit());
    }

    @Test
    @DisplayName("canHit() : 참여자는 21점 이상일 경우 카드를 더 받을 수 없다.")
    void test_canHit_overScore21() {
        // given
        final Participant participant = new Participant(new Name("player1"), cardArea);
        cardArea.addCard(new Card(CLOVER, ACE));

        // when & then
        assertFalse(participant.canHit());
    }
}
