package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.result.Result;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ResultTest {

    @ParameterizedTest
    @MethodSource("parameters1")
    @DisplayName("딜러의 숫자가 20일 때 승패를 결정한다.")
    void getResultWhenDealer20(Result result, List<Card> cards) {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.ACE, Suit.DIAMOND));
        dealer.addCard(new Card(Denomination.NINE, Suit.DIAMOND));

        Participant participant = new Participant("배카라");
        for (Card card : cards) {
            participant.addCard(card);
        }

        Score dealerScore = new Score(dealer);
        Score participantScore = new Score(participant);

        assertThat(Result.decide(dealerScore, participantScore)).isEqualTo(result);
    }

    static Stream<Arguments> parameters1() {
        return Stream.of(
                Arguments.of(Result.WIN, List.of(new Card(Denomination.TEN, Suit.DIAMOND),
                        new Card(Denomination.ACE, Suit.DIAMOND))),
                Arguments.of(Result.LOSE, List.of(new Card(Denomination.TEN, Suit.DIAMOND),
                        new Card(Denomination.NINE, Suit.DIAMOND))),
                Arguments.of(Result.DRAW, List.of(new Card(Denomination.TEN, Suit.DIAMOND),
                        new Card(Denomination.KING, Suit.DIAMOND)))
        );
    }

    @ParameterizedTest
    @MethodSource("parameters2")
    @DisplayName("딜러의 숫자가 21일 때 승패를 결정한다.")
    void getResultWhenDealer21(Result result, List<Card> cards) {
        Dealer dealer = new Dealer();

        dealer.addCard(new Card(Denomination.ACE, Suit.DIAMOND));
        dealer.addCard(new Card(Denomination.TWO, Suit.DIAMOND));
        dealer.addCard(new Card(Denomination.EIGHT, Suit.DIAMOND));

        Participant participant = new Participant("배카라");
        for (Card card : cards) {
            participant.addCard(card);
        }

        Score dealerScore = new Score(dealer);
        Score participantScore = new Score(participant);

        assertThat(Result.decide(dealerScore, participantScore)).isEqualTo(result);
    }

    static Stream<Arguments> parameters2() {
        return Stream.of(
                Arguments.of(Result.WIN, List.of(new Card(Denomination.TEN, Suit.DIAMOND),
                        new Card(Denomination.ACE, Suit.DIAMOND))),
                Arguments.of(Result.DRAW, List.of(new Card(Denomination.TEN, Suit.DIAMOND),
                        new Card(Denomination.FIVE, Suit.DIAMOND),
                        new Card(Denomination.SIX, Suit.DIAMOND))),
                Arguments.of(Result.LOSE, List.of(new Card(Denomination.TEN, Suit.DIAMOND),
                        new Card(Denomination.NINE, Suit.DIAMOND))),
                Arguments.of(Result.LOSE, List.of(new Card(Denomination.TEN, Suit.DIAMOND),
                        new Card(Denomination.KING, Suit.DIAMOND)))
        );
    }

    @ParameterizedTest
    @MethodSource("parameters3")
    @DisplayName("딜러의 숫자가 22일 때 승패를 결정한다.")
    void getResultWhenDealer22(Result result, List<Card> cards) {
        Dealer dealer = new Dealer();

        dealer.addCard(new Card(Denomination.TEN, Suit.DIAMOND));
        dealer.addCard(new Card(Denomination.SIX, Suit.DIAMOND));
        dealer.addCard(new Card(Denomination.SIX, Suit.HEART));

        Participant participant = new Participant("배카라");
        for (Card card : cards) {
            participant.addCard(card);
        }

        Score dealerScore = new Score(dealer);
        Score participantScore = new Score(participant);

        assertThat(Result.decide(dealerScore, participantScore)).isEqualTo(result);
    }

    static Stream<Arguments> parameters3() {
        return Stream.of(
                Arguments.of(Result.WIN, List.of(new Card(Denomination.TEN, Suit.DIAMOND),
                        new Card(Denomination.KING, Suit.DIAMOND))),
                Arguments.of(Result.LOSE, List.of(new Card(Denomination.TEN, Suit.DIAMOND),
                        new Card(Denomination.NINE, Suit.DIAMOND),
                        new Card(Denomination.THREE, Suit.HEART)))
        );
    }

    @ParameterizedTest
    @MethodSource("parameters4")
    @DisplayName("딜러가 블랙잭일 때 승패를 결정한다.")
    void getResultWhenDealerBlackJack(Result result, List<Card> cards) {
        Dealer dealer = new Dealer();

        dealer.addCard(new Card(Denomination.ACE, Suit.DIAMOND));
        dealer.addCard(new Card(Denomination.TEN, Suit.DIAMOND));

        Participant participant = new Participant("배카라");
        for (Card card : cards) {
            participant.addCard(card);
        }

        Score dealerScore = new Score(dealer);
        Score participantScore = new Score(participant);

        assertThat(Result.decide(dealerScore, participantScore)).isEqualTo(result);
    }

    static Stream<Arguments> parameters4() {
        return Stream.of(
                Arguments.of(Result.DRAW, List.of(new Card(Denomination.TEN, Suit.DIAMOND),
                        new Card(Denomination.ACE, Suit.DIAMOND))),
                Arguments.of(Result.LOSE, List.of(new Card(Denomination.TEN, Suit.DIAMOND),
                        new Card(Denomination.FIVE, Suit.DIAMOND),
                        new Card(Denomination.SIX, Suit.DIAMOND))),
                Arguments.of(Result.LOSE, List.of(new Card(Denomination.TEN, Suit.DIAMOND),
                        new Card(Denomination.NINE, Suit.DIAMOND))),
                Arguments.of(Result.LOSE, List.of(new Card(Denomination.TEN, Suit.DIAMOND),
                        new Card(Denomination.KING, Suit.DIAMOND)))
        );
    }
}
