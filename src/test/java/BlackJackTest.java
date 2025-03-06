import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    void hit() {
        //given
        Cards totalCards = new Cards();
        totalCards.add(new Card(Symbol.HEART, Number.FIVE));

        Player player = new Player("ad");

        //when
        player.hit(totalCards);
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

        player.hit(totalCards);
        player.hit(totalCards);
        player.hit(totalCards);

        //when
        int score = player.getScore();
        //then
        assertThat(score).isEqualTo(19);
    }

    @DisplayName("플레이어는 버스트되면 카드를 더 뽑을 수 없다.")
    @Test
    void burstIsNotHit() {
        //given
        Player player = new Player("ad");
        Cards totalCards = new Cards();
        totalCards.add(new Card(Symbol.HEART, Number.KING));
        totalCards.add(new Card(Symbol.HEART, Number.JACK));
        totalCards.add(new Card(Symbol.HEART, Number.TWO));

        player.hit(totalCards);
        player.hit(totalCards);
        player.hit(totalCards);

        //when //then
        assertThatThrownBy(() -> player.hit(totalCards))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @DisplayName("플레이어는 버스트 되지 않으면 카드를 더 뽑을 수 있다.")
    @Test
    void notBurstHit() {
        //given
        Player player = new Player("ad");
        Cards totalCards = new Cards();

        totalCards.add(new Card(Symbol.HEART, Number.FIVE));
        totalCards.add(new Card(Symbol.HEART, Number.FIVE));
        totalCards.add(new Card(Symbol.HEART, Number.FIVE));
        totalCards.add(new Card(Symbol.HEART, Number.FIVE));
        totalCards.add(new Card(Symbol.HEART, Number.FIVE));

        player.hit(totalCards);
        player.hit(totalCards);
        player.hit(totalCards);
        player.hit(totalCards);

        //when //then
        assertThatCode(() -> player.hit(totalCards))
                .doesNotThrowAnyException();
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

    @DisplayName("카드의 숫자는 1부터 k까지 14개다.")
    @Test
    void cardNumber() {
        //given

        //when
        Number[] values = Number.values();

        //then
        assertThat(values).hasSize(14);
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

    @DisplayName("Ace가 있을 때, 11로 간주해도 21을 초과하지않을 경우 11로 간주한다.")
    @Test
    void considerAceHas11() {
        //given
        Cards cards = new Cards();
        Card card1 = new Card(Symbol.COLVER, Number.ACE);
        Card card2 = new Card(Symbol.COLVER, Number.KING);

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
        Card card1 = new Card(Symbol.COLVER, Number.ACE);
        Card card2 = new Card(Symbol.HEART, Number.ACE);

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
        Card card1 = new Card(Symbol.COLVER, Number.ACE);
        Card card2 = new Card(Symbol.COLVER, Number.ACE);
        Card card3 = new Card(Symbol.COLVER, Number.ACE);
        Card card4 = new Card(Symbol.COLVER, Number.ACE);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);

        //when
        int actual = cards.calculateTotalPoint();

        //then
        assertThat(actual).isEqualTo(14);
    }

    @DisplayName("게임이 시작되면 플레이어는 2장의 카드를 받는다.")
    @Test
    void startGameGiveDefaultCards() {
        //given
        Player player = new Player("ad");

        Cards cards = new Cards();
        Card card1 = new Card(Symbol.COLVER, Number.ACE);
        Card card2 = new Card(Symbol.COLVER, Number.ACE);
        Card card3 = new Card(Symbol.COLVER, Number.ACE);
        Card card4 = new Card(Symbol.COLVER, Number.ACE);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);

        //when //then
        assertThatCode(() -> player.prepareGame(cards))
                .doesNotThrowAnyException();
    }

    @DisplayName("딜러는 처음 받은 2장의 카드의 합이 16 이하라면 카드를 뽑는다")
    @Test
    void canHit() {
        //given
        Cards cards = new Cards();
        Card card1 = new Card(Symbol.COLVER, Number.ACE);
        Card card2 = new Card(Symbol.HEART, Number.TWO);
        Card card3 = new Card(Symbol.SPADE, Number.KING);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        Dealer dealer = new Dealer();
        dealer.prepareGame(cards);

        int initialPoint = dealer.getCards().calculateTotalPoint();

        //when
        dealer.hit(cards);
        int actual = dealer.getCards().calculateTotalPoint();

        //then
        assertThat(actual).isNotEqualTo(initialPoint);
    }

    @DisplayName("딜러는 처음 받은 2장의 카드의 합이 17 이상이라면 카드를 뽑지 않는다")
    @Test
    void cannotHit() {
        //given
        Cards cards = new Cards();
        Card card1 = new Card(Symbol.COLVER, Number.JACK);
        Card card2 = new Card(Symbol.HEART, Number.NINE);
        Card card3 = new Card(Symbol.SPADE, Number.KING);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        Dealer dealer = new Dealer();
        dealer.prepareGame(cards);

        int initialPoint = dealer.getCards().calculateTotalPoint();

        //when
        dealer.hit(cards);
        int actual = dealer.getCards().calculateTotalPoint();

        //then
        assertThat(actual).isEqualTo(initialPoint);
    }

}
