package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HandTest {

    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand();
    }

    @Test
    @DisplayName("hand는 Card를 인자로 받아 add 메서드 수행")
    void add() {
        hand.add(new Card(CardSymbol.ACE, CardType.DIAMOND));
        hand.add(new Card(CardSymbol.FIVE, CardType.HEART));

        assertThat(hand.getHand()).size().isEqualTo(2);
    }

    @Test
    @DisplayName("hand의 add 인자로 null이 들어올 경우 예외 발생")
    void addException() {
        assertThatThrownBy(() -> hand.add(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("hand 중 첫번째 장을 반환하는 메서드")
    void getFirstHand() {
        Card aceCard = new Card(CardSymbol.ACE, CardType.DIAMOND);
        hand.add(aceCard);
        hand.add(new Card(CardSymbol.FIVE, CardType.HEART));

        assertThat(hand.getFirstHand()).contains(aceCard);
    }

    @ParameterizedTest
    @MethodSource("cardsSum")
    @DisplayName("카드의 합")
    void getCardStatus(List<Card> cards, int result) {
        for (Card card : cards) {
            hand.add(card);
        }
        assertThat(hand.calculate()).isEqualTo(result);
    }

    @ParameterizedTest
    @MethodSource("cardsAndBusted")
    void bustTest(List<Card> cards, boolean bustedResult) {
        for (Card card : cards) {
            hand.add(card);
        }

        assertThat(hand.isBusted()).isEqualTo(bustedResult);
    }

    @ParameterizedTest
    @MethodSource("cardsAndisBlackjack")
    void blackjack(List<Card> cards, boolean blackjackResult) {
        for (Card card : cards) {
            hand.add(card);
        }

        assertThat(hand.isBlackJack()).isEqualTo(blackjackResult);
    }

    private static Stream<Arguments> cardsSum() {
        Card aceSpade = new Card(CardSymbol.ACE, CardType.SPADE);
        Card twoDiamond = new Card(CardSymbol.TWO, CardType.DIAMOND);
        Card jackHeart = new Card(CardSymbol.JACK, CardType.HEART);
        Card kingClover = new Card(CardSymbol.KING, CardType.CLOVER);
        List<Card> twentyTwo = Arrays.asList(kingClover, jackHeart, twoDiamond);
        List<Card> blackjack = Arrays.asList(aceSpade, kingClover);
        List<Card> twenty = Arrays.asList(jackHeart, kingClover);
        List<Card> twelve = Arrays.asList(twoDiamond, jackHeart);
        return Stream.of(
                Arguments.of(twentyTwo, 0),
                Arguments.of(blackjack, 21),
                Arguments.of(twenty, 20),
                Arguments.of(twelve, 12)
        );
    }

    private static Stream<Arguments> cardsAndBusted() {
        Card aceSpade = new Card(CardSymbol.ACE, CardType.SPADE);
        Card twoDiamond = new Card(CardSymbol.TWO, CardType.DIAMOND);
        Card jackHeart = new Card(CardSymbol.JACK, CardType.HEART);
        Card kingClover = new Card(CardSymbol.KING, CardType.CLOVER);
        List<Card> bustedCards = Arrays.asList(kingClover, jackHeart, twoDiamond);
        List<Card> notBustedCards = Arrays.asList(aceSpade, kingClover);
        return Stream.of(
                Arguments.of(bustedCards, true),
                Arguments.of(notBustedCards, false)
        );
    }

    private static Stream<Arguments> cardsAndisBlackjack() {
        Card aceSpade = new Card(CardSymbol.ACE, CardType.SPADE);
        Card jackHeart = new Card(CardSymbol.JACK, CardType.HEART);
        Card kingClover = new Card(CardSymbol.KING, CardType.CLOVER);
        List<Card> blackjackCards = Arrays.asList(aceSpade, jackHeart);
        List<Card> notBlackjackCards = Arrays.asList(jackHeart, kingClover, aceSpade);
        return Stream.of(
                Arguments.of(blackjackCards, true),
                Arguments.of(notBlackjackCards, false)
        );
    }
}