package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Guests;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ResultTest {

    @ParameterizedTest(name = "[{index}] 딜러 : {0}, {1}, 참가자 : {2}, {3} -> {4}")
    @MethodSource("generateDecideWhenDealerIsAliveArguments")
    @DisplayName("딜러의 점수가 21점 이하인 경우, 참가자의 승패를 테스트한다.")
    void decideWhenDealerIsAlive(Card card1, Card card2, Card card3, Card card4, Result expected) {
        Dealer dealer = new Dealer();
        dealer.takeCard(card1);
        dealer.takeCard(card2);

        Guests guests = new Guests(List.of("엘리", "배카라"));
        for (Guest guest : guests) {
            guest.takeCard(card3);
            guest.takeCard(card4);
        }

        for (Guest guest : guests) {
            assertThat(Result.of(dealer, guest)).isEqualTo(expected);
        }
    }

    static Stream<Arguments> generateDecideWhenDealerIsAliveArguments() {
        return Stream.of(
                Arguments.of(
                        new Card(Denomination.NINE, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.HEART),
                        new Card(Denomination.TEN, Suit.SPADE), new Card(Denomination.ACE, Suit.HEART),
                        Result.BLACKJACK
                ),
                Arguments.of(
                        new Card(Denomination.NINE, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.HEART),
                        new Card(Denomination.ACE, Suit.SPADE), new Card(Denomination.SIX, Suit.HEART),
                        Result.DRAW
                ),
                Arguments.of(
                        new Card(Denomination.NINE, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.HEART),
                        new Card(Denomination.ACE, Suit.SPADE), new Card(Denomination.TWO, Suit.HEART),
                        Result.LOSE
                ),
                Arguments.of(
                        new Card(Denomination.NINE, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.HEART),
                        new Card(Denomination.TEN, Suit.SPADE), new Card(Denomination.NINE, Suit.HEART),
                        Result.WIN
                ),
                Arguments.of(
                        new Card(Denomination.NINE, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.HEART),
                        new Card(Denomination.THREE, Suit.SPADE), new Card(Denomination.EIGHT, Suit.HEART),
                        Result.LOSE
                ),
                Arguments.of(
                        new Card(Denomination.NINE, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.HEART),
                        new Card(Denomination.TEN, Suit.SPADE), new Card(Denomination.SEVEN, Suit.HEART),
                        Result.DRAW
                ),
                Arguments.of(
                        new Card(Denomination.ACE, Suit.CLOVER), new Card(Denomination.TEN, Suit.HEART),
                        new Card(Denomination.TEN, Suit.SPADE), new Card(Denomination.NINE, Suit.HEART),
                        Result.LOSE
                ),
                Arguments.of(
                        new Card(Denomination.ACE, Suit.CLOVER), new Card(Denomination.TEN, Suit.HEART),
                        new Card(Denomination.THREE, Suit.SPADE), new Card(Denomination.EIGHT, Suit.HEART),
                        Result.LOSE
                ),
                Arguments.of(
                        new Card(Denomination.ACE, Suit.CLOVER), new Card(Denomination.TEN, Suit.HEART),
                        new Card(Denomination.TEN, Suit.SPADE), new Card(Denomination.ACE, Suit.HEART),
                        Result.LOSE
                )
        );
    }

    @ParameterizedTest(name = "[{index}] 딜러 : {0}, {1}, 참가자 : {2}, {3}, {4} -> {5}")
    @MethodSource("generateDecideWhenDealerIsAliveAndGuestBustArguments")
    @DisplayName("딜러의 점수가 21점 이하이고 참가자가 버스트가 있는 경우, 참가자의 승패를 테스트한다.")
    void decideWhenDealerIsAliveAndguestBust(Card card1, Card card2, Card card3, Card card4,
                                                   Card card5, Result expected) {
        Dealer dealer = new Dealer();
        dealer.takeCard(card1);
        dealer.takeCard(card2);

        Guests guests = new Guests(List.of("엘리", "배카라"));
        for (Guest guest : guests) {
            guest.takeCard(card3);
            guest.takeCard(card4);
            guest.takeCard(card5);
        }

        for (Guest guest : guests) {
            assertThat(Result.of(dealer, guest)).isEqualTo(expected);
        }
    }

    static Stream<Arguments> generateDecideWhenDealerIsAliveAndGuestBustArguments() {
        return Stream.of(
                Arguments.of(
                        new Card(Denomination.NINE, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.HEART),
                        new Card(Denomination.TEN, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.SPADE),
                        new Card(Denomination.FIVE, Suit.HEART),
                        Result.LOSE
                ),
                Arguments.of(
                        new Card(Denomination.ACE, Suit.CLOVER), new Card(Denomination.QUEEN, Suit.HEART),
                        new Card(Denomination.QUEEN, Suit.CLOVER), new Card(Denomination.TWO, Suit.SPADE),
                        new Card(Denomination.JACK, Suit.HEART),
                        Result.LOSE
                )
        );
    }

    @ParameterizedTest(name = "[{index}] 딜러 : {0}, {1}, 참가자 : {3}, {4}, {5} -> {6}")
    @MethodSource("generateDecideWhenDealerBustArguments")
    @DisplayName("딜러의 점수가 21점 초과일 경우(버스트), 참가자의 승패를 테스트한다.")
    void decideWhenDealerBust(Card card1, Card card2, Card card3,
                              Card card4, Card card5, Card card6,
                              Result expected) {
        Dealer dealer = new Dealer();
        dealer.takeCard(card1);
        dealer.takeCard(card2);
        dealer.takeCard(card3);

        Guests guests = new Guests(List.of("엘리", "배카라"));
        for (Guest guest : guests) {
            guest.takeCard(card4);
            guest.takeCard(card5);
            guest.takeCard(card6);
        }

        for (Guest guest : guests) {
            assertThat(Result.of(dealer, guest)).isEqualTo(expected);
        }
    }

    static Stream<Arguments> generateDecideWhenDealerBustArguments() {
        return Stream.of(
                Arguments.of(
                        new Card(Denomination.NINE, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.HEART),
                        new Card(Denomination.QUEEN, Suit.CLOVER), new Card(Denomination.TEN, Suit.SPADE),
                        new Card(Denomination.EIGHT, Suit.HEART), new Card(Denomination.FIVE, Suit.HEART),
                        Result.LOSE
                ),
                Arguments.of(
                        new Card(Denomination.NINE, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.HEART),
                        new Card(Denomination.QUEEN, Suit.CLOVER), new Card(Denomination.TWO, Suit.SPADE),
                        new Card(Denomination.THREE, Suit.HEART), new Card(Denomination.FIVE, Suit.HEART),
                        Result.WIN
                )
        );
    }
}
