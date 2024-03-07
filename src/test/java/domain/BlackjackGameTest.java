package domain;

import static fixture.CardFixture.전체_카드;
import static fixture.CardFixture.카드;
import static fixture.CardFixture.카드들;
import static fixture.PlayersFixture.플레이어;
import static fixture.PlayersFixture.플레이어들;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
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

        assertThat(dealer).extracting("cardHand").extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .hasSize(2);
    }

    @Test
    void 블랙잭_게임_패인_결과를_알_수_있다() {
        // cards
        List<Card> cards = 카드들(카드(Denomination.THREE), 카드(Denomination.TEN), 카드(Denomination.SIX),
                카드(Denomination.QUEEN));
        CardDeck cardDeck = new CardDeck(cards);
        Dealer dealer = new Dealer(cardDeck, cardlist -> {
        });
        Players players = 플레이어들("뽀로로");
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players);

        blackjackGame.initGame();

        GameResult result = blackjackGame.createGameResult();
        Map<Player, ResultStatus> playerResult = result.getPlayerResult();
        assertThat(playerResult.get(플레이어("뽀로로"))).isEqualTo(ResultStatus.LOSE);
    }
}
