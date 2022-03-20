package blackJack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Symbol;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    void 참가자가_블랙잭인지_확인한다() {
        Participant participant = new Player("parang");

        participant.hit(Card.of(Symbol.CLOVER, Denomination.ACE));
        participant.hit(Card.of(Symbol.CLOVER, Denomination.JACK));

        assertThat(participant.isBlackJack()).isTrue();
    }
}