package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.GameManager;
import blackjack.domain.GameRound;
import blackjack.domain.card.CardNumber;
import blackjack.domain.gamer.Dealer;
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
        int initialMoney = 1000;

        Player player = new Player("pobi");
        GameRound round = new GameRound();
        round.betting(player, initialMoney);
        GameManager gameManager = GameManagerFixture.GameManagerWith(
            DeckFixture.deckOf(CardNumber.JACK, CardNumber.QUEEN, CardNumber.KING)
        );
        gameManager.drawStartingCards(player);
        gameManager.drawCard(player);

        assertThat(round.loseIfBust(player)).isTrue();
        assertThat(round.getFinalBettingMoney(player)).isZero();
    }

    @Test
    @DisplayName("처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅금액의 1.5배를 받는다.")
    void blackjackBettingTest() {
        int initialMoney = 10000;

        Player player = new Player("pobi");
        GameRound round = new GameRound();
        round.betting(player, initialMoney);
        GameManager gameManager = GameManagerFixture.GameManagerWith(
            DeckFixture.deckOf(CardNumber.JACK, CardNumber.ACE)
        );
        gameManager.drawStartingCards(player);
        assertThat(round.endGameIfBlackjack(player)).isTrue();
        assertThat(round.getFinalBettingMoney(player)).isEqualTo((int)(initialMoney * 1.5));
    }

    @Test
    @DisplayName("딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.")
    void bothBalckjackRollbackTest() {
        int initialMoney = 10000;

        Player player = new Player("pobi");
        Dealer dealer = new Dealer();
        GameRound round = new GameRound();
        round.betting(player, initialMoney);
        GameManager gameManager = GameManagerFixture.GameManagerWith(
            DeckFixture.deckOf(CardNumber.JACK, CardNumber.ACE, CardNumber.JACK, CardNumber.ACE)
        );
        gameManager.drawStartingCards(player);
        gameManager.drawStartingCards(dealer);
        assertThat(round.endGameIfBlackjack(dealer)).isTrue();
        assertThat(round.getFinalBettingMoney(player)).isEqualTo(initialMoney);
    }
}
