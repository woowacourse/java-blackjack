package domain;

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
    void 게임을_시작하면_딜러와_플레이어들에게_카드를_나눠준다() { // todo 플레이어의 카드 개수도 검증
        Dealer dealer = 딜러();
        Players players = 플레이어들("뽀로로", "프린", "포비");
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players);

        blackjackGame.startGame();

        assertThat(dealer).extracting("cardHand").extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .hasSize(2);
    }

    @Test
    void 블랙잭_게임의_패인_결과를_알_수_있다() {
        Dealer dealer = 딜러(카드(Denomination.THREE), 카드(Denomination.TEN), 카드(Denomination.SIX), 카드(Denomination.QUEEN));
        Players players = 플레이어들("뽀로로");
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players);

        blackjackGame.startGame();

        GameResult result = blackjackGame.createGameResult();
        Map<Player, ResultStatus> playerResult = result.getPlayerResult();
        assertThat(playerResult).containsEntry(플레이어("뽀로로"), ResultStatus.LOSE);
    }
}
