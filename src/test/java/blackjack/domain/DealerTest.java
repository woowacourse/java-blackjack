package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러의 이름은 '딜러'이다.")
    @Test
    void create() {
        Dealer dealer = new Dealer();

        String result = dealer.getName();

        assertThat(result).isEqualTo("딜러");
    }

    @DisplayName("딜러는 카드를 한 장 받을 수 있다.")
    @Test
    void hitOneCard() {
        Dealer dealer = new Dealer();
        Card card = new Card(CardRank.EIGHT, CardShape.DIAMOND);

        dealer.hit(card);

        assertThat(dealer.getCards()).containsExactly(card);
    }

    @DisplayName("딜러는 17점 이상이 되면, 더 이상 카드를 받을 수 없다.")
    @Test
    void hitWhenIsNotPlayable() {
        Dealer dealer = new Dealer();
        Card card1 = new Card(CardRank.KING, CardShape.DIAMOND);
        Card card2 = new Card(CardRank.SEVEN, CardShape.DIAMOND);
        Card card3 = new Card(CardRank.EIGHT, CardShape.DIAMOND);
        dealer.hit(card1);
        dealer.hit(card2);

        assertThatThrownBy(() -> dealer.hit(card3))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("딜러가 카드를 더 받을 수 있는지 확인할 수 있다.")
    @Test
    void isPlayable() {
        Dealer dealer = new Dealer();
        Card card1 = new Card(CardRank.KING, CardShape.DIAMOND);
        Card card2 = new Card(CardRank.SEVEN, CardShape.DIAMOND);
        dealer.hit(card1);
        dealer.hit(card2);

        boolean result = dealer.isPlayable();

        assertThat(result).isFalse();
    }

    @DisplayName("딜러의 카드 중 첫 번째 카드는 가려져 있다.")
    @Test
    void getVisibleCards() {
        Dealer dealer = new Dealer();
        Card card1 = new Card(CardRank.KING, CardShape.DIAMOND);
        Card card2 = new Card(CardRank.SEVEN, CardShape.DIAMOND);
        dealer.hit(card1);
        dealer.hit(card2);

        assertThat(dealer.getVisibleCards()).containsExactly(card2);
    }

    @Nested
    @DisplayName("딜러의 점수가 21점 이하이고, ")
    class DealerScoreLessThan21 {
        private final Dealer dealer = new Dealer();

        public DealerScoreLessThan21() {
            dealer.hit(new Card(CardRank.KING, CardShape.DIAMOND));
            dealer.hit(new Card(CardRank.SEVEN, CardShape.DIAMOND));
        }

        @Nested
        @DisplayName("플레이어의 점수가 21점 이하이고, ")
        class PlayerScoreLessThan21 {
            @DisplayName("플레이어의 점수가 딜러의 점수보다 높으면 플레이어가 이긴다.")
            @Test
            void playerWin() {
                Player player = new Player("pobi");
                player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
                player.hit(new Card(CardRank.EIGHT, CardShape.DIAMOND));

                GameResult gameResult = dealer.judge(player);

                assertThat(gameResult).isEqualTo(GameResult.WIN);
            }

            @DisplayName("플레이어의 점수가 딜러의 점수보다 낮으면 플레이어가 진다.")
            @Test
            void playerLose() {
                Player player = new Player("pobi");
                player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
                player.hit(new Card(CardRank.SIX, CardShape.DIAMOND));

                GameResult gameResult = dealer.judge(player);

                assertThat(gameResult).isEqualTo(GameResult.LOSE);
            }

            @DisplayName("플레이어의 점수가 딜러의 점수와 같으면 무승부이다.")
            @Test
            void playerPush() {
                Player player = new Player("pobi");
                player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
                player.hit(new Card(CardRank.SEVEN, CardShape.DIAMOND));

                GameResult gameResult = dealer.judge(player);

                assertThat(gameResult).isEqualTo(GameResult.PUSH);
            }
        }

        @DisplayName("플레이어의 점수가 블랙잭이면 플레이어가 이긴다.")
        @Test
        void playerBlackjack() {
            Player player = new Player("pobi");
            player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
            player.hit(new Card(CardRank.ACE, CardShape.DIAMOND));

            GameResult gameResult = dealer.judge(player);

            assertThat(gameResult).isEqualTo(GameResult.WIN);
        }

        @DisplayName("플레이어의 점수가 버스트이면 플레이어가 진다.")
        @Test
        void playerBust() {
            Player player = new Player("pobi");
            player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
            player.hit(new Card(CardRank.SIX, CardShape.DIAMOND));
            player.hit(new Card(CardRank.QUEEN, CardShape.DIAMOND));

            GameResult gameResult = dealer.judge(player);

            assertThat(gameResult).isEqualTo(GameResult.LOSE);
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
        void playerScoreLessThan21() {
            Player player = new Player("pobi");
            player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
            player.hit(new Card(CardRank.SEVEN, CardShape.DIAMOND));

            GameResult gameResult = dealer.judge(player);

            assertThat(gameResult).isEqualTo(GameResult.LOSE);
        }

        @DisplayName("플레이어의 점수가 블랙잭이면 비긴다.")
        @Test
        void playerBlackjack() {
            Player player = new Player("pobi");
            player.hit(new Card(CardRank.ACE, CardShape.DIAMOND));
            player.hit(new Card(CardRank.KING, CardShape.DIAMOND));

            GameResult gameResult = dealer.judge(player);

            assertThat(gameResult).isEqualTo(GameResult.PUSH);
        }

        @DisplayName("플레이어의 점수가 버스트이면 플레이어가 진다.")
        @Test
        void playerBust() {
            Player player = new Player("pobi");
            player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
            player.hit(new Card(CardRank.SEVEN, CardShape.DIAMOND));
            player.hit(new Card(CardRank.QUEEN, CardShape.DIAMOND));

            GameResult gameResult = dealer.judge(player);

            assertThat(gameResult).isEqualTo(GameResult.LOSE);
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

            GameResult gameResult = dealer.judge(player);

            assertThat(gameResult).isEqualTo(GameResult.WIN);
        }

        @DisplayName("플레이어의 점수가 블랙잭이면, 플레이어가 이긴다.")
        @Test
        void playerBlackjack() {
            Player player = new Player("pobi");
            player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
            player.hit(new Card(CardRank.ACE, CardShape.DIAMOND));

            GameResult gameResult = dealer.judge(player);

            assertThat(gameResult).isEqualTo(GameResult.WIN);
        }

        @DisplayName("플레이어의 점수가 버스트이면, 플레이어가 진다.")
        @Test
        void playerBust() {
            Player player = new Player("pobi");
            player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
            player.hit(new Card(CardRank.SEVEN, CardShape.DIAMOND));
            player.hit(new Card(CardRank.QUEEN, CardShape.DIAMOND));

            GameResult gameResult = dealer.judge(player);

            assertThat(gameResult).isEqualTo(GameResult.LOSE);
        }
    }

    @DisplayName("딜러의 점수가 블랙잭이 아닌 21점이고, 플레이어의 점수가 블랙잭이면, 플레이어가 이긴다.")
    @Test
    void dealerNotBlackjack21ScoreAndPlayerBlackjack() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        dealer.hit(new Card(CardRank.TWO, CardShape.DIAMOND));
        dealer.hit(new Card(CardRank.NINE, CardShape.DIAMOND));

        Player player = new Player("pobi");
        player.hit(new Card(CardRank.ACE, CardShape.DIAMOND));
        player.hit(new Card(CardRank.KING, CardShape.DIAMOND));

        GameResult gameResult = dealer.judge(player);

        assertThat(gameResult).isEqualTo(GameResult.WIN);
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

        GameResult gameResult = dealer.judge(player);

        assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }
}
