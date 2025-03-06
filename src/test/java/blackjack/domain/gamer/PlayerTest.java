package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;

class PlayerTest {

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
    void canReceiveAdditionalCards() {
    }
}
