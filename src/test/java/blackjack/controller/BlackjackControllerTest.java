package blackjack.controller;

import blackjack.model.Card;
import blackjack.model.CardCalculator;
import blackjack.model.CardProvider;
import blackjack.model.Dealer;
import blackjack.model.Player;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackControllerTest {
    private final CardProvider cardProvider = new CardProvider();
    private final CardCalculator cardCalculator = new CardCalculator();
    private final BlackjackController blackjackController = new BlackjackController(cardProvider, cardCalculator);

    @Test
    @DisplayName("최초 배분 카드 두 장의 합이 21일 경우 블랙잭")
    void test_checkBlackjack() {
        Player player = new Player("pobi");
        List<Player> players = List.of(player);
        Dealer dealer = new Dealer();

        player.addCard(Card.A_CLOVER);
        player.addCard(Card.J_DIA);

        dealer.addCard(Card.A_CLOVER);
        dealer.addCard(Card.J_DIA);

        blackjackController.checkBlackjack(players, dealer);

        Assertions.assertThat(player.isBlackjack()).isTrue();
        Assertions.assertThat(dealer.isBlackjack()).isTrue();
    }


    @Test
    @DisplayName("카드의 합이 21이상인 경우 카드 추가 지급 불가 ( false 반환 )")
    void test_checkAddCard_return_false() {

        Player player = new Player("pobi");
        player.addCard(Card.J_DIA);
        player.addCard(Card.Q_CLOVER);
        player.addCard(Card.TEN_HEART);

        Assertions.assertThat(blackjackController.checkAddCard(player)).isFalse();
    }

    @Test
    @DisplayName("카드의 합이 21이상인 경우 카드 추가 지급 불가 ( false 반환 )")
    void test_checkAddCard_return_true() {

        Player player = new Player("pobi");
        player.addCard(Card.J_DIA);
        player.addCard(Card.Q_CLOVER);

        Assertions.assertThat(blackjackController.checkAddCard(player)).isTrue();
    }
}