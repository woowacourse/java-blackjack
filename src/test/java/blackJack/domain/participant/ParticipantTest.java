package blackJack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    @DisplayName("참가자가 블랙잭인지 확인한다.")
    void isBlackJack() {
        Participant participant = new Player("parang");

        participant.hit(Card.of(Symbol.CLOVER, Denomination.ACE));
        participant.hit(Card.of(Symbol.CLOVER, Denomination.JACK));

        assertThat(participant.isBlackJack()).isTrue();
    }
}