package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.deck.Card;
import domain.deck.Rank;
import domain.deck.Suit;
import domain.player.Hand;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class HandTest {
    private static Stream<Arguments> calculateScoreTestProvider() {
        return Stream.of(
                Arguments.of(
                        new Card(Suit.DIAMOND, Rank.SEVEN),
                        new Card(Suit.DIAMOND, Rank.KING),
                        new Card(Suit.DIAMOND, Rank.TWO),
                        19
                ),
                Arguments.of(
                        new Card(Suit.CLOVER, Rank.ACE),
                        new Card(Suit.HEART, Rank.JACK),
                        new Card(Suit.DIAMOND, Rank.SEVEN),
                        18
                ),
                Arguments.of(
                        new Card(Suit.CLOVER, Rank.ACE),
                        new Card(Suit.HEART, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.SEVEN),
                        19
                ),
                Arguments.of(
                        new Card(Suit.CLOVER, Rank.KING),
                        new Card(Suit.HEART, Rank.QUEEN),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        30
                )
        );
    }

    @DisplayName("카드가 패에 처음 추가되면 패의 크기가 1이다.")
    @Test
    void addCardTest() {
        // given
        final Hand hand = new Hand();
        final Card card = new Card(Suit.DIAMOND, Rank.ACE);

        //when
        hand.addCard(card);

        // then
        assertEquals(1, hand.cards().size());
    }

    @DisplayName("21에 가장 가까운 점수를 반환한다.")
    @ParameterizedTest
    @MethodSource("calculateScoreTestProvider")
    void calculateScoreTest(final Card firstCard, final Card secondCard, final Card thirdCard,
                            final int expectedScore) {
        // given
        final Hand hand = new Hand();

        // when
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);

        // then
        assertEquals(expectedScore, hand.score());
    }
}
