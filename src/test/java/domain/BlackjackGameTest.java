package domain;

import static fixture.PlayersFixture.플레이어들;

import org.junit.jupiter.api.Test;

class BlackjackGameTest {
    @Test
    void 게임을_시작하기_전에_카드를_나눈다() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck);
        Player roro = new Player("뽀로로");
        Player prin = new Player("프린");
        Player pobi = new Player("포비");
        Players players = 플레이어들(roro, prin, pobi);
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players);

        blackjackGame.initGame();

//        assertThat(roro.getCards()).hasSize(2);
//        assertThat(prin.getCards()).hasSize(2);
//        assertThat(dealer).extracting("cards").extracting("cards", InstanceOfAssertFactories.list(Card.class))
//                .hasSize(2);
//        assertThat(roro).extracting("cards").extracting("cards", InstanceOfAssertFactories.list(Card.class))
//                .hasSize(2);
//        assertThat(prin).extracting("cards").extracting("cards", InstanceOfAssertFactories.list(Card.class))
//                .hasSize(2);
//        assertThat(pobi).extracting("cards").extracting("cards", InstanceOfAssertFactories.list(Card.class))
//                .hasSize(2);
    }
}
