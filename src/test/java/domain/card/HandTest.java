package domain.card;

import static domain.card.Number.ACE;
import static domain.card.Number.FIVE;
import static domain.card.Number.JACK;
import static domain.card.Number.QUEEN;
import static domain.card.Number.TWO;
import static domain.card.Shape.CLOVER;
import static domain.card.Shape.DIAMOND;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class HandTest {
    @Test
    @DisplayName("카드 추가 테스트")
    void addCardTest() {
        // given
        Hand hand = new Hand();
        Card card = new Card(DIAMOND, TWO);

        // when
        hand.addCard(card);

        // then
        assertThat(hand.getCards().size()).isEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("카드 합계 테스트")
    @MethodSource("provideCardDeckForSumWithAce")
    void sumWithAceTest(List<Card> cards, int expectedSum) {
        // given
        Hand hand = new Hand();
        cards.forEach(hand::addCard);

        // when-then
        assertThat(hand.sumWithAce()).isEqualTo(expectedSum);
    }

    private static Stream<Arguments> provideCardDeckForSumWithAce() {
        return Stream.of(
                Arguments.of(new ArrayList<>(List.of(new Card(DIAMOND, ACE))), 11),
                Arguments.of(new ArrayList<>(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE))), 12),
                Arguments.of(
                        new ArrayList<>(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE), new Card(HEART, ACE))),
                        13),
                Arguments.of(new ArrayList<>(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE), new Card(HEART, ACE),
                        new Card(CLOVER, ACE))), 14),
                Arguments.of(new ArrayList<>(List.of(new Card(SPADE, QUEEN), new Card(DIAMOND, ACE))), 21),
                Arguments.of(
                        new ArrayList<>(List.of(new Card(SPADE, QUEEN), new Card(DIAMOND, ACE), new Card(HEART, ACE))),
                        12),
                Arguments.of(new ArrayList<>(
                        List.of(new Card(SPADE, QUEEN), new Card(DIAMOND, QUEEN), new Card(HEART, ACE))), 21),
                Arguments.of(
                        new ArrayList<>(List.of(new Card(SPADE, QUEEN), new Card(DIAMOND, FIVE), new Card(DIAMOND, ACE),
                                new Card(SPADE, ACE), new Card(HEART, ACE), new Card(CLOVER, ACE))), 19),
                Arguments.of(new ArrayList<>(List.of(new Card(SPADE, JACK), new Card(DIAMOND, JACK))), 20)
        );
    }

    @Test
    @DisplayName("히든 카드를 제외한 카드 반환 테스트")
    void firstOpenCardsTest() {
        // given
        Hand hand = new Hand();
        hand.addCard(new Card(DIAMOND, TWO));
        hand.addCard(new Card(SPADE, ACE));

        // when
        Hand handExceptHidden = hand.getFirstCard();

        // then
        assertSoftly(softly -> {
            softly.assertThat(handExceptHidden.getCards().size()).isEqualTo(1);
            softly.assertThat(handExceptHidden.getCards().getFirst().getShape()).isEqualTo(DIAMOND);
            softly.assertThat(handExceptHidden.getCards().getFirst().getNumber()).isEqualTo(TWO);
        });
    }

}
