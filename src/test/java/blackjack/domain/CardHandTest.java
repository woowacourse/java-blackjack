package blackjack.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static blackjack.domain.CardShape.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.*;
import static org.junit.jupiter.api.Assertions.*;

public class CardHandTest {
    
    @Test
    void 카드_손패에_카드를_추가할_수_있다() {
        // given
        final CardHand cardHand = new CardHand();
        final Card newCard = new Card(1, 다이아몬드);
        
        // expected
        assertDoesNotThrow(() -> cardHand.addCard(newCard));
    }
    
    @Test
    void 카드_손패에_있는_카드들을_확인할_수_있다() {
        // given
        final CardHand cardHand = new CardHand();
        cardHand.addCard(new Card(1, 다이아몬드));
        cardHand.addCard(new Card(2, 하트));
        
        // when
        final List<Card> result = cardHand.getCards();
        
        // then
        assertThat(result).extracting(
                "number", "shape"
        ).containsExactlyInAnyOrder(
                tuple(1, 다이아몬드),
                tuple(2, 하트)
        );
    }
    
    @Test
    void 카드_손패에_있는_카드들의_숫자의_합을_계산할_수_있다() {
        // given
        final CardHand cardHand = new CardHand();
        cardHand.addCard(new Card(1, 다이아몬드));
        cardHand.addCard(new Card(2, 하트));
        
        // when
        final int sum = cardHand.getSum();
        
        // then
        assertThat(sum).isEqualTo(13);
    }
    
    @Test
    void JQK를_10으로_계산하여_숫자의_합을_반환한다() {
        // given
        final CardHand cardHand = new CardHand();
        cardHand.addCard(new Card(11, 다이아몬드));
        cardHand.addCard(new Card(12, 하트));
        cardHand.addCard(new Card(13, 하트));
        
        // when
        final int sum = cardHand.getSum();
        
        // then
        assertThat(sum).isEqualTo(30);
    }
    
    @Test
    void 손패에_존재하는_카드의_숫자의_합을_계산할_수_있다() {
        // given
        final CardHand cardHand = new CardHand();
        cardHand.addCard(new Card(1, 다이아몬드));
        cardHand.addCard(new Card(7, 하트));
        cardHand.addCard(new Card(10, 하트));
        
        // when
        final int sum = cardHand.getSum();
        
        // then
        assertThat(sum).isEqualTo(18);
    }
    
    @ParameterizedTest
    @MethodSource("provideCardsAndSumWithBurst")
    void A를_11로_계산하여_버스트_되는_경우_A는_1로_계산된다(List<Card> cards, int expected) {
        // given
        final CardHand cardHand = new CardHand();
        for (Card card : cards) {
            cardHand.addCard(card);
        }
        cardHand.addCard(new Card(1, 다이아몬드));
        
        // when
        final int sum = cardHand.getSum();
        
        // then
        assertThat(sum).isEqualTo(expected);
    }
    
    private static Stream<Arguments> provideCardsAndSumWithBurst() {
        return Stream.of(
                Arguments.of(List.of(new Card(10, 다이아몬드), new Card(5, 하트)), 16),
                Arguments.of(List.of(new Card(10, 다이아몬드), new Card(4, 하트)), 15),
                Arguments.of(List.of(new Card(10, 다이아몬드), new Card(3, 하트)), 14),
                Arguments.of(List.of(new Card(10, 다이아몬드), new Card(2, 하트)), 13),
                Arguments.of(List.of(new Card(10, 다이아몬드), new Card(1, 하트)), 12)
        );
    }
    
    @ParameterizedTest
    @MethodSource("provideCardsAndSumWithoutBurst")
    void A를_11로_계산하여_버스트_되지_않는_경우_A는_11로_계산된다(List<Card> cards, int expected) {
        // given
        final CardHand cardHand = new CardHand();
        for (Card card : cards) {
            cardHand.addCard(card);
        }
        cardHand.addCard(new Card(1, 다이아몬드));
        
        // when
        final int sum = cardHand.getSum();
        
        // then
        assertThat(sum).isEqualTo(expected);
    }
    
    private static Stream<Arguments> provideCardsAndSumWithoutBurst() {
        return Stream.of(
                Arguments.of(List.of(new Card(10, 다이아몬드)), 21),
                Arguments.of(List.of(new Card(9, 다이아몬드)), 20),
                Arguments.of(List.of(new Card(8, 다이아몬드)), 19),
                Arguments.of(List.of(new Card(7, 다이아몬드)), 18),
                Arguments.of(List.of(new Card(6, 다이아몬드)), 17)
        );
    }
    
    @ParameterizedTest
    @MethodSource("provideCardsWithAceAndSum")
    void A가_여러_개_주어진_경우_버스트가_되지_않는_선에서_최대_합을_구한다(List<Card> cards, int expected) {
        // given
        final CardHand cardHand = new CardHand();
        for (Card card : cards) {
            cardHand.addCard(card);
        }
        cardHand.addCard(new Card(1, 다이아몬드));
        cardHand.addCard(new Card(1, 하트));
        
        // when
        final int sum = cardHand.getSum();
        
        // then
        assertThat(sum).isEqualTo(expected);
    }
    
    private static Stream<Arguments> provideCardsWithAceAndSum() {
        return Stream.of(
                Arguments.of(List.of(new Card(9, 다이아몬드), new Card(10, 다이아몬드)), 21),
                Arguments.of(List.of(new Card(9, 다이아몬드), new Card(10, 다이아몬드)), 21),
                Arguments.of(List.of(new Card(9, 다이아몬드), new Card(10, 다이아몬드)), 21),
                Arguments.of(List.of(), 12)
                );
    }
}
