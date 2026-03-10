package blackjack.controller;

import blackjack.model.card.Card;
import blackjack.model.CardProvider;
import blackjack.model.user.Player;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackControllerTest {
    private final CardProvider cardProvider = new CardProvider();
    private final BlackjackController blackjackController = new BlackjackController(cardProvider);


    @Test
    @DisplayName("카드의 합이 21이상인 경우 카드 추가 지급 불가 ( false 반환 )")
    void test_checkAddCard_return_false() {

        Player player = new Player("pobi");
        player.addCard(new Card(Rank.J, Suit.DIAMOND));
        player.addCard(new Card(Rank.Q, Suit.CLOVER));
        player.addCard(new Card(Rank.K, Suit.HEART));

        Assertions.assertThat(blackjackController.checkAddCard(player)).isFalse();
    }

    @Test
    @DisplayName("카드의 합이 21 이하인 경우 카드 추가 지급 가능 ( true 반환 )")
    void test_checkAddCard_return_true() {

        Player player = new Player("pobi");
        player.addCard(new Card(Rank.J, Suit.DIAMOND));
        player.addCard(new Card(Rank.Q, Suit.CLOVER));

        Assertions.assertThat(blackjackController.checkAddCard(player)).isTrue();
    }
}