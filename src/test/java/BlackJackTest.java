import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BlackJackTest {

    public static Stream<Arguments> provideJQK() {
        return Stream.of(
                Arguments.of(Number.JACK, Number.QUEEN, Number.KING)
        );
    }

    public static Stream<Arguments> provideEachCardsAndExpected() {
        return Stream.of(
                Arguments.of(List.of(
                                new Card(Symbol.COLVER, Number.JACK),
                                new Card(Symbol.HEART, Number.FIVE)),
                        15),
                Arguments.of(List.of(
                                new Card(Symbol.COLVER, Number.SEVEN),
                                new Card(Symbol.HEART, Number.TEN)),
                        17)
        );
    }

    @DisplayName("플레이어는 이름으로 구별된다")
    @Test
    void playerName() {
        //given
        String name1 = "ad";
        String name2 = "dogy";

        //when
        Player player1 = new Player(name1);
        Player player2 = new Player(name2);
        Player player3 = new Player(name1);

        //then
        assertThat(player1).isNotEqualTo(player2).isEqualTo(player3);
    }

    @DisplayName("플레이어는 카드를 뽑을 수 있다.")
    @Test
    void draw() {
        //given
        Cards totalCards = new Cards();
        totalCards.add(new Card(Symbol.HEART, Number.FIVE));

        Player player = new Player("ad");

        //when
        player.draw(totalCards);
        Cards expected = new Cards();
        expected.add(new Card(Symbol.HEART, Number.FIVE));

        //then
        assertThat(player.getCards()).isEqualTo(expected);
    }

    @DisplayName("플레이어의 총 점수를 구할 수 있다")
    @Test
    void calculatePlayerScore() {
        //given
        Player player = new Player("ad");
        Cards totalCards = new Cards();
        totalCards.add(new Card(Symbol.HEART, Number.FIVE));
        totalCards.add(new Card(Symbol.HEART, Number.FOUR));
        totalCards.add(new Card(Symbol.HEART, Number.JACK));

        player.draw(totalCards);
        player.draw(totalCards);
        player.draw(totalCards);

        //when
        int score = player.getScore();
        //then
        assertThat(score).isEqualTo(19);
    }

    @DisplayName("점수가 21점을 초과하면 버스트된다.")
    @Test
    void burst2() {
        //given
        Cards totalCards = new Cards();
        totalCards.add(new Card(Symbol.HEART, Number.KING));
        totalCards.add(new Card(Symbol.HEART, Number.JACK));
        totalCards.add(new Card(Symbol.HEART, Number.TWO));

        //when
        boolean actual = totalCards.isBurst();

        //then
        assertThat(actual).isTrue();
    }

    @DisplayName("점수가 21점을 초과하지않으면 버스트되지 않는다.")
    @Test
    void notBurst() {
        //given
        Cards totalCards = new Cards();
        totalCards.add(new Card(Symbol.HEART, Number.KING));
        totalCards.add(new Card(Symbol.HEART, Number.JACK));

        //when
        boolean actual = totalCards.isBurst();

        //then
        assertThat(actual).isFalse();
    }

    @DisplayName("카드는 문양과 숫자를 가진다.")
    @Test
    void card() {
        //given
        final var symbol = Symbol.SPADE;
        final var number = Number.ACE;

        //when //then
        assertThatCode(() -> new Card(symbol, number))
                .doesNotThrowAnyException();
    }

    @DisplayName("카드의 문양은 4가지다.")
    @Test
    void cardSymbol() {
        //given

        //when
        Symbol[] values = Symbol.values();

        //then
        assertThat(values).hasSize(4);
    }

    @DisplayName("카드의 숫자는 1부터 k까지 13개다.")
    @Test
    void cardNumber() {
        //given

        //when
        Number[] values = Number.values();

        //then
        assertThat(values).hasSize(13);
    }

    @DisplayName("카드의 숫자는 1부터 10까지의 점수를 가진다.")
    @Test
    void cardNumberPoint() {
        //given
        Number five = Number.FIVE;

        //when

        //then
        assertThat(five.getPoint()).isEqualTo(5);
    }

    @DisplayName("J, Q, K 는 각각 10으로 계산한다.")
    @ParameterizedTest
    @MethodSource("provideJQK")
    void cardNumberPointForJQK(Number number) {
        //given

        //when

        //then
        assertThat(number.getPoint()).isEqualTo(10);
    }


    @DisplayName("카드 뭉치에 카드를 추가할 수 있다")
    @Test
    void addCard() {
        //given
        Cards cards = new Cards();
        Card card = new Card(Symbol.COLVER, Number.FIVE);
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
        Card card = new Card(Symbol.COLVER, Number.FIVE);

        cards.add(card);

        //when
        Card actual = cards.extractCard();

        //then
        assertThat(actual).isEqualTo(card);
    }
}
