package blackjack.domain;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardFigure;
import blackjack.domain.result.ResultType;
import blackjack.domain.user.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @DisplayName("딜러의 카드 합이 16미만일 때 확인")
    @Test
    void canReceiveMoreCardTest_SUM_UNDER_16() {
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck();

        dealer.addCard(cardDeck.getCard(CardNumber.FIVE, CardFigure.CLOVER));
        dealer.addCard(cardDeck.getCard(CardNumber.FIVE, CardFigure.HEART));

        boolean expected = true;
        assertThat(dealer.canReceiveMoreCard()).isEqualTo(expected);
    }

    @DisplayName("딜러의 카드 합이 16초과할 때 확인")
    @Test
    void canReceiveMoreCard_SUM_OVER_16() {
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck();

        dealer.addCard(cardDeck.getCard(CardNumber.QUEEN, CardFigure.CLOVER));
        dealer.addCard(cardDeck.getCard(CardNumber.KING, CardFigure.CLOVER));

        boolean expected = false;
        assertThat(dealer.canReceiveMoreCard()).isEqualTo(expected);
    }

    @DisplayName("플레이어의 계산 결과를 바탕으로 Dealer의 결과 계산")
    @ParameterizedTest
    @CsvSource(value = {"WIN,1", "DRAW,1", "LOSE,2"})
    void computeResultTest(ResultType resultType, int expected) {
        Dealer dealer = new Dealer();
        List<ResultType> playerResultType = Arrays.asList(ResultType.WIN, ResultType.WIN, ResultType.DRAW, ResultType.LOSE);
        dealer.computeResult(playerResultType);

        int actual = dealer.getResultSum(resultType);
        assertThat(actual).isEqualTo(expected);
    }

}
