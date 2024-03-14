package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static blackjack.domain.card.Denomination.*;
import static blackjack.fixture.CardFixture.카드;
import static blackjack.fixture.DealerFixture.딜러;
import static blackjack.fixture.PlayerFixture.플레이어들;
import static org.assertj.core.api.Assertions.assertThat;

class BlackjackGameTest {
    private Dealer dealer;
    private Players players;
    private Player player;
    private BlackjackGame blackjackGame;

    @BeforeEach
    void setUp() {
        dealer = 딜러();
        players = 플레이어들("pobi");
        player = players.findPlayerByIndex(0);
        blackjackGame = new BlackjackGame(dealer, players);
    }

    @Test
    void 블랙잭_게임을_시작하면_참가자들은_카드를_2장씩_받는다() {
        blackjackGame.start();

        assertThat(dealer.getCardHand())
                .extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .hasSize(2);
    }

    @Test
    void 플레이어에게_카드를_추가로_지급할_수_있다() {
        blackjackGame.distributeCardToPlayer(0);

        assertThat(player.getCardHand())
                .extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .hasSize(1);
    }

    @Test
    void 딜러에게_카드를_추가로_지급할_수_있다() {
        blackjackGame.distributeCardToDealer();

        assertThat(dealer.getCardHand())
                .extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .hasSize(1);
    }

    @Nested
    @DisplayName("플레이어가 진 경우를 알 수 있다.")
    class PlayerLoseBlackjackGame {

        @Test
        void 딜러가_플레이어보다_카드의_합이_더_크면_플레이어가_진다() {
            dealer.receiveCard(카드(TEN));
            player.receiveCard(카드(SIX));

            final Map<Player, ResultStatus> gameResult = blackjackGame.judgeGameResult();

            assertThat(gameResult.get(player)).isEqualTo(ResultStatus.LOSE);
        }

        @Test
        void 플레이어가_버스트이면_플레이어가_진다() {
            dealer.receiveCard(카드(TEN));
            player.receiveCard(카드(JACK));
            player.receiveCard(카드(QUEEN));
            player.receiveCard(카드(KING));

            final Map<Player, ResultStatus> gameResult = blackjackGame.judgeGameResult();

            assertThat(gameResult.get(player)).isEqualTo(ResultStatus.LOSE);
        }

        @Test
        void 플레이어와_딜러_둘_다_버스트라면_플레이어가_진다() {
            dealer.receiveCard(카드(TEN));
            dealer.receiveCard(카드(NINE));
            dealer.receiveCard(카드(EIGHT));
            player.receiveCard(카드(JACK));
            player.receiveCard(카드(QUEEN));
            player.receiveCard(카드(KING));

            final Map<Player, ResultStatus> gameResult = blackjackGame.judgeGameResult();

            assertThat(gameResult.get(player)).isEqualTo(ResultStatus.LOSE);
        }

        @Test
        void 딜러가_블랙잭이고_플레이어가_블랙잭이_아니라면_플레이어가_진다() {
            dealer.receiveCard(카드(TEN));
            dealer.receiveCard(카드(ACE));
            player.receiveCard(카드(JACK));
            player.receiveCard(카드(NINE));
            player.receiveCard(카드(TWO));

            final Map<Player, ResultStatus> gameResult = blackjackGame.judgeGameResult();

            assertThat(gameResult.get(player)).isEqualTo(ResultStatus.LOSE);
        }
    }

    @Nested
    @DisplayName("플레이어가 이긴 경우를 알 수 있다.")
    class PlayerWinBlackjackGame {

        @Test
        void 플레이어가_딜러보다_카드의_합이_더_크면_플레이어가_이긴다() {
            dealer.receiveCard(카드(SIX));
            player.receiveCard(카드(TEN));

            final Map<Player, ResultStatus> gameResult = blackjackGame.judgeGameResult();

            assertThat(gameResult.get(player)).isEqualTo(ResultStatus.WIN);
        }

        @Test
        void 딜러가_버스트이면_플레이어가_이긴다() {
            dealer.receiveCard(카드(JACK));
            dealer.receiveCard(카드(QUEEN));
            dealer.receiveCard(카드(KING));
            player.receiveCard(카드(TEN));

            final Map<Player, ResultStatus> gameResult = blackjackGame.judgeGameResult();

            assertThat(gameResult.get(player)).isEqualTo(ResultStatus.WIN);
        }

        @Test
        void 플레이어가_블랙잭이고_딜러가_블랙잭이_아니라면_플레이어가_이긴다() {
            dealer.receiveCard(카드(JACK));
            dealer.receiveCard(카드(NINE));
            dealer.receiveCard(카드(TWO));
            player.receiveCard(카드(TEN));
            player.receiveCard(카드(ACE));

            final Map<Player, ResultStatus> gameResult = blackjackGame.judgeGameResult();

            assertThat(gameResult.get(player)).isEqualTo(ResultStatus.WIN);
        }
    }

    @Nested
    @DisplayName("플레이어와 딜러가 무승부인 경우를 알 수 있다.")
    class PlayerDrawBlackjackGame {

        @Test
        void 플레이어와_딜러_카드의_합이_같으면_무승부이다() {
            dealer.receiveCard(카드(TEN));
            player.receiveCard(카드(TEN));

            final Map<Player, ResultStatus> gameResult = blackjackGame.judgeGameResult();

            assertThat(gameResult.get(player)).isEqualTo(ResultStatus.DRAW);
        }

        @Test
        void 플레이어가_블랙잭이고_딜러가_블랙잭이면_무승부이다() {
            dealer.receiveCard(카드(QUEEN));
            dealer.receiveCard(카드(ACE));
            player.receiveCard(카드(TEN));
            player.receiveCard(카드(ACE));

            final Map<Player, ResultStatus> gameResult = blackjackGame.judgeGameResult();

            assertThat(gameResult.get(player)).isEqualTo(ResultStatus.DRAW);
        }
    }
}
