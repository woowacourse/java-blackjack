package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Guests;
import blackjack.domain.player.Money;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GuestProfitTest {

    @ParameterizedTest(name = "[{index}] 딜러 {0}, {1}, 참가자 {2}, {3} -> {4} 원")
    @MethodSource("generateCalculateMoneyArguments")
    @DisplayName("참가자의 수익률을 계산한다.")
    void calculateMoney(Card card1, Card card2, Card card3, Card card4, long expected) {
        Dealer dealer = new Dealer();
        dealer.takeCard(card1);
        dealer.takeCard(card2);

        Guests guests = new Guests(List.of("김제니", "박채영"));
        for (Guest guest : guests) {
            guest.takeCard(card3);
            guest.takeCard(card4);
            guest.betMoney(Money.from(10_000));
        }

        GuestProfit guestProfit = new GuestProfit(dealer, guests);
        Map<Guest, Money> profits = guestProfit.getProfits();

        for (Guest guest : guests) {
            assertThat(profits).contains(entry(guest, Money.from(expected)));
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
}
