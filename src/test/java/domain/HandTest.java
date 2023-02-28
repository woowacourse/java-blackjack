package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class HandTest {
    @DisplayName("카드가 패에 처음 추가되면 패의 크기가 1이다.")
    @Test
    void addCardTest() {
        Hand hand = new Hand();
        final Card card = new Card(Suit.DIAMOND, Rank.ACE);

        assertEquals(0, hand.getCards().size());
        hand.addCard(card);
        assertEquals(1, hand.getCards().size());
    }

    @DisplayName("카드 패에 A가 들어오면 항상 마지막 자리에 위치한다.")
    @ParameterizedTest
    @MethodSource("addAceCardTestProvider")
    void addAceCardTest(final Card firstCard, final Card secondCard) {
        Hand hand = new Hand();
        hand.addCard(firstCard);
        hand.addCard(secondCard);

        int lastIndex = hand.getCards().size() - 1;
        Rank lastCardRank = hand.getCards().get(lastIndex).getRank();

        assertEquals(Rank.ACE, lastCardRank);
    }

    private static Stream<Arguments> addAceCardTestProvider() {
        return Stream.of(
                Arguments.of(new Card(Suit.DIAMOND, Rank.ACE), new Card(Suit.DIAMOND, Rank.SIX)),
                Arguments.of(new Card(Suit.CLOVER, Rank.ACE), new Card(Suit.HEART, Rank.SEVEN))
        );
    }

    @DisplayName("21에 가장 가까운 점수를 반환한다.")
    @ParameterizedTest
    @MethodSource("calculateScoreTestProvider")
    void calculateScoreTest(final Card firstCard, final Card secondCard, final Card thirdCard,
                            final int expectedScore) {

        Hand hand = new Hand();
        hand.addCard(firstCard);
        hand.addCard(secondCard);
        hand.addCard(thirdCard);

        assertEquals(expectedScore, hand.score());
    }

    private static Stream<Arguments> calculateScoreTestProvider() {
        return Stream.of(
                Arguments.of(new Card(Suit.DIAMOND, Rank.SEVEN),
                        new Card(Suit.DIAMOND, Rank.KING),
                        new Card(Suit.DIAMOND, Rank.TWO),
                        19),
                Arguments.of(new Card(Suit.CLOVER, Rank.ACE),
                        new Card(Suit.HEART, Rank.JACK),
                        new Card(Suit.DIAMOND, Rank.SEVEN),
                        18),
                Arguments.of(new Card(Suit.CLOVER, Rank.ACE),
                        new Card(Suit.HEART, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.SEVEN),
                        19),
                Arguments.of(new Card(Suit.CLOVER, Rank.KING),
                        new Card(Suit.HEART, Rank.QUEEN),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        30)
        );
    }
}
