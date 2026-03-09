package domain.card;

import domain.card.exception.CardException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.CardDeckBuilder;

public class CardDeckTest {

    @Test
    void 카드덱에서_카드를_뽑는다() {
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER), Card.of(CardDenomination.NINE, CardEmblem.SPADE))
                .build();
        Card card = cardDeck.giveCard();

        Assertions.assertThat(card).isEqualTo(Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER));
    }

    @Test
    void 카드덱에_카드가_없으면_예외가_발생한다() {
        CardDeck cardDeck = new CardDeckBuilder()
                .build();

        Assertions.assertThatThrownBy(() -> {
            Card card = cardDeck.giveCard();
        }).isInstanceOf(CardException.class);
    }

}
