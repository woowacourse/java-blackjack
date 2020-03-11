package blackjack.player;

import blackjack.GameReport;
import blackjack.player.card.Card;
import blackjack.player.card.CardBundle;
import blackjack.player.card.component.CardNumber;
import blackjack.player.card.component.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @DisplayName("딜러와 겜블러가 비교하여 결과를 만들어 낸다.")
    @ParameterizedTest
    @CsvSource(value = {"TWO,-1", "FIVE,0", "SIX,1"})
    void getReport(CardNumber cardNumber, int result) {
        //given
        Player dealer = new Dealer(new CardBundle());
        dealer.addCard(new Card(Symbol.DIAMOND, CardNumber.FIVE));

        Player gambler = new Gambler(new CardBundle(), "bebop");
        gambler.addCard(new Card(Symbol.DIAMOND, cardNumber));

        //when
        GameReport report = dealer.getReport(gambler);

        //then
        assertThat(report).isEqualTo(new GameReport("bebop", result));
    }
}