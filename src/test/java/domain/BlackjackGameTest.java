package domain;

import static fixture.CardFixture.전체_카드;
import static fixture.PlayersFixture.플레이어들;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {
    @Test
    void 게임을_시작하기_전에_카드를_나눈다() { // todo 플레이어의 카드 개수도 검증
        CardDeck cardDeck = new CardDeck(전체_카드());
        Dealer dealer = new Dealer(cardDeck, cards -> {
        });
        Players players = 플레이어들("뽀로로", "프린", "포비");
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players);

        blackjackGame.initGame();

        assertThat(dealer).extracting("cards").extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .hasSize(2);
    }

    @Test
    void 블랙잭_게임_패인_결과를_알_수_있다() {
        // cards
        CardDeck cardDeck = new CardDeck(전체_카드());
        Dealer dealer = new Dealer(cardDeck, cards -> {
        });
        Players players = 플레이어들("뽀로로", "프린", "포비");
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players);

        blackjackGame.initGame();
    }
}
