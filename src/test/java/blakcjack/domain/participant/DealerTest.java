package blakcjack.domain.participant;

import blakcjack.domain.card.Card;
import blakcjack.domain.card.CardNumber;
import blakcjack.domain.card.CardSymbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @DisplayName("딜러 객체 생성 성공")
    @Test
    void create() {
        final Dealer dealer = new Dealer();
        assertThat(dealer).isEqualTo(new Dealer());
    }

    @DisplayName("카드 받기 성공")
    @Test
    void receiveCard() {
        final Dealer dealer = new Dealer();
        final Card card = Card.of(CardSymbol.CLUB, CardNumber.ACE);
        dealer.receiveCard(card);

        assertThat(dealer.showCardList()).isEqualTo(Collections.singletonList(Card.of(CardSymbol.CLUB, CardNumber.ACE)));
    }

    @DisplayName("17점 미만이면 통과")
    @Test
    void isScoreLowerThanSevenTeen() {
        final Dealer dealer = new Dealer();
        dealer.receiveCard(Card.of(CardSymbol.CLUB, CardNumber.TEN));
        dealer.receiveCard(Card.of(CardSymbol.CLUB, CardNumber.SIX));
        assertThat(dealer.needsAdditionalCard()).isTrue();

        dealer.receiveCard(Card.of(CardSymbol.CLUB, CardNumber.ACE));
        assertThat(dealer.needsAdditionalCard()).isFalse();
    }
}
