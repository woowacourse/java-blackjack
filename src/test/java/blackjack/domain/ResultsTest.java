package blackjack.domain;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ResultsTest {

    @ParameterizedTest(name = "[{index}] {2}")
    @MethodSource("parameters1")
    @DisplayName("딜러의 점수가 21점 이하인 경우, 참가자의 승패를 테스트한다.")
    void decideWhenDealerIsAlive(Dealer dealer, List<Participant> participants, List<Result> expected) {
        Results results = new Results(dealer, participants);

        assertThat(results.getValues()).contains(entry(participants.get(0), expected.get(0)),
                entry(participants.get(1), expected.get(1)),
                entry(participants.get(2), expected.get(2))
        );
    }

    static Stream<Arguments> parameters1() {
        return Stream.of(
                Arguments.of(
                        new Dealer(
                                List.of(new Card(Denomination.NINE, Suit.CLOVER),
                                        new Card(Denomination.EIGHT, Suit.HEART))),
                        Arrays.asList(
                                new Participant("엘리",
                                        List.of(new Card(Denomination.TEN, Suit.SPADE),
                                                new Card(Denomination.ACE, Suit.HEART))),
                                new Participant("배카라",
                                        List.of(new Card(Denomination.ACE, Suit.CLOVER),
                                                new Card(Denomination.SIX, Suit.DIAMOND))
                                ),
                                new Participant("포비",
                                        List.of(new Card(Denomination.ACE, Suit.CLOVER),
                                                new Card(Denomination.TWO, Suit.DIAMOND))
                                )
                        ),
                        List.of(Result.WIN, Result.DRAW, Result.LOSE)
                ),
                Arguments.of(
                        new Dealer(
                                List.of(new Card(Denomination.NINE, Suit.CLOVER),
                                        new Card(Denomination.EIGHT, Suit.HEART))),
                        Arrays.asList(
                                new Participant("엘리",
                                        List.of(new Card(Denomination.TEN, Suit.SPADE),
                                                new Card(Denomination.NINE, Suit.HEART))),
                                new Participant("배카라",
                                        List.of(new Card(Denomination.THREE, Suit.CLOVER),
                                                new Card(Denomination.EIGHT, Suit.DIAMOND))),
                                new Participant("포비",
                                        List.of(new Card(Denomination.TEN, Suit.CLOVER),
                                                new Card(Denomination.SEVEN, Suit.DIAMOND)))
                        ),
                        List.of(Result.WIN, Result.LOSE, Result.DRAW)
                ),

                Arguments.of(
                        new Dealer(
                                List.of(new Card(Denomination.ACE, Suit.CLOVER),
                                        new Card(Denomination.TEN, Suit.HEART))),
                        Arrays.asList(
                                new Participant("엘리",
                                        List.of(new Card(Denomination.TEN, Suit.SPADE),
                                                new Card(Denomination.NINE, Suit.HEART))),
                                new Participant("배카라",
                                        List.of(new Card(Denomination.THREE, Suit.CLOVER),
                                                new Card(Denomination.EIGHT, Suit.DIAMOND))),
                                new Participant("포비",
                                        List.of(new Card(Denomination.TEN, Suit.CLOVER),
                                                new Card(Denomination.ACE, Suit.DIAMOND)))
                        ),
                        List.of(Result.LOSE, Result.LOSE, Result.DRAW)
                )
        );
    }

    @ParameterizedTest(name = "[{index}] {3}")
    @MethodSource("parameters2")
    @DisplayName("딜러의 점수가 21점 이하이고 참가자가 버스트가 있는 경우, 참가자의 승패를 테스트한다.")
    void decideWhenDealerIsAliveAndParticipantBust(Dealer dealer, Participant participant, Card card, Result expected) {
        participant.addCard(card);

        Results results = new Results(dealer, List.of(participant));

        assertThat(results.getValues()).contains(entry(participant, expected));
    }

    static Stream<Arguments> parameters2() {
        return Stream.of(
                Arguments.of(
                        new Dealer(
                                List.of(new Card(Denomination.NINE, Suit.CLOVER),
                                        new Card(Denomination.EIGHT, Suit.HEART))),
                        new Participant("엘리", List.of(new Card(Denomination.TEN, Suit.CLOVER),
                                new Card(Denomination.EIGHT, Suit.SPADE))),
                        new Card(Denomination.FIVE, Suit.HEART),
                        Result.LOSE),
                Arguments.of(
                        new Dealer(
                                List.of(new Card(Denomination.ACE, Suit.CLOVER),
                                        new Card(Denomination.QUEEN, Suit.HEART))),
                        new Participant("엘리", List.of(new Card(Denomination.QUEEN, Suit.CLOVER),
                                new Card(Denomination.TWO, Suit.SPADE))),
                        new Card(Denomination.JACK, Suit.HEART),
                        Result.LOSE)
        );
    }

    @ParameterizedTest(name = "[{index}] {4}")
    @MethodSource("parameters3")
    @DisplayName("딜러의 점수가 21점 초과일 경우(버스트), 참가자의 승패를 테스트한다.")
    void decideWhenDealerBust(Dealer dealer, Card dealerCard, Participant participant, Card participantCard,
                              Result expected) {

        dealer.addCard(dealerCard);
        participant.addCard(participantCard);

        Results results = new Results(dealer, List.of(participant));

        assertThat(results.getValues()).contains(entry(participant, expected));
    }

    static Stream<Arguments> parameters3() {
        return Stream.of(
                Arguments.of(
                        new Dealer(
                                List.of(new Card(Denomination.NINE, Suit.CLOVER),
                                        new Card(Denomination.EIGHT, Suit.HEART))),
                        new Card(Denomination.QUEEN, Suit.HEART),

                        new Participant("엘리", List.of(new Card(Denomination.TEN, Suit.CLOVER),
                                new Card(Denomination.EIGHT, Suit.SPADE))),
                        new Card(Denomination.FIVE, Suit.HEART),

                        Result.LOSE),
                Arguments.of(
                        new Dealer(
                                List.of(new Card(Denomination.NINE, Suit.CLOVER),
                                        new Card(Denomination.EIGHT, Suit.HEART))),
                        new Card(Denomination.QUEEN, Suit.HEART),

                        new Participant("엘리", List.of(new Card(Denomination.TWO, Suit.CLOVER),
                                new Card(Denomination.THREE, Suit.SPADE))),
                        new Card(Denomination.FIVE, Suit.HEART),

                        Result.WIN)
        );
    }
}
