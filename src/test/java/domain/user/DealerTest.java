package domain.user;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.OutputView;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class DealerTest {

    private Dealer dealer;

    @Mock
    private Deck deck;

    @BeforeEach
    void setUp() {
        dealer = Dealer.appoint();
    }

    @Test
    @DisplayName("생성 확인")
    void create() {
        assertThat(Dealer.appoint()).isNotNull();
    }

    @ParameterizedTest
    @DisplayName("딜러 기준 드로우 가능한지 확인")
    @MethodSource("createOption")
    void isAvailableToDraw(Card card, boolean expected) {
        MockitoAnnotations.initMocks(this);
        given(deck.dealOut()).willReturn(new Card(Symbol.CLOVER, Type.TEN));
        dealer.draw(deck);

        given(deck.dealOut()).willReturn(card);
        dealer.cards.add(card);

        assertThat(dealer.isAvailableToDraw()).isEqualTo(expected);
    }

    private static Stream<Arguments> createOption() {
        return Stream.of(
                Arguments.of(new Card(Symbol.DIAMOND, Type.SIX), true),
                Arguments.of(new Card(Symbol.DIAMOND, Type.SEVEN), false),
                Arguments.of(new Card(Symbol.DIAMOND, Type.ACE), false)
        );
    }

    @Test
    @DisplayName("추가 드로우")
    void additionalDealOut() {
        MockitoAnnotations.initMocks(this);
        Queue<Card> cards = new LinkedList<>(Arrays.asList(
                new Card(Symbol.SPADE, Type.SIX),
                new Card(Symbol.SPADE, Type.SEVEN),
                new Card(Symbol.HEART, Type.FIVE))
        );
        List<Card> expected = new ArrayList<>(cards);

        given(deck.dealOut()).will(invocation -> cards.poll());
        dealer.additionalDealOut(deck, OutputView::printDealerDealOut);

        assertThat(dealer.getCards()).containsAll(expected);
    }
}