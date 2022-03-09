package blackjack.domain.player;

import blackjack.domain.card.Deck;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    @DisplayName("참가자는 시작시 카드를 2장 받는다.")
    void checkParticipantCardSize(){
        Deck deck = new Deck();
        Participant participant = new Participant(deck.initDistributeCard(), "pobi");
        Assertions.assertThat(participant.getCards().size()).isEqualTo(2);
    }
}