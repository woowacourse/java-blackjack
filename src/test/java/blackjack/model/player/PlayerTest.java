package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.TrumpNumber;
import blackjack.model.card.TrumpSymbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("카드 점수의 합이 21이고 카드 개수가 2개 이면 true를 반환한다.")
    @Test
    void isBlackJack_true() {
        Player dealer = new Dealer();
        dealer.receive(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        dealer.receive(new Card(TrumpNumber.ACE, TrumpSymbol.CLOVER));

        assertThat(dealer.isBlackJack()).isTrue();
    }

    @DisplayName("카드 점수의 합이 21이고 카드 개수가 3개 이면 false를 반환한다.")
    @Test
    void isBlackJack_false() {
        Player gamer = new Gamer("리버");
        gamer.receive(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        gamer.receive(new Card(TrumpNumber.ACE, TrumpSymbol.CLOVER));
        gamer.receive(new Card(TrumpNumber.KING, TrumpSymbol.CLOVER));

        assertThat(gamer.isBlackJack()).isFalse();
    }
}