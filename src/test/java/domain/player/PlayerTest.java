package domain.player;

import domain.deck.Card;
import domain.deck.Rank;
import domain.deck.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlayerTest {

    @DisplayName("처음 상태에서 카드를 한 장 뽑으면 카드의 개수는 1이다.")
    @Test
    void hitTest() {
        int expectedCardSize = 1;

        Player player = new Player("hardy");
        player.hit(Card.of(Suit.DIAMOND, Rank.QUEEN));

        assertEquals(expectedCardSize, player.getCards().size());
    }

    @DisplayName("가진 카드에 대한 점수 테스트")
    @ParameterizedTest
    @MethodSource("getScoreProvider")
    void getScoreTest(final int expectedScore, final Card firstCard, final Card secondCard) {
        Player player = new Player("hardy");
        player.hit(firstCard);
        player.hit(secondCard);

        assertEquals(expectedScore, player.getScore());
    }

    Stream<Arguments> getScoreProvider() {
        return Stream.of(
                Arguments.of(20, Card.of(Suit.DIAMOND, Rank.QUEEN), Card.of(Suit.HEART, Rank.KING)),
                Arguments.of(21, Card.of(Suit.DIAMOND, Rank.QUEEN), Card.of(Suit.HEART, Rank.ACE)),
                Arguments.of(12, Card.of(Suit.DIAMOND, Rank.ACE), Card.of(Suit.HEART, Rank.ACE)),
                Arguments.of(13, Card.of(Suit.DIAMOND, Rank.TWO), Card.of(Suit.HEART, Rank.ACE))
        );
    }

    @DisplayName("점수가 블랙잭넘버인 21보다 크거나 같으면 true를 반환한다.")
    @ParameterizedTest
    @MethodSource("isEqualOrLargerThanBlackJackNumber")
    void isEqualOrLargerThanBlackJackNumberTest(final boolean expected, final Card firstCard, final Card secondCard, final Card thirdCard) {
        Player player = new Player("hardy");
        player.hit(firstCard);
        player.hit(secondCard);
        player.hit(thirdCard);

        assertEquals(expected, player.isEqualOrLargerThanBlackJackNumber());
    }

    Stream<Arguments> isEqualOrLargerThanBlackJackNumber() {
        return Stream.of(
                Arguments.of(true, Card.of(Suit.DIAMOND, Rank.QUEEN), Card.of(Suit.HEART, Rank.KING), Card.of(Suit.SPADE, Rank.KING)),
                Arguments.of(true, Card.of(Suit.DIAMOND, Rank.QUEEN), Card.of(Suit.HEART, Rank.ACE), Card.of(Suit.SPADE, Rank.KING)),
                Arguments.of(false, Card.of(Suit.DIAMOND, Rank.ACE), Card.of(Suit.HEART, Rank.ACE), Card.of(Suit.SPADE, Rank.ACE)),
                Arguments.of(false, Card.of(Suit.DIAMOND, Rank.NINE), Card.of(Suit.HEART, Rank.ACE), Card.of(Suit.SPADE, Rank.JACK))
        );
    }
}
