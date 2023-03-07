package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParticipantTest {

    @Test
    @DisplayName("카드 한장씩 잘 받는지 테스트")
    void receiveCardTest() {
        Participant participant = new Player("IO");
        Card card = new Card(CardShape.CLOVER, CardNumber.FIVE);

        participant.receiveCard(card);

        List<Card> cards = participant.getCards();
        assertThat(cards.get(cards.size() - 1)).isEqualTo(card);
    }
}
