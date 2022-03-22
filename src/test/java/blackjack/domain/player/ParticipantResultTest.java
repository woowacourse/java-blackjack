package blackjack.domain.player;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.result.ParticipantResult;
import blackjack.domain.result.Result;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ParticipantResultTest {

    @ParameterizedTest(name = "[{index}] 딜러 : {0}, {1}, 참가자 : {2}, {3} -> {4}")
    @MethodSource("decideWhenDealerIsAliveParameters")
    @DisplayName("딜러의 점수가 21점 이하인 경우, 참가자의 승패를 테스트한다.")
    void decideWhenDealerIsAlive(Card card1, Card card2, Card card3, Card card4, int expected) {
        Dealer dealer = new Dealer();
        dealer.addCard(card1);
        dealer.addCard(card2);

        Participants participants = new Participants(List.of("엘리"));
        for (Participant participant : participants) {
            participant.addCard(card3);
            participant.addCard(card4);
            participants.putBet(participant, 10000);
        }

        ParticipantResult results = new ParticipantResult(dealer, participants);

        for (Participant participant : participants) {
            assertThat(results.getParticipantYields()).contains(entry(participant, expected));
        }
    }

    static Stream<Arguments> decideWhenDealerIsAliveParameters() {
        return Stream.of(
                Arguments.of(
                        new Card(Denomination.NINE, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.HEART),
                        new Card(Denomination.TEN, Suit.SPADE), new Card(Denomination.ACE, Suit.HEART),
                        15000
                ),
                Arguments.of(
                        new Card(Denomination.NINE, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.HEART),
                        new Card(Denomination.ACE, Suit.SPADE), new Card(Denomination.SIX, Suit.HEART),
                        0
                ),
                Arguments.of(
                        new Card(Denomination.NINE, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.HEART),
                        new Card(Denomination.ACE, Suit.SPADE), new Card(Denomination.TWO, Suit.HEART),
                        -10000
                ),
                Arguments.of(
                        new Card(Denomination.NINE, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.HEART),
                        new Card(Denomination.TEN, Suit.SPADE), new Card(Denomination.NINE, Suit.HEART),
                        10000
                ),
                Arguments.of(
                        new Card(Denomination.NINE, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.HEART),
                        new Card(Denomination.THREE, Suit.SPADE), new Card(Denomination.EIGHT, Suit.HEART),
                        -10000
                ),
                Arguments.of(
                        new Card(Denomination.NINE, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.HEART),
                        new Card(Denomination.TEN, Suit.SPADE), new Card(Denomination.SEVEN, Suit.HEART),
                        0
                ),
                Arguments.of(
                        new Card(Denomination.ACE, Suit.CLOVER), new Card(Denomination.TEN, Suit.HEART),
                        new Card(Denomination.TEN, Suit.SPADE), new Card(Denomination.NINE, Suit.HEART),
                        -10000
                ),
                Arguments.of(
                        new Card(Denomination.ACE, Suit.CLOVER), new Card(Denomination.TEN, Suit.HEART),
                        new Card(Denomination.THREE, Suit.SPADE), new Card(Denomination.EIGHT, Suit.HEART),
                        -10000
                ),
                Arguments.of(
                        new Card(Denomination.ACE, Suit.CLOVER), new Card(Denomination.TEN, Suit.HEART),
                        new Card(Denomination.TEN, Suit.SPADE), new Card(Denomination.ACE, Suit.HEART),
                        0
                )
        );
    }

    @ParameterizedTest(name = "[{index}] 딜러 : {0}, {1}, 참가자 : {2}, {3}, {4} -> {5}")
    @MethodSource("decideWhenDealerIsAliveAndParticipantBustParameters")
    @DisplayName("딜러의 점수가 21점 이하이고 참가자가 버스트가 있는 경우, 참가자의 승패를 테스트한다.")
    void decideWhenDealerIsAliveAndParticipantBust(Card card1, Card card2, Card card3, Card card4,
                                                   Card card5, Integer expected) {
        Dealer dealer = new Dealer();
        dealer.addCard(card1);
        dealer.addCard(card2);

        Participants participants = new Participants(List.of("배카라"));
        for (Participant participant : participants) {
            participant.addCard(card3);
            participant.addCard(card4);
            participant.addCard(card5);
            participants.putBet(participant, 10000);
        }

        ParticipantResult results = new ParticipantResult(dealer, participants);

        for (Participant participant : participants) {
            assertThat(results.getParticipantYields()).contains(entry(participant, expected));
        }
    }

    static Stream<Arguments> decideWhenDealerIsAliveAndParticipantBustParameters() {
        return Stream.of(
                Arguments.of(
                        new Card(Denomination.NINE, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.HEART),
                        new Card(Denomination.TEN, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.SPADE),
                        new Card(Denomination.FIVE, Suit.HEART),
                        -10000
                ),
                Arguments.of(
                        new Card(Denomination.ACE, Suit.CLOVER), new Card(Denomination.QUEEN, Suit.HEART),
                        new Card(Denomination.QUEEN, Suit.CLOVER), new Card(Denomination.TWO, Suit.SPADE),
                        new Card(Denomination.JACK, Suit.HEART),
                        -10000
                )
        );
    }

    @ParameterizedTest(name = "[{index}] 딜러 : {0}, {1}, 참가자 : {3}, {4}, {5} -> {6}")
    @MethodSource("decideWhenDealerBustParameters")
    @DisplayName("딜러의 점수가 21점 초과일 경우(버스트), 참가자의 승패를 테스트한다.")
    void decideWhenDealerBust(Card card1, Card card2, Card card3,
                              Card card4, Card card5, Card card6,
                              Integer expected) {
        Dealer dealer = new Dealer();
        dealer.addCard(card1);
        dealer.addCard(card2);
        dealer.addCard(card3);

        Participants participants = new Participants(List.of("엘리"));
        for (Participant participant : participants) {
            participant.addCard(card4);
            participant.addCard(card5);
            participant.addCard(card6);
            participants.putBet(participant, 10000);
        }

        ParticipantResult results = new ParticipantResult(dealer, participants);

        for (Participant participant : participants) {
            assertThat(results.getParticipantYields()).contains(entry(participant, expected));
        }
    }

    static Stream<Arguments> decideWhenDealerBustParameters() {
        return Stream.of(
                Arguments.of(
                        new Card(Denomination.NINE, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.HEART),
                        new Card(Denomination.QUEEN, Suit.CLOVER), new Card(Denomination.TEN, Suit.SPADE),
                        new Card(Denomination.EIGHT, Suit.HEART), new Card(Denomination.FIVE, Suit.HEART),
                        -10000
                ),
                Arguments.of(
                        new Card(Denomination.NINE, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.HEART),
                        new Card(Denomination.QUEEN, Suit.CLOVER), new Card(Denomination.TWO, Suit.SPADE),
                        new Card(Denomination.THREE, Suit.HEART),new Card(Denomination.FIVE, Suit.HEART),
                        10000
                )
        );
    }
}
