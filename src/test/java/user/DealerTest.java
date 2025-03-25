package user;

import card.Card;
import card.CardDeck;
import card.CardRank;
import card.CardShape;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    @DisplayName("딜러는 자신이 가진 하나의 카드만 공개해야 한다")
    @Test
    void test1() {
        // given
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck();
        dealer.drawCard(cardDeck.drawCard());
        dealer.drawCard(cardDeck.drawCard());

        // when
        List<Card> cards = dealer.openInitialCard();

        // then
        Assertions.assertThat(cards).hasSize(1);
    }

    @DisplayName("딜러는 카드의 총 합이 16 이하일 때만 카드를 뽑을 수 있다")
    @Test
    void test2() {
        // given
        Dealer dealer = new Dealer();
        dealer.drawCard(new Card(CardShape.CLOVER, CardRank.TEN));
        dealer.drawCard(new Card(CardShape.CLOVER, CardRank.SIX));

        // when
        boolean drawable = dealer.isDrawable();

        // then
        Assertions.assertThat(drawable).isTrue();
    }

    @DisplayName("딜러는 플레이어가 아니다.")
    @Test
    void test3() {
        // given
        Dealer dealer = new Dealer();

        // when
        boolean isPlayer = dealer.isPlayer();

        // then
        Assertions.assertThat(isPlayer).isFalse();
    }
}