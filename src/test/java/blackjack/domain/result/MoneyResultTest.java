package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Money;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MoneyResultTest {

    @ParameterizedTest(name = "[{index}] 딜러 {0}, {1}, 참가자 {2}, {3} -> {4} 원")
    @MethodSource("generateCalculateMoneyArguments")
    @DisplayName("참가자의 수익률을 계산한다.")
    void calculateMoney(Card card1, Card card2, Card card3, Card card4, long expected) {
        Dealer dealer = new Dealer();
        dealer.takeCard(card1);
        dealer.takeCard(card2);

        Participants participants = new Participants(List.of("김제니", "박채영"));
        for (Participant participant : participants) {
            participant.takeCard(card3);
            participant.takeCard(card4);
            participant.betMoney(Money.from(10_000));
        }

        MoneyResult moneyResult = new MoneyResult();
        moneyResult.calculateParticipantMoney(dealer, participants);
        Map<Participant, Money> moneys = moneyResult.getMoneys();

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

        Participants participants = new Participants(List.of("김제니", "박채영"));
        for (Participant participant : participants) {
            participant.takeCard(card3);
            participant.takeCard(card4);
            participant.betMoney(Money.from(10_000));
        }

        MoneyResult moneyResult = new MoneyResult();
        moneyResult.calculateParticipantMoney(dealer, participants);

        assertThat(moneyResult.calculateDealerMoney()).isEqualTo(Money.from(expected));
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
