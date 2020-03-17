package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CardsTest {
    private static List<Card> cardList;

    private static Stream<Arguments> getCardsOneAceExist() {
        List<Card> cardsWhenAceIs11
            = makeCardList(new Card(Symbol.ACE, Type.CLUB), new Card(Symbol.QUEEN, Type.CLUB));
        List<Card> cardsWhenAceIs1 = makeCardList(new Card(Symbol.ACE, Type.CLUB),
            new Card(Symbol.QUEEN, Type.CLUB), new Card(Symbol.QUEEN, Type.HEART));

        return Stream.of(
            Arguments.of(cardsWhenAceIs11),
            Arguments.of(cardsWhenAceIs1)
        );
    }

    private static Stream<Arguments> getCardsTwoAceExist() {
        List<Card> cardsWhenAceIs11
            = makeCardList(new Card(Symbol.ACE, Type.CLUB), new Card(Symbol.ACE, Type.CLUB));
        List<Card> cardsWhenAceIs1 = makeCardList(new Card(Symbol.ACE, Type.CLUB),
            new Card(Symbol.ACE, Type.CLUB), new Card(Symbol.QUEEN, Type.HEART));

        return Stream.of(
            Arguments.of(cardsWhenAceIs11, 12),
            Arguments.of(cardsWhenAceIs1, 12)
        );
    }

    private static List<Card> makeCardList(Card card1, Card card2) {
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        return cards;
    }

    @ParameterizedTest
    @DisplayName("한 장의 에이스가 존재할 때 합 구하기")
    @MethodSource("getCardsOneAceExist")
    void sumScoresWhenOneAceExist(List<Card> cards) {
        Cards userCards = new Cards();
        userCards.put(cards);
        assertThat(userCards.sumScores()).isEqualTo(21);
    }

    @ParameterizedTest
    @DisplayName("두 장의 에이스가 존재할 때 합 구하기")
    @MethodSource("getCardsTwoAceExist")
    void sumScoresWhenAceIsMoreThanTwo(List<Card> cards, int sumResult) {
        Cards userCards = new Cards();
        userCards.put(cards);
        assertThat(userCards.sumScores()).isEqualTo(sumResult);
    }

    private static List<Card> makeCardList(Card card1, Card card2, Card card3) {
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        return cards;
    }

    @Test
    void sumScores() {
        cardList = makeCardList(new Card(Symbol.TWO, Type.CLUB), new Card(Symbol.QUEEN, Type.CLUB));
        Cards cards = new Cards();
        cards.put(cardList);
        assertThat(cards.sumScores()).isEqualTo(12);
    }

    @Test
    @DisplayName("합이 21 보다 작아서 블랙잭이 아닌 경우")
    void isBlackJackWhenFalse() {
        cardList = makeCardList(new Card(Symbol.TWO, Type.CLUB), new Card(Symbol.QUEEN, Type.CLUB));
        Cards cards = new Cards();
        cards.put(cardList);
        assertThat(cards.isBlackJack()).isFalse();
    }

    @Test
    @DisplayName("합이 21 이지만 3장 이상이라서 블랙잭이 아닌 경우")
    void isBlackJackWhenFalseButSumIsBlackJack() {
        cardList = makeCardList(
            new Card(Symbol.ACE, Type.CLUB),
            new Card(Symbol.TWO, Type.CLUB),
            new Card(Symbol.EIGHT, Type.CLUB)
        );
        Cards cards = new Cards();
        cards.put(cardList);
        assertThat(cards.isBlackJack()).isFalse();
    }

    @Test
    void isBlackJackWhenTrue() {
        cardList = makeCardList(new Card(Symbol.ACE, Type.CLUB), new Card(Symbol.QUEEN, Type.CLUB));
        Cards cards = new Cards();
        cards.put(cardList);
        assertThat(cards.isBlackJack()).isTrue();
    }

    @Test
    void isLargerThan() {
        Cards cards = new Cards();
        cardList = makeCardList(new Card(Symbol.ACE, Type.CLUB), new Card(Symbol.QUEEN, Type.CLUB));
        cards.put(cardList);
        assertThat(cards.isLargerThan(Cards.BLACKJACK_SCORE)).isFalse();
    }

    @Test
    void isSmallerThan() {
        Cards cards = new Cards();
        cardList = makeCardList(new Card(Symbol.ACE, Type.CLUB), new Card(Symbol.NINE, Type.CLUB));
        cards.put(cardList);
        assertThat(cards.isSmallerThan(Cards.BLACKJACK_SCORE)).isTrue();
    }
}
