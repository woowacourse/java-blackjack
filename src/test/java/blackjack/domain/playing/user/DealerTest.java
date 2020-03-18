package blackjack.domain.playing.user;

import blackjack.domain.playing.card.Card;
import blackjack.domain.playing.card.Symbol;
import blackjack.domain.playing.card.Type;
import blackjack.domain.playing.deck.Deck;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DealerTest {
    @Test
    void create() {
        assertThat(Dealer.create()).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("createCardAndResult")
    void shouldDrawCard(Card card, boolean result) {
        Dealer dealer = Dealer.create();

        Deck deck = mock(Deck.class);
        when(deck.draw()).thenReturn(new Card(Symbol.KING, Type.SPADE));
        dealer.drawCardsInTurn(deck);

        when(deck.draw()).thenReturn(card);
        dealer.drawCardsInTurn(deck);

        assertThat(dealer.shouldDrawCard()).isEqualTo(result);
    }

    private static Stream<Arguments> createCardAndResult() {
        return Stream.of(
                Arguments.of(new Card(Symbol.SIX, Type.SPADE), true),
                Arguments.of(new Card(Symbol.SEVEN, Type.SPADE), false)
        );
    }
}