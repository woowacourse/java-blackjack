import static org.assertj.core.api.Assertions.assertThat;

import domain.Card;
import domain.Dealer;
import domain.Deck;
import domain.GameRule;
import domain.Participant;
import domain.Player;
import domain.constants.Score;
import domain.constants.Shape;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GameRuleTest {
    @DisplayName("참가자들의 승패 여부를 판단한다.")
    @Test
    void judgeWinners() {
        // given
        Deck twoCards = createNormalWithTwoCards();
        Deck threeCards = createNormalWithThreeCards();

        Dealer dealer = new Dealer("딜러");
        for (int i = 0; i < 2; i++) {
            dealer.pickOneCard(twoCards);
        }

        Player player = new Player("pobi");
        for (int i = 0; i < 3; i++) {
            dealer.pickOneCard(threeCards);
        }

        GameRule rule = createGameRule(player, dealer);

        // when
        List<Boolean> isWinner = rule.judge();

        // then
        assertThat(isWinner).containsExactly(true);
    }

    @DisplayName("딜러의 점수가 21을 초과한 경우")
    @Nested
    class dealerBusted {
        @DisplayName("모든 참가자는 21을 초과해도 승리한다.")
        @Test
        void playerBusted() {
            Deck bustedCardsForDealer = createBustedCards();
            Dealer dealer = new Dealer("딜러");
            for (int i = 0; i < 3; i++) {
                dealer.pickOneCard(bustedCardsForDealer);
            }

            Deck bustedCardsForPlayer = createBustedCards();
            Player player = new Player("pobi");
            for (int i = 0; i < 3; i++) {
                player.pickOneCard(bustedCardsForPlayer);
            }

            GameRule rule = createGameRule(player, dealer);

            List<Boolean> gameResult = rule.judgePlayersIfDealerBusted();

            assertThat(gameResult)
                    .hasSize(1)
                    .containsExactly(false);
        }

        @DisplayName("참가자의 점수가 21 미만인 경우 승리한다.")
        @Test
        void playerDoesNotBusted() {
            Deck bustedCards = createBustedCards();
            Deck twoCards = createNormalWithTwoCards();

            Dealer dealer = new Dealer("딜러");
            for (int i = 0; i < 3; i++) {
                dealer.pickOneCard(bustedCards);
            }

            Player player = new Player("pobi");
            for (int i = 0; i < 2; i++) {
                player.pickOneCard(twoCards);
            }

            GameRule rule = createGameRule(player, dealer);

            List<Boolean> gameResult = rule.judgePlayersIfDealerBusted();

            assertThat(gameResult)
                    .hasSize(1)
                    .containsExactly(true);
        }
    }

    @DisplayName("딜러가 블랙잭인 경우")
    @Nested
    class dealerBlackJack {
        @DisplayName("참가자가 블랙잭인 경우 카드 개수가 적은 사람이 승리한다.")
        @Test
        void playerBlackJack() {
            Deck blackJackTwoCards = createBlackJackWithTwoCards();
            Deck blackJackThreeCards = createBlackJackWithThreeCards();

            Dealer dealer = new Dealer("딜러");
            for (int i = 0; i < 2; i++) {
                dealer.pickOneCard(blackJackTwoCards);
            }

            Player player = new Player("pobi");
            for (int i = 0; i < 3; i++) {
                player.pickOneCard(blackJackThreeCards);
            }

            GameRule rule = createGameRule(player, dealer);

            List<Boolean> gameResult = rule.judge();

            assertThat(gameResult)
                    .hasSize(1)
                    .containsExactly(false);
        }

        @DisplayName("참가자가 블랙잭이 아닌 경우 딜러가 승리한다.")
        @Test
        void playerIsNotBlackJack() {
            Deck blackJackTwoCards = createBlackJackWithTwoCards();
            Deck twoCards = createNormalWithTwoCards();

            Dealer dealer = new Dealer("딜러");
            for (int i = 0; i < 2; i++) {
                dealer.pickOneCard(blackJackTwoCards);
            }

            Player player = new Player("pobi");
            for (int i = 0; i < 2; i++) {
                player.pickOneCard(twoCards);
            }

            GameRule rule = createGameRule(player, dealer);

            List<Boolean> gameResult = rule.judge();

            assertThat(gameResult)
                    .hasSize(1)
                    .containsExactly(false);
        }
    }

    @DisplayName("딜러의 점수가 21 미만인 경우")
    @Nested
    class DealerIsNotBlackJack {
        @DisplayName("참가자의 점수가 21인 경우 참가자가 승리한다.")
        @Test
        void playerIsBlackJack() {
            Deck twoCards = createNormalWithTwoCards();
            Deck blackJackTwoCards = createBlackJackWithTwoCards();

            Dealer dealer = new Dealer("딜러");
            for (int i = 0; i < 2; i++) {
                dealer.pickOneCard(twoCards);
            }

            Player player = new Player("pobi");
            for (int i = 0; i < 2; i++) {
                player.pickOneCard(blackJackTwoCards);
            }

            GameRule rule = createGameRule(player, dealer);

            List<Boolean> gameResult = rule.judge();

            assertThat(gameResult)
                    .hasSize(1)
                    .containsExactly(true);
        }

        @DisplayName("참가자의 점수가 21 미만인 경우 점수가 큰 사람이 승리한다.")
        @Test
        void playerIsNotBlackJack() {
            Deck twoCards = createNormalWithTwoCards();
            Deck threeCards = createNormalWithThreeCards();

            Dealer dealer = new Dealer("딜러");
            for (int i = 0; i < 2; i++) {
                dealer.pickOneCard(twoCards);
            }

            Player player = new Player("pobi");
            for (int i = 0; i < 3; i++) {
                player.pickOneCard(threeCards);
            }

            GameRule rule = createGameRule(player, dealer);

            List<Boolean> gameResult = rule.judge();

            assertThat(gameResult)
                    .hasSize(1)
                    .containsExactly(true);
        }

        @DisplayName("참가자의 점수가 21 미만이고 딜러와 점수가 같은 경우 카드 개수가 적은 사람이 승리한다.")
        @Test
        void playerScoreEqualsToDealer() {
            Deck threeCards = createNormalWithThreeCards();
            Deck twoCards = createSameScoreNormalWithTwoCards();

            Dealer dealer = new Dealer("딜러");
            for (int i = 0; i < 3; i++) {
                dealer.pickOneCard(threeCards);
            }

            Player player = new Player("pobi");
            for (int i = 0; i < 2; i++) {
                player.pickOneCard(twoCards);
            }

            GameRule rule = createGameRule(player, dealer);

            List<Boolean> gameResult = rule.judge();

            assertThat(gameResult)
                    .hasSize(1)
                    .containsExactly(true);
        }
    }

    private GameRule createGameRule(final Player player, final Dealer dealer) {
        List<Player> players = List.of(player);
        Participant participant = new Participant(dealer, players);

        return new GameRule(participant);
    }

    private Deck createBlackJackWithTwoCards() {
        return new Deck(new ArrayList<>(Arrays.asList(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.ACE, Shape.DIAMOND)
        )));
    }

    private Deck createBlackJackWithThreeCards() {
        return new Deck(new ArrayList<>(Arrays.asList(
                new Card(Score.FIVE, Shape.CLOVER),
                new Card(Score.FIVE, Shape.DIAMOND),
                new Card(Score.ACE, Shape.DIAMOND)
        )));
    }

    private Deck createNormalWithTwoCards() {
        return new Deck(new ArrayList<>(Arrays.asList(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.SIX, Shape.DIAMOND)
        )));
    }

    private Deck createNormalWithThreeCards() {
        return new Deck(new ArrayList<>(Arrays.asList(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.THREE, Shape.DIAMOND),
                new Card(Score.SIX, Shape.HEART)
        )));
    }

    private Deck createSameScoreNormalWithTwoCards() {
        return new Deck(new ArrayList<>(Arrays.asList(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.NINE, Shape.DIAMOND)
        )));
    }

    private Deck createBustedCards() {
        return new Deck(new ArrayList<>(Arrays.asList(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.FIVE, Shape.DIAMOND),
                new Card(Score.EIGHT, Shape.HEART)
        )));
    }
}
