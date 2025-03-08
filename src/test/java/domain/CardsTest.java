package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {

    @DisplayName("점수가 21점을 초과하면 버스트된다.")
    @Test
    void bust() {
        //given
        Cards totalCards = new Cards();
        totalCards.add(new Card(Symbol.HEART, Rank.KING));
        totalCards.add(new Card(Symbol.HEART, Rank.JACK));
        totalCards.add(new Card(Symbol.HEART, Rank.TWO));

        //when
        boolean actual = totalCards.isBust();

        //then
        assertThat(actual).isTrue();
    }

    @DisplayName("점수가 21점을 초과하지않으면 버스트되지 않는다.")
    @Test
    void notBust() {
        //given
        Cards totalCards = new Cards();
        totalCards.add(new Card(Symbol.HEART, Rank.KING));
        totalCards.add(new Card(Symbol.HEART, Rank.JACK));

        //when
        boolean actual = totalCards.isBust();

        //then
        assertThat(actual).isFalse();
    }

    @DisplayName("카드 뭉치에 카드를 추가할 수 있다")
    @Test
    void addCard() {
        //given
        Cards cards = new Cards();
        Card card = new Card(Symbol.COLVER, Rank.FIVE);
        //when

        //then
        assertThatCode(() -> cards.add(card))
                .doesNotThrowAnyException();
    }

    @DisplayName("카드뭉치의 카드들의 점수의 합을 계산할 수 있다")
    @ParameterizedTest
    @MethodSource("provideEachCardsAndExpected")
    void calculateTotalPoint(List<Card> cardList, int expected) {
        //given
        Cards cards = new Cards();
        for (Card card : cardList) {
            cards.add(card);
        }

        //when
        int actual = cards.calculateTotalPoint();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("카드뭉치의 카드를 드로우 할 수 있다.")
    @Test
    void extractCard() {
        //given
        Cards cards = new Cards();
        Card card = new Card(Symbol.COLVER, Rank.FIVE);

        cards.add(card);

        //when
        Card actual = cards.extractCard();

        //then
        assertThat(actual).isEqualTo(card);
    }

    @DisplayName("Ace가 있을 때, 11로 간주해도 21을 초과하지않을 경우 11로 간주한다.")
    @Test
    void considerAceHas11() {
        //given
        Cards cards = new Cards();
        Card card1 = new Card(Symbol.COLVER, Rank.ACE);
        Card card2 = new Card(Symbol.COLVER, Rank.KING);

        cards.add(card1);
        cards.add(card2);

        //when
        int actual = cards.calculateTotalPoint();

        //then
        assertThat(actual).isEqualTo(21);
    }

    @DisplayName("Ace가 있을 때, 11로 간주해도 21을 초과하지않을 경우 11로 간주한다.")
    @Test
    void considerAceHas112() {
        //given
        Cards cards = new Cards();
        Card card1 = new Card(Symbol.COLVER, Rank.ACE);
        Card card2 = new Card(Symbol.HEART, Rank.ACE);

        cards.add(card1);
        cards.add(card2);

        //when
        int actual = cards.calculateTotalPoint();

        //then
        assertThat(actual).isEqualTo(12);
    }

    @DisplayName("Ace가 있을 때, 11로 간주해도 21을 초과하지않을 경우 11로 간주한다.")
    @Test
    void considerAceHas113() {
        //given
        Cards cards = new Cards();
        Card card1 = new Card(Symbol.COLVER, Rank.ACE);
        Card card2 = new Card(Symbol.COLVER, Rank.ACE);
        Card card3 = new Card(Symbol.COLVER, Rank.ACE);
        Card card4 = new Card(Symbol.COLVER, Rank.ACE);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);

        //when
        int actual = cards.calculateTotalPoint();

        //then
        assertThat(actual).isEqualTo(14);
    }

    public static Stream<Arguments> provideEachCardsAndExpected() {
        return Stream.of(
                Arguments.of(List.of(
                                new Card(Symbol.COLVER, Rank.JACK),
                                new Card(Symbol.HEART, Rank.FIVE)),
                        15),
                Arguments.of(List.of(
                                new Card(Symbol.COLVER, Rank.SEVEN),
                                new Card(Symbol.HEART, Rank.TEN)),
                        17)
        );
    }
}
