import static org.assertj.core.api.Assertions.assertThat;

import domain.Card;
import domain.Dealer;
import domain.GameRule;
import domain.Participant;
import domain.Player;
import domain.constants.CardValue;
import domain.constants.Shape;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GameRuleTest {
    @DisplayName("참가자들의 승패 여부를 판단한다.")
    @Test
    void judgeWinners() {
        // given
        Dealer dealer = new Dealer("딜러");
        dealer.saveCards(createNormalWithTwoCards());

        Player player = new Player("pobi");
        player.saveCards(createNormalWithThreeCards());

        GameRule rule = createGameRule(player, dealer);

        // when
        List<Boolean> isWinner = rule.compareScore();

        // then
        assertThat(isWinner).containsExactly(true);
    }

    @DisplayName("딜러의 점수가 21을 초과한 경우")
    @Nested
    class dealerBusted {
        @DisplayName("모든 참가자는 21을 초과해도 승리한다.")
        @Test
        void playerBusted() {
            Dealer dealer = new Dealer("딜러");
            dealer.saveCards(createBustedCards());

            Player player = new Player("pobi");
            player.saveCards(createBustedCards());

            GameRule rule = createGameRule(player, dealer);

            List<Boolean> gameResult = rule.judgePlayersIfDealerBusted();

            Assertions.assertThat(gameResult).hasSize(1);
            Assertions.assertThat(gameResult).containsExactly(false);
        }

        @DisplayName("참가자의 점수가 21 미만인 경우 승리한다.")
        @Test
        void playerDoesNotBusted() {
            Dealer dealer = new Dealer("딜러");
            dealer.saveCards(createBustedCards());

            Player player = new Player("pobi");
            player.saveCards(createNormalWithTwoCards());

            GameRule rule = createGameRule(player, dealer);

            List<Boolean> gameResult = rule.judgePlayersIfDealerBusted();

            Assertions.assertThat(gameResult).hasSize(1);
            Assertions.assertThat(gameResult).containsExactly(true);
        }
    }

    @DisplayName("딜러가 블랙잭인 경우")
    @Nested
    class dealerBlackJack {
        @DisplayName("참가자가 블랙잭인 경우 카드 개수가 적은 사람이 승리한다.")
        @Test
        void playerBlackJack() {
            Dealer dealer = new Dealer("딜러");
            dealer.saveCards(createBlackJackWithTwoCards());

            Player player = new Player("pobi");
            player.saveCards(createBlackJackWithThreeCards());

            GameRule rule = createGameRule(player, dealer);

            List<Boolean> gameResult = rule.judge();

            Assertions.assertThat(gameResult).hasSize(1);
            Assertions.assertThat(gameResult).containsExactly(false);
        }

        @DisplayName("참가자가 블랙잭이 아닌 경우 딜러가 승리한다.")
        @Test
        void playerIsNotBlackJack() {
            Dealer dealer = new Dealer("딜러");
            dealer.saveCards(createBlackJackWithTwoCards());

            Player player = new Player("pobi");
            player.saveCards(createNormalWithTwoCards());

            GameRule rule = createGameRule(player, dealer);

            List<Boolean> gameResult = rule.judge();

            Assertions.assertThat(gameResult).hasSize(1);
            Assertions.assertThat(gameResult).containsExactly(false);
        }
    }

    @DisplayName("딜러의 점수가 21 미만인 경우")
    @Nested
    class DealerIsNotBlackJack {
        @DisplayName("참가자의 점수가 21인 경우 참가자가 승리한다.")
        @Test
        void playerIsBlackJack() {
            Dealer dealer = new Dealer("딜러");
            dealer.saveCards(createNormalWithTwoCards());

            Player player = new Player("pobi");
            player.saveCards(createBlackJackWithTwoCards());

            GameRule rule = createGameRule(player, dealer);

            List<Boolean> gameResult = rule.judge();

            Assertions.assertThat(gameResult).hasSize(1);
            Assertions.assertThat(gameResult).containsExactly(true);
        }

        @DisplayName("참가자의 점수가 21 미만인 경우 점수가 큰 사람이 승리한다.")
        @Test
        void playerIsNotBlackJack() {
            Dealer dealer = new Dealer("딜러");
            dealer.saveCards(createNormalWithTwoCards());

            Player player = new Player("pobi");
            player.saveCards(createNormalWithThreeCards());

            GameRule rule = createGameRule(player, dealer);

            List<Boolean> gameResult = rule.judge();

            Assertions.assertThat(gameResult).hasSize(1);
            Assertions.assertThat(gameResult).containsExactly(true);
        }

        @DisplayName("참가자의 점수가 21 미만이고 딜러와 점수가 같은 경우 카드 개수가 적은 사람이 승리한다.")
        @Test
        void playerScoreEqualsToDealer() {
            Dealer dealer = new Dealer("딜러");
            dealer.saveCards(createNormalWithThreeCards());

            Player player = new Player("pobi");
            player.saveCards(createSameScoreNormalWithTwoCards());

            GameRule rule = createGameRule(player, dealer);

            List<Boolean> gameResult = rule.judge();

            Assertions.assertThat(gameResult).hasSize(1);
            Assertions.assertThat(gameResult).containsExactly(true);
        }
    }

    private GameRule createGameRule(final Player player, final Dealer dealer) {
        List<Player> players = List.of(player);
        Participant participant = new Participant(dealer, players);

        return new GameRule(participant);
    }

    private List<Card> createBlackJackWithTwoCards() {
        return List.of(
                new Card(CardValue.TEN, Shape.CLOVER),
                new Card(CardValue.ACE, Shape.DIAMOND));
    }

    private List<Card> createBlackJackWithThreeCards() {
        return List.of(
                new Card(CardValue.FIVE, Shape.CLOVER),
                new Card(CardValue.FIVE, Shape.DIAMOND),
                new Card(CardValue.ACE, Shape.DIAMOND));
    }

    private List<Card> createNormalWithTwoCards() {
        return List.of(
                new Card(CardValue.TEN, Shape.CLOVER),
                new Card(CardValue.SIX, Shape.DIAMOND));
    }

    private List<Card> createNormalWithThreeCards() {
        return List.of(
                new Card(CardValue.TEN, Shape.CLOVER),
                new Card(CardValue.THREE, Shape.DIAMOND),
                new Card(CardValue.SIX, Shape.HEART));
    }

    private List<Card> createSameScoreNormalWithTwoCards() {
        return List.of(
                new Card(CardValue.TEN, Shape.CLOVER),
                new Card(CardValue.NINE, Shape.DIAMOND));
    }

    private List<Card> createBustedCards() {
        return List.of(
                new Card(CardValue.TEN, Shape.CLOVER),
                new Card(CardValue.FIVE, Shape.DIAMOND),
                new Card(CardValue.EIGHT, Shape.HEART));
    }
}
