package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    void 카드덱에서_카드를_뽑는다() {
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(Card.of(CardEmblem.EIGHT, CardDenomination.CLOVER), Card.of(CardEmblem.NINE, CardDenomination.SPADE))
                .build();
        Card card = cardDeck.giveCard();

        Assertions.assertThat(card).isEqualTo(Card.of(CardEmblem.EIGHT, CardDenomination.CLOVER));
    }

    @Test
    void 카드덱에_카드가_없으면_예외가_발생한다() {
        CardDeck cardDeck = new CardDeckBuilder()
                .build();

        Assertions.assertThatThrownBy(() -> {
            Card card = cardDeck.giveCard();
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
