package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HandTest {

    private static final Card aceCard = new Card(Suit.CLOVER, Denomination.ACE);
    private static final Card tenCard = new Card(Suit.CLOVER, Denomination.KING);
    private static final Card jackCard = new Card(Suit.CLOVER, Denomination.KING);
    private static final Card queenCard = new Card(Suit.CLOVER, Denomination.KING);
    private static final Card kingCard = new Card(Suit.CLOVER, Denomination.KING);
    private static final Card sevenCard = new Card(Suit.CLOVER, Denomination.SEVEN);
    private static final Card twoCard = new Card(Suit.CLOVER, Denomination.TWO);

    private Card card;
    private Hand emptyHand;

    @BeforeEach
    void setUp() {
        emptyHand = new Hand(Collections.emptyList());
        card = new Card(Suit.CLOVER, Denomination.ACE);
    }

    @Test
    @DisplayName("빈 카드패 생성")
    void createEmptyHand() {
        assertThat(Hand.createEmptyHand().getCards()).isEmpty();
    }

    @Test
    @DisplayName("카드패에 카드 한 장 추가")
    void add() {
        emptyHand.add(card);

        assertThat(emptyHand.getCards()).containsExactly(card);
    }

    @ParameterizedTest(name = "카드패 점수계산")
    @MethodSource("getScoreTestcase")
    void getScore(Hand hand, int expectedScore) {
        assertThat(hand.getScore()).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> getScoreTestcase() {
        return Stream.of(
                Arguments.of(createHand(aceCard), 11),
                Arguments.of(createHand(aceCard, kingCard), 21),
                Arguments.of(createHand(aceCard, aceCard, aceCard), 13)
        );
    }

    @ParameterizedTest(name = "버스트인지 검사")
    @MethodSource("isBustTestcase")
    void isBust(Hand hand, boolean expected) {
        assertThat(hand.isBust()).isEqualTo(expected);
    }

    private static Stream<Arguments> isBustTestcase() {
        return Stream.of(
                Arguments.of(createHand(aceCard), false),
                Arguments.of(createHand(aceCard, kingCard), false),
                Arguments.of(createHand(aceCard, aceCard, aceCard), false),
                Arguments.of(createHand(sevenCard, sevenCard, sevenCard), false),
                Arguments.of(createHand(kingCard, kingCard, twoCard), true)
        );
    }

    @ParameterizedTest(name = "블랙잭인지 검사")
    @MethodSource("isBlackjackTestcase")
    void isBlackjack(Hand hand, boolean expected) {
        assertThat(hand.isBlackjack()).isEqualTo(expected);
    }

    private static Stream<Arguments> isBlackjackTestcase() {
        return Stream.of(
                Arguments.of(createHand(aceCard, tenCard), true),
                Arguments.of(createHand(aceCard, jackCard), true),
                Arguments.of(createHand(aceCard, queenCard), true),
                Arguments.of(createHand(aceCard, kingCard), true),
                Arguments.of(createHand(aceCard, aceCard), false),
                Arguments.of(createHand(sevenCard, sevenCard, sevenCard), false)
        );
    }

    private static Hand createHand(Card... cards) {
        return new Hand(Arrays.asList(cards));
    }
}