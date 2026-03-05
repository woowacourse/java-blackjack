package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DealerTest {

    // THINK @BeforeEach 생각해볼 것.

    @Test
    void 카드를_2장_나눠준다() {
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(Card.of(CardEmblem.EIGHT, CardDenomination.CLOVER), Card.of(CardEmblem.NINE, CardDenomination.SPADE))
                .build();

        Dealer dealer = Dealer.of(cardDeck);
        CardBundle cardBundle = dealer.handOutCard(2);

        Assertions.assertThat(cardBundle)
                .isEqualTo(CardBundle.of(List.of(
                        Card.of(CardEmblem.EIGHT, CardDenomination.CLOVER),
                        Card.of(CardEmblem.NINE, CardDenomination.SPADE)))
                );
    }

    @Test
    void 카드를_1장_나눠준다() {
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(Card.of(CardEmblem.EIGHT, CardDenomination.CLOVER), Card.of(CardEmblem.NINE, CardDenomination.SPADE))
                .build();

        Dealer dealer = Dealer.of(cardDeck);
        CardBundle cardBundle = dealer.handOutCard(1);

        Assertions.assertThat(cardBundle)
                .isEqualTo(CardBundle.of(List.of(Card.of(CardEmblem.EIGHT, CardDenomination.CLOVER))
                ));
    }

    @Test
    void 카드덱에_카드가없으면_딜러가_카드를_나눠줄_수_없다() {
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(Card.of(CardEmblem.EIGHT, CardDenomination.CLOVER))
                .build();

        Dealer dealer = Dealer.of(cardDeck);

        Assertions.assertThatThrownBy(() -> {
            CardBundle cardBundle = dealer.handOutCard(2);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
