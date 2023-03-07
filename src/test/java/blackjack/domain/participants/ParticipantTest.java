package blackjack.domain.participants;

import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParticipantTest {

    private Participant player;

    @BeforeEach
    void setParticipant() {
        player = new Player("test");
    }

    @DisplayName("참가자는 카드를 받으면 카드의 수가 1 증가한다.")
    @Test
    void should_HasSize_1Increased() {
        int previousSize = player.getCards().size();

        Card card = new Card(SPADE, JACK);
        player.take(card);
        int currentSize = player.getCards().size();

        assertThat(currentSize).isEqualTo(previousSize + 1);
    }

    @DisplayName("참가자는 카드를 받으면 마지막 위치에 저장한다.")
    @Test
    void should_addCard_At_LastIndex() {
        Card card = new Card(SPADE, JACK);
        player.take(card);

        List<Card> cards = player.getCards();
        Card lastCard = cards.get(cards.size() - 1);
        assertThat(lastCard).isEqualTo(card);
    }
}
