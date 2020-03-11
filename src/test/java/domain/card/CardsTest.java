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
    private static Stream<Arguments> getCardsOneAceExist() {
        List<Card> cardsWhenAceIs11 = new ArrayList<>();
        cardsWhenAceIs11.add(new Card(Symbol.ACE, Type.CLUB));
        cardsWhenAceIs11.add(new Card(Symbol.QUEEN, Type.CLUB));

        List<Card> cardsWhenAceIs1 = new ArrayList<>();
        cardsWhenAceIs1.add(new Card(Symbol.ACE, Type.CLUB));
        cardsWhenAceIs1.add(new Card(Symbol.QUEEN, Type.CLUB));
        cardsWhenAceIs1.add(new Card(Symbol.QUEEN, Type.HEART));

        return Stream.of(
            Arguments.of(cardsWhenAceIs11),
            Arguments.of(cardsWhenAceIs1)
        );
    }

    private static Stream<Arguments> getCardsTwoAceExist() {
        List<Card> cardsWhenAceIs11 = new ArrayList<>();
        cardsWhenAceIs11.add(new Card(Symbol.ACE, Type.CLUB));
        cardsWhenAceIs11.add(new Card(Symbol.ACE, Type.CLUB));

        List<Card> cardsWhenAceIs1 = new ArrayList<>();
        cardsWhenAceIs1.add(new Card(Symbol.ACE, Type.CLUB));
        cardsWhenAceIs1.add(new Card(Symbol.ACE, Type.CLUB));
        cardsWhenAceIs1.add(new Card(Symbol.QUEEN, Type.HEART));

        return Stream.of(
            Arguments.of(cardsWhenAceIs11, 12),
            Arguments.of(cardsWhenAceIs1, 12)
        );
    }

    @Test
    void sumScores() {
        List<Card> card = new ArrayList<>();
        card.add(new Card(Symbol.TWO, Type.CLUB));
        card.add(new Card(Symbol.QUEEN, Type.CLUB));

        Cards cards = new Cards();
        cards.put(card);
        assertThat(cards.sumScores()).isEqualTo(12);
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

    @Test
    void isBlackJackWhenFalse() {
        List<Card> card = new ArrayList<>();
        card.add(new Card(Symbol.TWO, Type.CLUB));
        card.add(new Card(Symbol.QUEEN, Type.CLUB));

        Cards cards = new Cards();
        cards.put(card);
        assertThat(cards.isBlackJack()).isFalse();
    }

    @Test
    void isBlackJackWhenTrue() {
        List<Card> card = new ArrayList<>();
        card.add(new Card(Symbol.ACE, Type.CLUB));
        card.add(new Card(Symbol.QUEEN, Type.CLUB));

        Cards cards = new Cards();
        cards.put(card);
        assertThat(cards.isBlackJack()).isTrue();
    }

    @Test
    void isLargerThan() {
        Cards cards = new Cards();
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Symbol.ACE, Type.CLUB));
        cardList.add(new Card(Symbol.QUEEN, Type.CLUB));

        cards.put(cardList);
        assertThat(cards.isLargerThan(Cards.BLACKJACK_SCORE)).isFalse();
    }

    @Test
    void isSmallerThan() {
        Cards cards = new Cards();
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Symbol.ACE, Type.CLUB));
        cardList.add(new Card(Symbol.NINE, Type.CLUB));

        cards.put(cardList);
        assertThat(cards.isSmallerThan(Cards.BLACKJACK_SCORE)).isTrue();
    }
}
