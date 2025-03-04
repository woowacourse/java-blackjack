package blackjack.domain;

import blackjack.domain.card_hand.DealerCardHand;
import blackjack.testutil.CardHandInitializerDummy;
import blackjack.testutil.CardHandInitializerStub;
import blackjack.testutil.DeckStub;
import blackjack.testutil.TestConstants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static blackjack.testutil.TestConstants.*;

public class DealerCardHandTest {
    
    @Test
    void 딜러_손패_숫자의_합이_16_이하인_경우_카드를_뽑는다() {
        //given
        DealerCardHand dealerCardHand = new DealerCardHand(new CardHandInitializerDummy());
        
        //when
        dealerCardHand.startAdding(new Deck());
        
        //then
        Assertions.assertThat(dealerCardHand.getCards().size()).isGreaterThanOrEqualTo(1);
    }
    
    @ParameterizedTest
    @MethodSource("provideInitCardsAndDeckCardsAndExpectedSum")
    void 딜러_손패_숫자의_합이_15_이고_뽑을_수_있는_숫자가_1_2_3인_경우_18까지_뽑는다(
            List<Card> initCards,
            List<Card> deckCards,
            int expectedSum
    ) {
        //given
        DealerCardHand dealerCardHand = new DealerCardHand(new CardHandInitializerStub(initCards));
        
        //when
        final DeckStub deck = new DeckStub(deckCards);
        dealerCardHand.startAdding(deck);
        
        //then
        Assertions.assertThat(dealerCardHand.getSum()).isEqualTo(expectedSum);
    }
    
    private static Stream<Arguments> provideInitCardsAndDeckCardsAndExpectedSum() {
        return Stream.of(
                Arguments.of(
                        List.of(HEART_10, HEART_5),
                        List.of(HEART_1, HEART_2, HEART_3),
                        18
                ),
                Arguments.of(
                        List.of(HEART_10, HEART_3),
                        List.of(HEART_1, HEART_2, HEART_3),
                        19
                ),
                Arguments.of(
                        List.of(HEART_10, HEART_5, HEART_2),
                        List.of(HEART_1, HEART_2, HEART_3),
                        17
                )
        );
    }
}
