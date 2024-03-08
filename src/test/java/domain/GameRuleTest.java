package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.constants.Outcome;
import domain.constants.Score;
import domain.constants.Shape;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GameRuleTest {
    @DisplayName("참가자들의 승패 여부를 판단한다.")
    @Test
    void judgeWinners() {
        // given
        Dealer dealer = new Dealer();
        dealer.drawCards(createNormalWithTwoCards());

        Player player = new Player("pobi");
        player.drawCards(createNormalWithThreeCards());

        GameRule rule = createGameRule(player, dealer);

        // when
        List<Outcome> isWinner = rule.judge();

        // then
        assertThat(isWinner).containsExactly(Outcome.WIN);
    }

    @DisplayName("딜러의 점수가 21을 초과한 경우")
    @Nested
    class dealerBusted {
        @DisplayName("모든 참가자는 21을 초과해도 승리한다.")
        @Test
        void playerBusted() {
            Dealer dealer = new Dealer();
            dealer.drawCards(createBustedCards());

            Player player = new Player("pobi");
            player.drawCards(createBustedCards());

            GameRule rule = createGameRule(player, dealer);

            List<Outcome> gameResult = rule.judgePlayersWhenDealerBusted();

            assertThat(gameResult)
                    .hasSize(1)
                    .containsExactly(Outcome.LOSE);
        }

        @DisplayName("참가자의 점수가 21 미만인 경우 승리한다.")
        @Test
        void playerDoesNotBusted() {
            Dealer dealer = new Dealer();
            dealer.drawCards(createBustedCards());

            Player player = new Player("pobi");
            player.drawCards(createNormalWithTwoCards());

            GameRule rule = createGameRule(player, dealer);

            List<Outcome> gameResult = rule.judgePlayersWhenDealerBusted();

            assertThat(gameResult)
                    .hasSize(1)
                    .containsExactly(Outcome.WIN);
        }
    }

    @DisplayName("딜러가 블랙잭인 경우")
    @Nested
    class dealerBlackJack {
        @DisplayName("참가자가 블랙잭인 경우 카드 개수가 적은 사람이 승리한다.")
        @Test
        void playerBlackJack() {
            Dealer dealer = new Dealer();
            dealer.drawCards(createBlackJackWithTwoCards());

            Player player = new Player("pobi");
            player.drawCards(createBlackJackWithThreeCards());

            GameRule rule = createGameRule(player, dealer);

            List<Outcome> gameResult = rule.judge();

            assertThat(gameResult)
                    .hasSize(1)
                    .containsExactly(Outcome.LOSE);
        }

        @DisplayName("참가자가 블랙잭이 아닌 경우 딜러가 승리한다.")
        @Test
        void playerIsNotBlackJack() {
            Dealer dealer = new Dealer();
            dealer.drawCards(createBlackJackWithTwoCards());

            Player player = new Player("pobi");
            player.drawCards(createNormalWithTwoCards());

            GameRule rule = createGameRule(player, dealer);

            List<Outcome> gameResult = rule.judge();

            assertThat(gameResult)
                    .hasSize(1)
                    .containsExactly(Outcome.LOSE);
        }
    }

    @DisplayName("딜러의 점수가 21 미만인 경우")
    @Nested
    class DealerIsNotBlackJack {
        @DisplayName("참가자의 점수가 21인 경우 참가자가 승리한다.")
        @Test
        void playerIsBlackJack() {
            Dealer dealer = new Dealer();
            dealer.drawCards(createNormalWithTwoCards());

            Player player = new Player("pobi");
            player.drawCards(createBlackJackWithTwoCards());

            GameRule rule = createGameRule(player, dealer);

            List<Outcome> gameResult = rule.judge();

            assertThat(gameResult)
                    .hasSize(1)
                    .containsExactly(Outcome.WIN);
        }

        @DisplayName("참가자의 점수가 21 미만인 경우 점수가 큰 사람이 승리한다.")
        @Test
        void playerIsNotBlackJack() {
            Dealer dealer = new Dealer();
            dealer.drawCards(createNormalWithTwoCards());

            Player player = new Player("pobi");
            player.drawCards(createNormalWithThreeCards());

            GameRule rule = createGameRule(player, dealer);

            List<Outcome> gameResult = rule.judge();

            assertThat(gameResult)
                    .hasSize(1)
                    .containsExactly(Outcome.WIN);
        }

        @DisplayName("참가자의 점수가 21 미만이고 딜러와 점수가 같은 경우 카드 개수가 적은 사람이 승리한다.")
        @Test
        void playerScoreEqualsToDealer() {
            Dealer dealer = new Dealer();
            dealer.drawCards(createNormalWithThreeCards());

            Player player = new Player("pobi");
            player.drawCards(createSameScoreNormalWithTwoCards());

            GameRule rule = createGameRule(player, dealer);

            List<Outcome> gameResult = rule.judge();

            assertThat(gameResult)
                    .hasSize(1)
                    .containsExactly(Outcome.WIN);
        }
    }

    private GameRule createGameRule(final Player player, final Dealer dealer) {
        List<Player> players = List.of(player);
        Participant participant = new Participant(dealer, players);

        return new GameRule(participant);
    }

    private List<Card> createBlackJackWithTwoCards() {
        return List.of(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.ACE, Shape.DIAMOND));
    }

    private List<Card> createBlackJackWithThreeCards() {
        return List.of(
                new Card(Score.FIVE, Shape.CLOVER),
                new Card(Score.FIVE, Shape.DIAMOND),
                new Card(Score.ACE, Shape.DIAMOND));
    }

    private List<Card> createNormalWithTwoCards() {
        return List.of(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.SIX, Shape.DIAMOND));
    }

    private List<Card> createNormalWithThreeCards() {
        return List.of(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.THREE, Shape.DIAMOND),
                new Card(Score.SIX, Shape.HEART));
    }

    private List<Card> createSameScoreNormalWithTwoCards() {
        return List.of(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.NINE, Shape.DIAMOND));
    }

    private List<Card> createBustedCards() {
        return List.of(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.FIVE, Shape.DIAMOND),
                new Card(Score.EIGHT, Shape.HEART));
    }
}
