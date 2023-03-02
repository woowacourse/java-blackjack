package blackjack.domain;

import static blackjack.domain.Number.JACK;
import static blackjack.domain.Symbol.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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

    @DisplayName("참가자는 카드를 받으면 마지막 위치에 저장한다.")
    @Test
    void should_addCard_At_LastIndex() {
        Participant participant = new Participant();

        Card card = new Card(SPADE, JACK);
        participant.take(card);

        List<Card> cards = participant.getCards();
        Card lastCard = cards.get(cards.size() - 1);
        assertThat(lastCard).isEqualTo(card);
    }
}
