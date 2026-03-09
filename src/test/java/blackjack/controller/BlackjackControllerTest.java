package blackjack.controller;

import blackjack.model.Deck;
import blackjack.view.InputView;
import blackjack.view.OutputView;

class BlackjackControllerTest {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final Deck deck = new Deck();
    private final BlackjackController blackjackController = new BlackjackController(inputView, outputView, deck);

//    @Test
//    @DisplayName("카드의 합이 21이상인 경우 카드 추가 지급 불가 ( false 반환 )")
//    void test_checkAddCard_return_false() {
//
//        Player player = new Player("pobi");
//        player.addCard(new Card(Suit.DIAMOND, Rank.JACK));
//        player.addCard(new Card(Suit.CLOVER, Rank.QUEEN));
//        player.addCard(new Card(Suit.HEART, Rank.TEN));
//
//        Assertions.assertThat(blackjackController.checkAddCard(player)).isFalse();
//    }

//    @Test
//    @DisplayName("카드의 합이 21이상인 경우 카드 추가 지급 불가 ( false 반환 )")
//    void test_checkAddCard_return_true() {
//
//        Player player = new Player("pobi");
//        player.addCard(new Card(Suit.DIAMOND, Rank.JACK));
//        player.addCard(new Card(Suit.CLOVER, Rank.QUEEN));
//
//        Assertions.assertThat(blackjackController.checkAddCard(player)).isTrue();
//    }
}
