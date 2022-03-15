package blackjack.domain.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import blackjack.domain.result.ParticipantResult;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class BettingMachineTest {

    @ParameterizedTest(name = "[{index}] {0}원 배팅 -> {1}원")
    @CsvSource({"10000, 10_000", "40000, 40_000", "100000, 100_000"})
    @DisplayName("참가자가 원하는 금액을 배팅한다.")
    void betMoney(String input, int expected) {
        Participant participant = new Participant("김제니");

        BettingMachine bettingMachine = new BettingMachine();
        bettingMachine.betMoney(participant, Money.from(input));

        assertThat(bettingMachine.getMoneys()).contains(entry(participant, Money.from(expected)));
    }

    @ParameterizedTest(name = "[{index}] 딜러 {0}, {1}, 참가자 {2}, {3} -> {4} 원")
    @MethodSource("generateCalculateMoneyArguments")
    @DisplayName("참가자의 수익률을 계산한다.")
    void calculateMoney(Card card1, Card card2, Card card3, Card card4, long expected) {
        Dealer dealer = new Dealer();
        dealer.takeCard(card1);
        dealer.takeCard(card2);

        BettingMachine bettingMachine = new BettingMachine();

        Participants participants = new Participants(List.of("김제니", "박채영"));
        for (Participant participant : participants) {
            participant.takeCard(card3);
            participant.takeCard(card4);
            bettingMachine.betMoney(participant, Money.from(10_000));
        }

        ParticipantResult participantResult = new ParticipantResult(dealer, participants);

        bettingMachine.calculateMoney(participantResult.getResult());
        Map<Participant, Money> moneys = bettingMachine.getMoneys();

        for (Participant participant : participants) {
            assertThat(moneys).contains(entry(participant, Money.from(expected)));
        }
    }

    static Stream<Arguments> generateCalculateMoneyArguments() {
        return Stream.of(
                Arguments.of(
                        new Card(Denomination.ACE, Suit.CLOVER), new Card(Denomination.FIVE, Suit.SPADE),
                        new Card(Denomination.ACE, Suit.HEART), new Card(Denomination.KING, Suit.CLOVER),
                        15_000
                ),
                Arguments.of(
                        new Card(Denomination.ACE, Suit.CLOVER), new Card(Denomination.FIVE, Suit.SPADE),
                        new Card(Denomination.ACE, Suit.HEART), new Card(Denomination.NINE, Suit.CLOVER),
                        10_000
                ),
                Arguments.of(
                        new Card(Denomination.ACE, Suit.CLOVER), new Card(Denomination.FIVE, Suit.SPADE),
                        new Card(Denomination.ACE, Suit.HEART), new Card(Denomination.FIVE, Suit.CLOVER),
                        0
                ),
                Arguments.of(
                        new Card(Denomination.ACE, Suit.CLOVER), new Card(Denomination.FIVE, Suit.SPADE),
                        new Card(Denomination.THREE, Suit.HEART), new Card(Denomination.NINE, Suit.CLOVER),
                        -10_000
                )
        );
    }

    @ParameterizedTest(name = "[{index}] 딜러 {0}, {1}, 참가자 {2}, {3} -> {4} 원")
    @MethodSource("generateCalculateDealerMoneyArguments")
    @DisplayName("딜러의 수익률을 계산한다.")
    void calculateDealerMoney(Card card1, Card card2, Card card3, Card card4, long expected) {
        Dealer dealer = new Dealer();
        dealer.takeCard(card1);
        dealer.takeCard(card2);

        BettingMachine bettingMachine = new BettingMachine();

        Participants participants = new Participants(List.of("김제니", "박채영"));
        for (Participant participant : participants) {
            participant.takeCard(card3);
            participant.takeCard(card4);
            bettingMachine.betMoney(participant, Money.from(10_000));
        }

        ParticipantResult participantResult = new ParticipantResult(dealer, participants);

        bettingMachine.calculateMoney(participantResult.getResult());
        assertThat(bettingMachine.calculateDealerMoney()).isEqualTo(Money.from(expected));
    }

    static Stream<Arguments> generateCalculateDealerMoneyArguments() {
        return Stream.of(
                Arguments.of(
                        new Card(Denomination.ACE, Suit.CLOVER), new Card(Denomination.FIVE, Suit.SPADE),
                        new Card(Denomination.ACE, Suit.HEART), new Card(Denomination.KING, Suit.CLOVER),
                        -30_000
                ),
                Arguments.of(
                        new Card(Denomination.ACE, Suit.CLOVER), new Card(Denomination.FIVE, Suit.SPADE),
                        new Card(Denomination.ACE, Suit.HEART), new Card(Denomination.NINE, Suit.CLOVER),
                        -20_000
                ),
                Arguments.of(
                        new Card(Denomination.ACE, Suit.CLOVER), new Card(Denomination.FIVE, Suit.SPADE),
                        new Card(Denomination.ACE, Suit.HEART), new Card(Denomination.FIVE, Suit.CLOVER),
                        0
                ),
                Arguments.of(
                        new Card(Denomination.ACE, Suit.CLOVER), new Card(Denomination.FIVE, Suit.SPADE),
                        new Card(Denomination.THREE, Suit.HEART), new Card(Denomination.NINE, Suit.CLOVER),
                        20_000
                )
        );
    }
}
