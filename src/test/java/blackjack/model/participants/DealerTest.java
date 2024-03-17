package blackjack.model.participants;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.cards.Card;
import blackjack.model.cards.CardNumber;
import blackjack.model.cards.CardShape;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DealerTest {

    @DisplayName("기준 점수와 비교하여 카드를 더 받을 수 있는지 판단한다.")
    @ParameterizedTest
    @CsvSource(value = {"FIVE,true", "SIX,true", "SEVEN,false", "EIGHT,false"})
    void checkCanGetMoreCard(CardNumber given, boolean expected) {
        Dealer dealer = new Dealer();
        List<Card> cards = List.of(
                new Card(given, CardShape.DIAMOND),
                new Card(CardNumber.KING, CardShape.SPADE)
        );
        dealer.addCards(cards);

        boolean result = dealer.checkCanGetMoreCard();
        assertThat(result).isEqualTo(expected);
    }
}
