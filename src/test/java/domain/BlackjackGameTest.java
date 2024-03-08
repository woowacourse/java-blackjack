package domain;

import static fixture.CardFixture.전체_카드;
import static fixture.CardFixture.카드;
import static fixture.DealerFixture.딜러;
import static fixture.PlayersFixture.플레이어;
import static fixture.PlayersFixture.플레이어들;
import static org.assertj.core.api.Assertions.assertThat;

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

        blackjackGame.startGame();

        assertThat(dealer).extracting("cardHand").extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .hasSize(2);
    }

    @Test
    void 블랙잭_게임_패인_결과를_알_수_있다() {
        Dealer dealer = 딜러(카드(Denomination.THREE), 카드(Denomination.TEN), 카드(Denomination.SIX), 카드(Denomination.QUEEN));
        Players players = 플레이어들("뽀로로");
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players);

        blackjackGame.startGame();

        GameResult result = blackjackGame.createGameResult();
        Map<Player, ResultStatus> playerResult = result.getPlayerResult();
        assertThat(playerResult).containsEntry(플레이어("뽀로로"), ResultStatus.LOSE);
    }
}
