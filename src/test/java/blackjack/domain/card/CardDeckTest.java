package blackjack.domain.card;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardDeckTest {
    private CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        cardDeck = new CardDeck();
    }

    @Test
    void 처음_카드_덱의_총_카드_수는_52장이다() {
        assertThat(cardDeck).extracting("cardDeck", InstanceOfAssertFactories.list(Card.class))
                .hasSize(52);
    }

    @Test
    void 카드를_한_장_뽑을_수_있다() {
        final Card card = cardDeck.draw();

        assertThat(card).isEqualTo(new Card(Suit.DIAMOND, Denomination.KING));
    }

    @Test
    void 기존_카드_덱의_카드를_모두_사용하였을_경우_새로운_카드_덱에서_카드를_뽑는다() {
        for (int i = 0; i < 52; i++) {
            cardDeck.draw();
        }

        final Card card = cardDeck.draw();

        assertThat(card).isEqualTo(new Card(Suit.DIAMOND, Denomination.KING));
    }
}
