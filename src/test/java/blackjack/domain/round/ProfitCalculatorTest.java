package blackjack.domain.round;

import blackjack.domain.card.CardNumber;
import blackjack.domain.deck.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.dto.response.ProfitsResponseDto;
import blackjack.fixture.DeckFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitCalculatorTest {

    private final ProfitCalculator profitCalculator = new ProfitCalculator();

    @Test
    @DisplayName("플레이어가 블랙잭 없이 승리할 경우 베팅 금액의 1배를 수익으로 계산한다")
    void playerWinTest() {
        // given
        Deck playerDeck = DeckFixture.deckOf(CardNumber.JACK, CardNumber.NINE);
        Deck dealerDeck = DeckFixture.deckOf(CardNumber.TWO, CardNumber.THREE);
        Player player = new Player("Pobi", playerDeck.draw(), playerDeck.draw());
        Dealer dealer = new Dealer(dealerDeck.draw(), dealerDeck.draw());

        // when
        profitCalculator.addPlayerBet("Pobi", 10_000);
        ProfitsResponseDto response = profitCalculator.getProfits(dealer, List.of(player));
        double actual = response.gamers().stream()
                .filter(gamer -> gamer.name().equals("Pobi"))
                .findFirst()
                .orElseThrow()
                .profit();

        // then
        assertThat(actual).isEqualTo(10_000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 승리할 경우 베팅 금액의 1.5배를 수익으로 계산한다")
    void playerBlackjackWinTest() {
        // given
        Deck playerDeck = DeckFixture.deckOf(CardNumber.ACE, CardNumber.JACK);
        Deck dealerDeck = DeckFixture.deckOf(CardNumber.TWO, CardNumber.THREE);
        Player player = new Player("Pobi", playerDeck.draw(), playerDeck.draw());
        Dealer dealer = new Dealer(dealerDeck.draw(), dealerDeck.draw());

        // when
        profitCalculator.addPlayerBet("Pobi", 10_000);
        ProfitsResponseDto response = profitCalculator.getProfits(dealer, List.of(player));
        double actual = response.gamers().stream()
                .filter(gamer -> gamer.name().equals("Pobi"))
                .findFirst()
                .orElseThrow()
                .profit();

        // then
        assertThat(actual).isEqualTo(15_000);
    }

    @Test
    @DisplayName("플레이어가 패배할 경우 수익을 0에서 베팅 금액을 차감한 값으로 계산한다")
    void playerLoseTest() {
        // given
        Deck playerDeck = DeckFixture.deckOf(CardNumber.TWO, CardNumber.THREE);
        Deck dealerDeck = DeckFixture.deckOf(CardNumber.EIGHT, CardNumber.NINE);
        Player player = new Player("Pobi", playerDeck.draw(), playerDeck.draw());
        Dealer dealer = new Dealer(dealerDeck.draw(), dealerDeck.draw());

        // when
        profitCalculator.addPlayerBet("Pobi", 20_000);
        ProfitsResponseDto response = profitCalculator.getProfits(dealer, List.of(player));
        double actual = response.gamers().stream()
                .filter(gamer -> gamer.name().equals("Pobi"))
                .findFirst()
                .orElseThrow()
                .profit();

        // then
        assertThat(actual).isEqualTo(-20_000);
    }

    @Test
    @DisplayName("플레이어가 버스트일 경우 딜러의 버스트 여부와 상관없이 수익을 0에서 베팅 금액을 차감한 값으로 계산한다")
    void playerBustTest() {
        // given
        Deck playerDeck = DeckFixture.deckOf(CardNumber.EIGHT, CardNumber.NINE, CardNumber.JACK);
        Deck dealerDeck = DeckFixture.deckOf(CardNumber.EIGHT, CardNumber.NINE, CardNumber.JACK);
        Player player = new Player("Pobi", playerDeck.draw(), playerDeck.draw(), playerDeck.draw());
        Dealer dealer = new Dealer(dealerDeck.draw(), dealerDeck.draw(), dealerDeck.draw());

        // when
        profitCalculator.addPlayerBet("Pobi", 20_000);
        ProfitsResponseDto response = profitCalculator.getProfits(dealer, List.of(player));
        double actual = response.gamers().stream()
                .filter(gamer -> gamer.name().equals("Pobi"))
                .findFirst()
                .orElseThrow()
                .profit();

        // then
        assertThat(actual).isEqualTo(-20_000);
    }

    @Test
    @DisplayName("딜러가 승리할 경우 플레이어가 베팅한 금액을 수익으로 계산한다")
    void dealerWinTest() {
        // given
        Deck playerDeck = DeckFixture.deckOf(CardNumber.TWO, CardNumber.THREE);
        Deck dealerDeck = DeckFixture.deckOf(CardNumber.EIGHT, CardNumber.NINE);
        Player player = new Player("Pobi", playerDeck.draw(), playerDeck.draw());
        Dealer dealer = new Dealer(dealerDeck.draw(), dealerDeck.draw());

        // when
        profitCalculator.addPlayerBet("Pobi", 30_000);
        ProfitsResponseDto response = profitCalculator.getProfits(dealer, List.of(player));
        double actual = response.gamers().stream()
                .filter(gamer -> gamer.name().equals("딜러"))
                .findFirst()
                .orElseThrow()
                .profit();

        // then
        assertThat(actual).isEqualTo(30_000);
    }

    @Test
    @DisplayName("딜러가 패배할 경우 수익을 플레이어의 베팅 금액을 차감한 값으로 계산한다")
    void dealerLoseTest() {
        // given
        Deck playerDeck = DeckFixture.deckOf(CardNumber.EIGHT, CardNumber.NINE, CardNumber.SEVEN, CardNumber.SIX);
        Deck dealerDeck = DeckFixture.deckOf(CardNumber.TWO, CardNumber.THREE);
        Player player1 = new Player("Pobi", playerDeck.draw(), playerDeck.draw());
        Player player2 = new Player("Neo", playerDeck.draw(), playerDeck.draw());
        Dealer dealer = new Dealer(dealerDeck.draw(), dealerDeck.draw());

        // when
        profitCalculator.addPlayerBet("Pobi", 10_000);
        profitCalculator.addPlayerBet("Neo", 12_000);
        ProfitsResponseDto response = profitCalculator.getProfits(dealer, List.of(player1, player2));
        double actual = response.gamers().stream()
                .filter(gamer -> gamer.name().equals("딜러"))
                .findFirst()
                .orElseThrow()
                .profit();

        // then
        assertThat(actual).isEqualTo(-22_000);
    }

    @ParameterizedTest
    @CsvSource({
            "TWO,EIGHT,THREE,SEVEN,0",
            "ACE,JACK,ACE,KING,0",
    })
    @DisplayName("무승부일 경우 플레이어의 수익을 0으로 계산한다")
    void playerTieTest(CardNumber cardNumber1, CardNumber cardNumber2, CardNumber cardNumber3,
                       CardNumber cardNumber4, double expected) {
        // given
        Deck playerDeck = DeckFixture.deckOf(cardNumber1, cardNumber2);
        Deck dealerDeck = DeckFixture.deckOf(cardNumber3, cardNumber4);
        Player player = new Player("Pobi", playerDeck.draw(), playerDeck.draw());
        Dealer dealer = new Dealer(dealerDeck.draw(), dealerDeck.draw());

        // when
        profitCalculator.addPlayerBet("Pobi", 10_000);
        ProfitsResponseDto response = profitCalculator.getProfits(dealer, List.of(player));
        double actual = response.gamers().stream()
                .filter(gamer -> gamer.name().equals("Pobi"))
                .findFirst()
                .orElseThrow()
                .profit();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "TWO,EIGHT,THREE,SEVEN,0",
            "ACE,JACK,ACE,KING,0",
    })
    @DisplayName("무승부일 경우 딜러의 수익을 0으로 계산한다")
    void dealerTieTest(CardNumber cardNumber1, CardNumber cardNumber2, CardNumber cardNumber3,
                       CardNumber cardNumber4, double expected) {
        // given
        Deck playerDeck = DeckFixture.deckOf(cardNumber1, cardNumber2);
        Deck dealerDeck = DeckFixture.deckOf(cardNumber3, cardNumber4);
        Player player = new Player("Pobi", playerDeck.draw(), playerDeck.draw());
        Dealer dealer = new Dealer(dealerDeck.draw(), dealerDeck.draw());

        // when
        profitCalculator.addPlayerBet("Pobi", 10_000);
        ProfitsResponseDto response = profitCalculator.getProfits(dealer, List.of(player));
        double actual = response.gamers().stream()
                .filter(gamer -> gamer.name().equals("딜러"))
                .findFirst()
                .orElseThrow()
                .profit();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
