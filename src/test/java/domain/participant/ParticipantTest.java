package domain.participant;


import static config.BlackjackGameConstant.INITIAL_CARD_DRAW_COUNT;

import domain.card.Card;
import domain.card.CardBundle;
import domain.card.CardDeck;
import domain.card.CardDeckBuilder;
import domain.card.CardDenomination;
import domain.card.CardEmblem;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    void 참가자가_카드_2장을_뽑는다() {
        Card clover8 = Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER);
        Card clover9 = Card.of(CardDenomination.NINE, CardEmblem.CLOVER);
        List<Card> cards = List.of(clover8, clover9);
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(cards)
                .build();
        Participant participant = new TestParticipant();
        CardBundle cardBundle = participant.drawCards(cardDeck, INITIAL_CARD_DRAW_COUNT);

        Assertions.assertThat(cardBundle)
                .isEqualTo(CardBundle.from(cards));
    }

    @Test
    void 덱에_카드가_없는_경우_카드를_뽑을_수_없다() {
        Card clover8 = Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER);
        List<Card> cards = List.of(clover8);

        CardDeck cardDeck = new CardDeckBuilder()
                .cards(cards)
                .build();

        Dealer dealer = Dealer.from();

        Assertions.assertThatThrownBy(() -> {
            dealer.drawCards(cardDeck, INITIAL_CARD_DRAW_COUNT);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
