package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class RatioMatherTest {

    @Test
    @DisplayName("calculateResult()는 딜러와 플레이어의 점수를 받아 플레이어의 결과를 반환한다.")
    void test_calculateResult() {
        // given & when
        List<Card> playerCards = List.of(new Card(CardNumber.JACK, CardSymbol.HEART), new Card(CardNumber.NINE, CardSymbol.HEART));
        List<Card> dealerCards = List.of(new Card(CardNumber.JACK, CardSymbol.HEART), new Card(CardNumber.EIGHT, CardSymbol.HEART));

        RatioMather ratioMather = RatioMather.of(playerCards, dealerCards);
        RatioMather expectedRatioMather = RatioMather.WIN;

        // then
        Assertions.assertThat(ratioMather).isEqualTo(expectedRatioMather);
    }
}
