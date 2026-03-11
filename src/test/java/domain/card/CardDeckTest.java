package domain.card;

import static config.BlackjackGameConstant.INITIAL_CARD_DRAW_COUNT;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CardDeckTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void 덱에서_카드를_정해진_개수만큼_뽑는다(int count) {
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER),
                        Card.of(CardDenomination.NINE, CardEmblem.SPADE))
                .build();

        CardBundle cardBundle = CardBundle.empty();
        cardDeck.draw(cardBundle, 2);

        Assertions.assertThat(cardBundle)
                .isEqualTo(CardBundle.from(List.of(
                        Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER),
                        Card.of(CardDenomination.NINE, CardEmblem.SPADE)))
                );
    }

    @Test
    void 카드덱에_카드가없으면_카드를_뽑을_수_없다() {
        CardDeck cardDeck = new CardDeckBuilder()
                .cards(Card.of(CardDenomination.EIGHT, CardEmblem.CLOVER))
                .build();

        CardBundle cardBundle = CardBundle.empty();

        Assertions.assertThatThrownBy(() -> {
            cardDeck.draw(cardBundle, INITIAL_CARD_DRAW_COUNT);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
