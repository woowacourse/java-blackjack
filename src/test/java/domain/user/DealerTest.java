package domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Card;
import domain.CardHand;
import domain.CardNumber;
import domain.Symbol;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    @Test
    @DisplayName("점수를 계산한다.")
    void 점수_계산() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(Symbol.SPADE, CardNumber.TWO));
        cardHand.add(new Card(Symbol.CLOVER, CardNumber.KING));
        Dealer dealer = new Dealer(cardHand);
        assertThat(dealer.calculateScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("카드를 더 받을 수 있는 경우 true를 반환한다.")
    void 카드_추가_가능() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(Symbol.SPADE, CardNumber.TWO));
        cardHand.add(new Card(Symbol.CLOVER, CardNumber.KING));
        Dealer dealer = new Dealer(cardHand);
        assertThat(dealer.canAdd()).isTrue();
    }

    @Test
    @DisplayName("카드를 더 받을 수 없는 경우 false를 반환한다.")
    void 카드_추가_불가능() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(Symbol.SPADE, CardNumber.SEVEN));
        cardHand.add(new Card(Symbol.CLOVER, CardNumber.KING));
        Dealer dealer = new Dealer(cardHand);
        assertThat(dealer.canAdd()).isFalse();
    }

    @Test
    @DisplayName("카드를 저장한다.")
    void 카드_저장() {
        Dealer dealer = new Dealer();
        assertThat(dealer.calculateScore()).isEqualTo(0);
        dealer.addCard(new Card(Symbol.SPADE, CardNumber.TWO));
        assertThat(dealer.calculateScore()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드를 추가하는 것이 불가능할 때 추가하려는 경우 예외가 발생한다.")
    void 카드_저장_불가능시_예외() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(Symbol.SPADE, CardNumber.ACE));
        cardHand.add(new Card(Symbol.CLOVER, CardNumber.KING));
        cardHand.add(new Card(Symbol.CLOVER, CardNumber.KING));
        Dealer dealer = new Dealer(cardHand);
        assertThatThrownBy(() -> dealer.addCard(new Card(Symbol.SPADE, CardNumber.ACE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드 추가가 불가능하여 실행되지 않았습니다.");
    }

    @Test
    @DisplayName("Dealer가 가진 카드의 점수의 합이 블랙잭이면 true를 반환한다.")
    void 카드_블랙잭_인지_확인() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(Symbol.SPADE, CardNumber.ACE));
        cardHand.add(new Card(Symbol.CLOVER, CardNumber.KING));
        Dealer dealer = new Dealer(cardHand);
        assertThat(dealer.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("Dealer가 가진 카드의 점수의 합이 블랙잭이 아니면 false를 반환한다.")
    void 카드_블랙잭_아닌지_확인() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(Symbol.CLOVER, CardNumber.KING));
        cardHand.add(new Card(Symbol.CLOVER, CardNumber.KING));
        Dealer dealer = new Dealer(cardHand);
        assertThat(dealer.isBlackjack()).isFalse();
    }

    @DisplayName("bust인 경우")
    @Test
    void 플레이어가_bust인_경우() {
        final int betting = 10000;
        CardHand playerCardHand = new CardHand();
        playerCardHand.add(new Card(Symbol.HEART, CardNumber.JACK));
        playerCardHand.add(new Card(Symbol.HEART, CardNumber.QUEEN));
        playerCardHand.add(new Card(Symbol.HEART, CardNumber.JACK));
        Player player = new Player("name", betting, playerCardHand);
        CardHand dealerCardHand = new CardHand();
        Dealer dealer = new Dealer(dealerCardHand);
        dealer.calculateAllResults(List.of(player));

        assertThat(dealer.calculateAllResults(List.of(player)).getPlayerResults().get(0).getBettingResult()
                .getResult()).isEqualTo(-10000);
    }

    @DisplayName("플레이어만 blackjack인 경우")
    @Test
    void 플레이어만_blackjack인_경우() {
        final int betting = 10000;
        CardHand playerCardHand = new CardHand();
        playerCardHand.add(new Card(Symbol.HEART, CardNumber.JACK));
        playerCardHand.add(new Card(Symbol.HEART, CardNumber.ACE));
        Player player = new Player("name", betting, playerCardHand);
        CardHand dealerCardHand = new CardHand();
        Dealer dealer = new Dealer(dealerCardHand);
        dealer.calculateAllResults(List.of(player));

        assertThat(dealer.calculateAllResults(List.of(player)).getPlayerResults().get(0).getBettingResult()
                .getResult()).isEqualTo(15000);
    }

    @DisplayName("딜러와 플레이어가 동시에 blackjack 경우")
    @Test
    void 딜러와_플레이어가_동시에_blackjack_경우() {
        final int betting = 10000;
        CardHand playerCardHand = new CardHand();
        playerCardHand.add(new Card(Symbol.HEART, CardNumber.JACK));
        playerCardHand.add(new Card(Symbol.HEART, CardNumber.ACE));
        Player player = new Player("name", betting, playerCardHand);
        CardHand dealerCardHand = new CardHand();
        dealerCardHand.add(new Card(Symbol.SPADE, CardNumber.JACK));
        dealerCardHand.add(new Card(Symbol.SPADE, CardNumber.ACE));
        Dealer dealer = new Dealer(dealerCardHand);
        dealer.calculateAllResults(List.of(player));

        assertThat(dealer.calculateAllResults(List.of(player)).getPlayerResults().get(0).getBettingResult()
                .getResult()).isEqualTo(0);
    }

    @DisplayName("플레이어가 21미만이고 딜러가 bust인 경우")
    @Test
    void 플레이어가_21미만이고_딜러가_bust인_경우() {
        final int betting = 10000;
        CardHand playerCardHand = new CardHand();
        playerCardHand.add(new Card(Symbol.HEART, CardNumber.JACK));
        Player player = new Player("name", betting, playerCardHand);
        CardHand dealerCardHand = new CardHand();
        dealerCardHand.add(new Card(Symbol.SPADE, CardNumber.JACK));
        dealerCardHand.add(new Card(Symbol.SPADE, CardNumber.QUEEN));
        dealerCardHand.add(new Card(Symbol.SPADE, CardNumber.KING));
        Dealer dealer = new Dealer(dealerCardHand);
        dealer.calculateAllResults(List.of(player));

        assertThat(dealer.calculateAllResults(List.of(player)).getPlayerResults().get(0).getBettingResult()
                .getResult()).isEqualTo(10000);
    }

    @DisplayName("플레이어와 딜러가 둘다 21미만이고 플레이어 승")
    @Test
    void 플레이어와_딜러가_둘다_21미만이고_플레이어승() {
        final int betting = 10000;
        CardHand playerCardHand = new CardHand();
        playerCardHand.add(new Card(Symbol.HEART, CardNumber.JACK));
        playerCardHand.add(new Card(Symbol.HEART, CardNumber.KING));
        Player player = new Player("name", betting, playerCardHand);
        CardHand dealerCardHand = new CardHand();
        dealerCardHand.add(new Card(Symbol.SPADE, CardNumber.JACK));
        dealerCardHand.add(new Card(Symbol.SPADE, CardNumber.FIVE));
        Dealer dealer = new Dealer(dealerCardHand);
        dealer.calculateAllResults(List.of(player));

        assertThat(dealer.calculateAllResults(List.of(player)).getPlayerResults().get(0).getBettingResult()
                .getResult()).isEqualTo(10000);
    }

    @DisplayName("플레이어와 딜러가 둘다 21미만이고 무승부")
    @Test
    void 플레이어와_딜러가_둘다_21미만이고_무승부() {
        final int betting = 10000;
        CardHand playerCardHand = new CardHand();
        playerCardHand.add(new Card(Symbol.HEART, CardNumber.JACK));
        playerCardHand.add(new Card(Symbol.HEART, CardNumber.KING));
        Player player = new Player("name", betting, playerCardHand);
        CardHand dealerCardHand = new CardHand();
        dealerCardHand.add(new Card(Symbol.SPADE, CardNumber.JACK));
        dealerCardHand.add(new Card(Symbol.SPADE, CardNumber.KING));
        Dealer dealer = new Dealer(dealerCardHand);
        dealer.calculateAllResults(List.of(player));

        assertThat(dealer.calculateAllResults(List.of(player)).getPlayerResults().get(0).getBettingResult()
                .getResult()).isEqualTo(0);
    }

    @DisplayName("플레이어와 딜러가 둘다 21미만이고 플레이어 패")
    @Test
    void 플레이어와_딜러가_둘다_21미만이고_플레이어패() {
        final int betting = 10000;
        CardHand playerCardHand = new CardHand();
        playerCardHand.add(new Card(Symbol.HEART, CardNumber.JACK));
        playerCardHand.add(new Card(Symbol.HEART, CardNumber.FIVE));
        Player player = new Player("name", betting, playerCardHand);
        CardHand dealerCardHand = new CardHand();
        dealerCardHand.add(new Card(Symbol.SPADE, CardNumber.JACK));
        dealerCardHand.add(new Card(Symbol.SPADE, CardNumber.KING));
        Dealer dealer = new Dealer(dealerCardHand);
        dealer.calculateAllResults(List.of(player));

        assertThat(dealer.calculateAllResults(List.of(player)).getPlayerResults().get(0).getBettingResult()
                .getResult()).isEqualTo(-10000);
    }
}
