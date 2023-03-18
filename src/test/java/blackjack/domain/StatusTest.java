package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.result.Status;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class StatusTest {

    @Test
    @DisplayName("of()는 두개의 인자를 받아 해당 이넘을 반환한다.")
    void blackjack() {
        List<Card> cards = List.of(new Card(CardNumber.QUEEN, CardSymbol.HEART),new Card(CardNumber.ACE, CardSymbol.HEART));
        // then
        Assertions.assertThat(Status.of(cards)).isEqualTo(Status.BLACKJACK);
    }

    @Test
    @DisplayName("of()는 두개의 인자를 받아 해당 이넘을 반환한다.")
    void bust() {
        List<Card> cards = List.of(new Card(CardNumber.QUEEN, CardSymbol.HEART),new Card(CardNumber.TWO, CardSymbol.HEART),new Card(CardNumber.JACK, CardSymbol.HEART));
        // then
        Assertions.assertThat(Status.of(cards)).isEqualTo(Status.BUST);
    }

    @Test
    @DisplayName("of()는 두개의 인자를 받아 해당 이넘을 반환한다.")
    void none() {
        List<Card> cards = List.of(new Card(CardNumber.QUEEN, CardSymbol.HEART),new Card(CardNumber.ACE, CardSymbol.HEART),new Card(CardNumber.JACK, CardSymbol.HEART));
        // then
        Assertions.assertThat(Status.of(cards)).isEqualTo(Status.NONE);
    }
}
