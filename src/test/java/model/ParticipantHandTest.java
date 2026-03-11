package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ParticipantHandTest {
    @Test
    public void 카드_넣기_정상_작동() {
        ParticipantHand participantHand = new ParticipantHand();

        participantHand.addDeck(new Card(Shape.CLOVER, CardNumber.ACE));

        List<Card> cards = participantHand.getDeck();
        Card firstCard = cards.getFirst();

        assertThat(cards.size()).isEqualTo(1);
        assertThat(firstCard.shape()).isEqualTo(Shape.CLOVER);
        assertThat(firstCard.cardNumber()).isEqualTo(CardNumber.ACE);
    }

    @Test
    public void 카드_점수_정상_작동() {
        ParticipantHand participantHand = new ParticipantHand();

        participantHand.addDeck(new Card(Shape.CLOVER, CardNumber.ACE));
        assertThat(participantHand.getScore()).isEqualTo(11);

        participantHand.addDeck(new Card(Shape.CLOVER, CardNumber.KING));
        assertThat(participantHand.getScore()).isEqualTo(21);

        participantHand.addDeck(new Card(Shape.DIAMOND, CardNumber.ACE));
        assertThat(participantHand.getScore()).isEqualTo(12);
    }

    @Test
    public void 중복_카드_추가_예외() {
        ParticipantHand participantHand = new ParticipantHand();
        Card card = new Card(Shape.CLOVER, CardNumber.ACE);

        participantHand.addDeck(card);
        assertThatThrownBy(() -> participantHand.addDeck(card))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATED_CARD_IN_DECK.getMessage());

        assertThatThrownBy(() -> participantHand.addDeck(new Card(Shape.CLOVER, CardNumber.ACE)))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATED_CARD_IN_DECK.getMessage());
    }
}
