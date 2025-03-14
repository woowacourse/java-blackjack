package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.GameManager;
import blackjack.domain.GameRound;
import blackjack.domain.card.CardNumber;
import blackjack.domain.gamer.Player;
import blackjack.fixture.DeckFixture;
import blackjack.fixture.GameManagerFixture;

class BettingTest {

    @Test
    @DisplayName("플레이어는 게임을 시작할 때 배팅 금액을 정한다.")
    void playerStartBettingTest() {
        Player player = new Player("pobi");
        GameRound round = new GameRound();
        assertThatCode(() -> round.betting(player, 1000))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드를 추가로 뽑아 21을 초과할 경우 베팅 금액을 모두 잃는다.")
    void additionalCardMoreThan21Test() {
        int startMoney = 1000;

        Player player = new Player("pobi");
        GameRound round = new GameRound();
        round.betting(player, startMoney);
        GameManager gameManager = GameManagerFixture.GameManagerWith(
            DeckFixture.deckOf(CardNumber.JACK, CardNumber.QUEEN, CardNumber.KING)
        );
        gameManager.drawStartingCards(player);
        gameManager.drawCard(player);

        assertThat(round.loseIfBust(player)).isTrue();
        assertThat(round.getFinalBettingMoney(player)).isZero();
    }
}
