import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @Test
    @DisplayName("카드는 4가지 문양을 가질 수 있다")
    void cardTypeTest() {
        assertThat(CardType.values()).hasSize(4);
    }

    @Test
    @DisplayName("J, Q, K는 각각 10으로 계산한다")
    void cardNumberTest() {
        assertThat(CardNumber.JACK.getNumber()).isEqualTo(10);
        assertThat(CardNumber.QUEEN.getNumber()).isEqualTo(10);
        assertThat(CardNumber.KING.getNumber()).isEqualTo(10);
    }

    @Test
    @DisplayName("플레이어는 카드 두 장을 지급 받는다")
    void playerGetCardsTest() {
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.THREE);
        Player player = new Player("Pobi");
        player.addCard(card1);
        player.addCard(card2);

        assertThat(player.getCards()).hasSize(2);
    }
    
    @Test
    @DisplayName("21이하인 경우 버스트되지 않는다")
    void additionalCardBustTest1() {
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.QUEEN);
        Player player = new Player("Pobi");
        player.addCard(card1);
        player.addCard(card2);

        assertThat(player.isBust()).isFalse();
    }

    @Test
    @DisplayName("21초과인 경우 버스트된다")
    void additionalCardBustTest2() {
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.QUEEN);
        Card card3 = new Card(CardType.CLOVER, CardNumber.EIGHT);
        Player player = new Player("Pobi");
        player.addCard(card1);
        player.addCard(card2);
        player.addCard(card3);

        assertThat(player.isBust()).isTrue();
    }

    @Test
    @DisplayName("버스트되었을 경우 카드를 추가로 지급받을 수 없다")
    void additionalCardFalseTest() {
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.QUEEN);
        Card card3 = new Card(CardType.CLOVER, CardNumber.EIGHT);
        Player player = new Player("Pobi");
        player.addCard(card1);
        player.addCard(card2);
        player.addCard(card3);

        assertThat(player.canReceiveAdditionalCards()).isFalse();
    }

    @Test
    @DisplayName("버스트되지 않았을 경우 카드를 추가로 지급받을 수 있다")
    void additionalCardFalseTest2() {
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.QUEEN);
        Player player = new Player("Pobi");
        player.addCard(card1);
        player.addCard(card2);

        assertThat(player.canReceiveAdditionalCards()).isTrue();
    }

    @Test
    @DisplayName("딜러는 카드 숫자 합이 16 이하이면 카드를 추가로 받을 수 있다")
    void dealerAdditionalCardTest() {
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.TWO);
        Dealer dealer = new Dealer();
        dealer.addCard(card1);
        dealer.addCard(card2);

        assertThat(dealer.canReceiveAdditionalCards()).isTrue();
    }

    @Test
    @DisplayName("딜러는 카드 숫자 합이 16 초과면 카드를 추가로 받을 수 없다")
    void dealerAdditionalCardTest2() {
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.QUEEN);
        Dealer dealer = new Dealer();
        dealer.addCard(card1);
        dealer.addCard(card2);

        assertThat(dealer.canReceiveAdditionalCards()).isFalse();
    }
}
