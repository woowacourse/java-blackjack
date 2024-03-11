package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardShape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class JudgeTest {
    @Nested
    @DisplayName("딜러의 점수가 21점 이하이고, ")
    class DealerScoreLessThanOrEqual21 {
        private final Dealer dealer = new Dealer();

        public DealerScoreLessThanOrEqual21() {
            dealer.hit(new Card(CardRank.KING, CardShape.DIAMOND));
            dealer.hit(new Card(CardRank.SEVEN, CardShape.DIAMOND));
        }

        @Nested
        @DisplayName("플레이어의 점수가 21점 이하이고, ")
        class PlayerScoreLessThanOrEqual21 {

            @DisplayName("플레이어의 점수가 딜러의 점수보다 높으면 플레이어가 이긴다.")
            @Test
            void playerWin() {
                Player player = new Player("pobi");
                player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
                player.hit(new Card(CardRank.EIGHT, CardShape.DIAMOND));

                PlayerGameResult playerGameResult = dealer.judge(player);

                assertThat(playerGameResult).isEqualTo(PlayerGameResult.WIN);
            }

            @DisplayName("플레이어의 점수가 딜러의 점수보다 낮으면 플레이어가 진다.")
            @Test
            void playerLose() {
                Player player = new Player("pobi");
                player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
                player.hit(new Card(CardRank.SIX, CardShape.DIAMOND));

                PlayerGameResult playerGameResult = dealer.judge(player);

                assertThat(playerGameResult).isEqualTo(PlayerGameResult.LOSE);
            }

            @DisplayName("플레이어의 점수가 딜러의 점수와 같으면 무승부이다.")
            @Test
            void playerPush() {
                Player player = new Player("pobi");
                player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
                player.hit(new Card(CardRank.SEVEN, CardShape.DIAMOND));

                PlayerGameResult playerGameResult = dealer.judge(player);

                assertThat(playerGameResult).isEqualTo(PlayerGameResult.PUSH);
            }
        }

        @DisplayName("플레이어의 점수가 블랙잭이면 플레이어가 블랙잭 승이다.")
        @Test
        void playerBlackjack() {
            Player player = new Player("pobi");
            player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
            player.hit(new Card(CardRank.ACE, CardShape.DIAMOND));

            PlayerGameResult playerGameResult = dealer.judge(player);

            assertThat(playerGameResult).isEqualTo(PlayerGameResult.BLACKJACK_WIN);
        }

        @DisplayName("플레이어의 점수가 버스트이면 플레이어가 진다.")
        @Test
        void playerBust() {
            Player player = new Player("pobi");
            player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
            player.hit(new Card(CardRank.SIX, CardShape.DIAMOND));
            player.hit(new Card(CardRank.QUEEN, CardShape.DIAMOND));

            PlayerGameResult playerGameResult = dealer.judge(player);

            assertThat(playerGameResult).isEqualTo(PlayerGameResult.LOSE);
        }

    }

    @Nested
    @DisplayName("딜러의 점수가 블래잭이고, ")
    class DealerBlackjack {
        private final Dealer dealer = new Dealer();

        public DealerBlackjack() {
            dealer.hit(new Card(CardRank.KING, CardShape.DIAMOND));
            dealer.hit(new Card(CardRank.ACE, CardShape.DIAMOND));
        }

        @DisplayName("플레이어의 카드가 21점 이하이면 플레이어가 진다.")
        @Test
        void playerScoreLessThanOrEqual21() {
            Player player = new Player("pobi");
            player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
            player.hit(new Card(CardRank.SEVEN, CardShape.DIAMOND));

            PlayerGameResult playerGameResult = dealer.judge(player);

            assertThat(playerGameResult).isEqualTo(PlayerGameResult.LOSE);
        }

        @DisplayName("플레이어의 점수가 블랙잭이면 비긴다.")
        @Test
        void playerBlackjack() {
            Player player = new Player("pobi");
            player.hit(new Card(CardRank.ACE, CardShape.DIAMOND));
            player.hit(new Card(CardRank.KING, CardShape.DIAMOND));

            PlayerGameResult playerGameResult = dealer.judge(player);

            assertThat(playerGameResult).isEqualTo(PlayerGameResult.PUSH);
        }

        @DisplayName("플레이어의 점수가 버스트이면 플레이어가 진다.")
        @Test
        void playerBust() {
            Player player = new Player("pobi");
            player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
            player.hit(new Card(CardRank.SEVEN, CardShape.DIAMOND));
            player.hit(new Card(CardRank.QUEEN, CardShape.DIAMOND));

            PlayerGameResult playerGameResult = dealer.judge(player);

            assertThat(playerGameResult).isEqualTo(PlayerGameResult.LOSE);
        }
    }

    @Nested
    @DisplayName("딜러의 점수가 버스트이고, ")
    class DealerBust {
        private final Dealer dealer = new Dealer();

        public DealerBust() {
            dealer.hit(new Card(CardRank.KING, CardShape.DIAMOND));
            dealer.hit(new Card(CardRank.SIX, CardShape.DIAMOND));
            dealer.hit(new Card(CardRank.QUEEN, CardShape.DIAMOND));
        }

        @DisplayName("플레이어의 카드가 21점 이하이면 플레이어가 이긴다.")
        @Test
        void playerWin() {
            Player player = new Player("pobi");
            player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
            player.hit(new Card(CardRank.SEVEN, CardShape.DIAMOND));

            PlayerGameResult playerGameResult = dealer.judge(player);

            assertThat(playerGameResult).isEqualTo(PlayerGameResult.WIN);
        }

        @DisplayName("플레이어의 점수가 블랙잭이면, 플레이어가 블랙잭 승이다.")
        @Test
        void playerBlackjack() {
            Player player = new Player("pobi");
            player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
            player.hit(new Card(CardRank.ACE, CardShape.DIAMOND));

            PlayerGameResult playerGameResult = dealer.judge(player);

            assertThat(playerGameResult).isEqualTo(PlayerGameResult.BLACKJACK_WIN);
        }

        @DisplayName("플레이어의 점수가 버스트이면, 플레이어가 진다.")
        @Test
        void playerBust() {
            Player player = new Player("pobi");
            player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
            player.hit(new Card(CardRank.SEVEN, CardShape.DIAMOND));
            player.hit(new Card(CardRank.QUEEN, CardShape.DIAMOND));

            PlayerGameResult playerGameResult = dealer.judge(player);

            assertThat(playerGameResult).isEqualTo(PlayerGameResult.LOSE);
        }
    }

    @DisplayName("딜러의 점수가 블랙잭이 아닌 21점이고, 플레이어의 점수가 블랙잭이면, 플레이어가 블랙잭 승리다.")
    @Test
    void dealerNotBlackjack21ScoreAndPlayerBlackjack() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        dealer.hit(new Card(CardRank.TWO, CardShape.DIAMOND));
        dealer.hit(new Card(CardRank.NINE, CardShape.DIAMOND));

        Player player = new Player("pobi");
        player.hit(new Card(CardRank.ACE, CardShape.DIAMOND));
        player.hit(new Card(CardRank.KING, CardShape.DIAMOND));

        PlayerGameResult playerGameResult = dealer.judge(player);

        assertThat(playerGameResult).isEqualTo(PlayerGameResult.BLACKJACK_WIN);
    }

    @DisplayName("딜러의 점수가 블랙잭이고, 플레이어의 점수가 블랙잭이 아닌 21점이면, 플레이어가 진다.")
    @Test
    void dealerBlackjackAndPlayerNotBlackjack21Score() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        dealer.hit(new Card(CardRank.ACE, CardShape.DIAMOND));

        Player player = new Player("pobi");
        player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        player.hit(new Card(CardRank.TWO, CardShape.DIAMOND));
        player.hit(new Card(CardRank.NINE, CardShape.DIAMOND));

        PlayerGameResult playerGameResult = dealer.judge(player);

        assertThat(playerGameResult).isEqualTo(PlayerGameResult.LOSE);
    }
}
