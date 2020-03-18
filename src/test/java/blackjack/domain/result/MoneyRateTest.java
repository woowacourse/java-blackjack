package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.card.component.CardNumber;
import blackjack.domain.card.component.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyRateTest {

    @DisplayName("WinRate 블랙잭여부에 따라 이율 차등 배분")
    @ParameterizedTest
    @CsvSource(value = {"KING,1.5", "ACE,1"})
    void apply(CardNumber addingNumber, double expectRate) {
        //given
        CardBundle cardBundle = CardBundle.emptyBundle();
        cardBundle.addCard(Card.of(Symbol.DIAMOND, CardNumber.ACE));
        cardBundle.addCard(Card.of(Symbol.DIAMOND, addingNumber));

        //when
        MoneyRate moneyRate = new WinRate();
        Double actualRate = moneyRate.getRate(cardBundle);

        //then
        assertThat(actualRate).isEqualTo(expectRate);
    }

    @DisplayName("DrawRate = 0 반환")
    @Test
    void apply2() {
        //given
        CardBundle cardBundle = CardBundle.emptyBundle();
        cardBundle.addCard(Card.of(Symbol.DIAMOND, CardNumber.ACE));
        cardBundle.addCard(Card.of(Symbol.DIAMOND, CardNumber.ACE));

        //when
        MoneyRate moneyRate = new DrawRate();
        Double actualRate = moneyRate.getRate(cardBundle);

        //then
        assertThat(actualRate).isEqualTo(0D);
    }

    @DisplayName("LoseRate = -1 반환")
    @Test
    void apply3() {
        //given
        CardBundle cardBundle = CardBundle.emptyBundle();
        cardBundle.addCard(Card.of(Symbol.DIAMOND, CardNumber.ACE));
        cardBundle.addCard(Card.of(Symbol.DIAMOND, CardNumber.ACE));

        //when
        MoneyRate moneyRate = new LoseRate();
        Double actualRate = moneyRate.getRate(cardBundle);

        //then
        assertThat(actualRate).isEqualTo(-1D);
    }
}