package domain.participant;


import domain.card.Card;
import domain.card.CardDenomination;
import domain.card.CardEmblem;
import domain.card.Hand;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    void 참가자가_카드_2장을_뽑는다() {
        // given
        Card clover8 = Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER);
        Card clover9 = Card.of(CardDenomination.NINE, CardEmblem.CLOVER);
        List<Card> cards = List.of(clover8, clover9);
        Participant participant = new TestParticipant();

        // when
        Hand hand = participant.drawCards(cards);

        // then
        Assertions.assertThat(hand)
                .isEqualTo(Hand.from(cards));
    }

}
