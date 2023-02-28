package blackjack.domain;

import static blackjack.domain.Number.JACK;
import static blackjack.domain.Symbol.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParticipantTest {

    @DisplayName("참가자는 카드를 받으면 카드의 수가 1 증가한다.")
    @Test
    void should_HasSize_1Increased() {
        Participant participant = new Participant();
        int previousSize = participant.getCards().size();

        Card card = new Card(SPADE, JACK);
        participant.take(card);
        int currentSize = participant.getCards().size();

        assertThat(currentSize).isEqualTo(previousSize + 1);
    }
}
