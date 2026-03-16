package domain.participant;



import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardDeckBuilder;
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
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(cards)
                .build();
        Participant participant = new TestParticipant();

        // when
        Hand hand = participant.drawCards(cardDeck, 2);

        // then
        Assertions.assertThat(hand)
                .isEqualTo(Hand.from(cards));
    }

    @Test
    void 덱에_카드가_없는_경우_카드를_뽑을_수_없다() {
        // given
        Card clover8 = Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER);
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(List.of(clover8))
                .build();
        Dealer dealer = Dealer.create();

        // when & then
        Assertions.assertThatThrownBy(() -> {
            dealer.drawCards(cardDeck, 2);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
