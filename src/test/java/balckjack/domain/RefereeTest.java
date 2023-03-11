package balckjack.domain;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RefereeTest {

    @ParameterizedTest
    @MethodSource("makeRefereeCase")
    void testRefereeCase(Referee referee, List<Double> playerMoneys, Double dealerMoney) {
        List<Double> playerWinningMoney = referee.calculateWinningMoneys();
        Double dealerWinningMoney = referee.calculateDealerWinningMoney();
        Assertions.assertThat(playerWinningMoney).isEqualTo(playerMoneys);
        Assertions.assertThat(dealerWinningMoney).isEqualTo(dealerMoney);
    }

    static Stream<Arguments> makeRefereeCase() {
        List<CardDeck> players = makePlayers();
        List<CardDeck> playersHaveBlackJack = makePlayersHaveBlackJack();
        CardDeck commonDealer = makeCardDeck(Pattern.SPADE, Denomination.TEN, Denomination.JACK);
        CardDeck maxDealer = makeCardDeck(Pattern.SPADE, Denomination.TEN, Denomination.FOUR,
            Denomination.SEVEN);
        CardDeck bustDealer = makeCardDeck(Pattern.SPADE, Denomination.TEN, Denomination.FOUR,
            Denomination.JACK);
        CardDeck blackJackDealer = makeCardDeck(Pattern.SPADE, Denomination.TEN, Denomination.ACE);
        List<Money> moneys = List.of(new Money(1000.0), new Money(1000.0), new Money(1500.0),
            new Money(2000.0));
        return Stream.of(
            Arguments.arguments(new Referee(commonDealer, players, moneys),
                List.of(1000.0, -1000.0, 0.0, -2000.0), 2000.0),
            Arguments.arguments(new Referee(bustDealer, players, moneys),
                List.of(1000.0, 1000.0, 1500.0, -2000.0), -1500.0),
            Arguments.arguments(new Referee(bustDealer, playersHaveBlackJack, moneys),
                List.of(1500.0, 1000.0, 1500.0, -2000.0), -2000.0),
            Arguments.arguments(new Referee(blackJackDealer, players, moneys),
                List.of(-1000.0, -1000.0, -1500.0, -2000.0), 5500.0),
            Arguments.arguments(new Referee(blackJackDealer, playersHaveBlackJack, moneys),
                List.of(0.0, -1000.0, -1500.0, -2000.0), 4500.0),
            Arguments.arguments(new Referee(maxDealer, playersHaveBlackJack, moneys),
                List.of(1500.0, -1000.0, -1500.0, -2000.0), 3000.0)
        );
    }

    private static List<CardDeck> makePlayers() {
        return List.of(
            makeCardDeck(Pattern.SPADE, Denomination.EIGHT, Denomination.ACE, Denomination.TWO),
            makeCardDeck(Pattern.SPADE, Denomination.NINE),
            makeCardDeck(Pattern.SPADE, Denomination.TEN, Denomination.FOUR, Denomination.SIX),
            makeCardDeck(Pattern.CLOVER, Denomination.TEN, Denomination.TEN, Denomination.KING)
        );
    }

    private static CardDeck makeCardDeck(Pattern pattern, Denomination... denominations) {
        CardDeck deck = new CardDeck();
        for (Denomination denomination : denominations) {
            deck.addCard(new Card(pattern, denomination));
        }
        return deck;
    }

    private static List<CardDeck> makePlayersHaveBlackJack() {
        return List.of(
            makeCardDeck(Pattern.HEART, Denomination.ACE, Denomination.KING),
            makeCardDeck(Pattern.SPADE, Denomination.NINE),
            makeCardDeck(Pattern.SPADE, Denomination.TEN, Denomination.FOUR, Denomination.SIX),
            makeCardDeck(Pattern.CLOVER, Denomination.TEN, Denomination.TEN, Denomination.KING)
        );
    }
}