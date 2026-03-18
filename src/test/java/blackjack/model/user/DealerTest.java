package blackjack.model.user;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러 핸드의 합계가 17 미만인 경우 카드 추가 지급 가능 (true 반환)")
    void isHitAvailable_return_true() {
        //given
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Rank.K, Suit.DIAMOND));
        dealer.draw(new Card(Rank.SIX, Suit.DIAMOND));

        //when & then
        Assertions.assertThat(dealer.isHitAvailable()).isTrue();
    }

    @Test
    @DisplayName("딜러 핸드의 합계가 17 이상인 경우 카드 추가 지급 불가 (false 반환)")
    void isHitAvailable_return_false() {
        //given
        blackjack.model.user.Dealer dealer = new Dealer();
        dealer.draw(new Card(Rank.K, Suit.DIAMOND));
        dealer.draw(new Card(Rank.SEVEN, Suit.DIAMOND));

        //when & then
        Assertions.assertThat(dealer.isHitAvailable()).isFalse();
    }
}