package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static blackjack.domain.card.Denomination.*;
import static blackjack.fixture.CardFixture.카드;
import static blackjack.fixture.DealerFixture.딜러;
import static blackjack.fixture.PlayerFixture.플레이어;
import static blackjack.fixture.PlayerFixture.플레이어들;
import static org.assertj.core.api.Assertions.assertThat;

class BlackjackGameTest {

    @Test
    void 블랙잭_게임을_시작하면_참가자들은_카드를_2장씩_받는다() {
        final Dealer dealer = 딜러();
        final Players players = 플레이어들("pobi", "jason");
        final BlackjackGame blackjackGame = new BlackjackGame(dealer, players);

        blackjackGame.start();

        assertThat(dealer.getCardHand())
                .extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .hasSize(2);
    }

    @Nested
    @DisplayName("플레이어가 진 경우를 알 수 있다.")
    class PlayerLoseBlackjackGame {

        @Test
        void 딜러가_플레이어보다_카드의_합이_더_크면_플레이어가_진다() {
            final Dealer dealer = 딜러();
            final Players players = 플레이어들("pobi");
            final BlackjackGame blackjackGame = new BlackjackGame(dealer, players);

            dealer.receiveCard(카드(TEN));
            players.distributeCardToPlayer(0, 카드(SIX));

            final Map<Player, ResultStatus> gameResult = blackjackGame.judgeGameResult();
            final ResultStatus resultStatus = gameResult.get(플레이어("pobi"));

            assertThat(resultStatus).isEqualTo(ResultStatus.LOSE);
        }

        @Test
        void 플레이어가_버스트이면_플레이어가_진다() {
            final Dealer dealer = 딜러();
            final Players players = 플레이어들("pobi");
            final BlackjackGame blackjackGame = new BlackjackGame(dealer, players);

            dealer.receiveCard(카드(TEN));
            players.distributeCardToPlayer(0, 카드(JACK));
            players.distributeCardToPlayer(0, 카드(QUEEN));
            players.distributeCardToPlayer(0, 카드(KING));

            final Map<Player, ResultStatus> gameResult = blackjackGame.judgeGameResult();
            final ResultStatus resultStatus = gameResult.get(플레이어("pobi"));

            assertThat(resultStatus).isEqualTo(ResultStatus.LOSE);
        }

        @Test
        void 플레이어와_딜러_둘_다_버스트라면_플레이어가_진다() {
            final Dealer dealer = 딜러();
            final Players players = 플레이어들("pobi");
            final BlackjackGame blackjackGame = new BlackjackGame(dealer, players);

            dealer.receiveCard(카드(TEN));
            dealer.receiveCard(카드(NINE));
            dealer.receiveCard(카드(EIGHT));
            players.distributeCardToPlayer(0, 카드(JACK));
            players.distributeCardToPlayer(0, 카드(QUEEN));
            players.distributeCardToPlayer(0, 카드(KING));

            final Map<Player, ResultStatus> gameResult = blackjackGame.judgeGameResult();
            final ResultStatus resultStatus = gameResult.get(플레이어("pobi"));

            assertThat(resultStatus).isEqualTo(ResultStatus.LOSE);
        }

        @Test
        void 딜러가_블랙잭이고_플레이어가_블랙잭이_아니라면_플레이어가_진다() {
            final Dealer dealer = 딜러();
            final Players players = 플레이어들("pobi");
            final BlackjackGame blackjackGame = new BlackjackGame(dealer, players);

            dealer.receiveCard(카드(TEN));
            dealer.receiveCard(카드(ACE));
            players.distributeCardToPlayer(0, 카드(JACK));
            players.distributeCardToPlayer(0, 카드(NINE));
            players.distributeCardToPlayer(0, 카드(TWO));

            final Map<Player, ResultStatus> gameResult = blackjackGame.judgeGameResult();
            final ResultStatus resultStatus = gameResult.get(플레이어("pobi"));

            assertThat(resultStatus).isEqualTo(ResultStatus.LOSE);
        }
    }
}
