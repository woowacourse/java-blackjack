package domain;

import static org.assertj.core.api.Assertions.assertThat;

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
        Hand twoCards = createNormalWithTwoCards();
        Hand threeCards = createNormalWithThreeCards();

        Dealer dealer = new Dealer(twoCards);
        Gamer gamer = new Gamer("pobi", threeCards);
        GameRule rule = createGameRule(gamer, dealer);

        // when
        List<Boolean> isWinner = rule.judge();

        // then
        assertThat(isWinner).containsExactly(true);
    }

    @DisplayName("딜러의 점수가 21을 초과한 경우")
    @Nested
    class DealerBusted {
        @DisplayName("모든 참가자는 21을 초과하면 무조건 패배한다.")
        @Test
        void playerBusted() {
            Hand bustedCardsForDealer = createBustedCards();
            Dealer dealer = new Dealer(bustedCardsForDealer);

            Hand bustedCardsForPlayer = createBustedCards();
            Gamer gamer = new Gamer("pobi", bustedCardsForPlayer);
            GameRule rule = createGameRule(gamer, dealer);

            List<Boolean> gameResult = rule.judgeGamersIfDealerBusted();

            assertThat(gameResult)
                    .hasSize(1)
                    .containsExactly(false);
        }

        @DisplayName("참가자의 점수가 21 미만인 경우 승리한다.")
        @Test
        void playerDoesNotBusted() {
            Hand bustedCards = createBustedCards();
            Hand twoCards = createNormalWithTwoCards();

            Dealer dealer = new Dealer(bustedCards);
            Gamer gamer = new Gamer("pobi", twoCards);
            GameRule rule = createGameRule(gamer, dealer);

            List<Boolean> gameResult = rule.judgeGamersIfDealerBusted();

            assertThat(gameResult)
                    .hasSize(1)
                    .containsExactly(true);
        }
    }

    @DisplayName("딜러가 블랙잭인 경우")
    @Nested
    class DealerBlackJack {
        @DisplayName("참가자가 블랙잭인 경우 카드 개수가 적은 사람이 승리한다.")
        @Test
        void playerBlackJack() {
            Hand blackJackTwoCards = createBlackJackWithTwoCards();
            Hand blackJackThreeCards = createBlackJackWithThreeCards();

            Dealer dealer = new Dealer(blackJackTwoCards);
            Gamer gamer = new Gamer("pobi", blackJackThreeCards);
            GameRule rule = createGameRule(gamer, dealer);

            List<Boolean> gameResult = rule.judge();

            assertThat(gameResult)
                    .hasSize(1)
                    .containsExactly(false);
        }

        @DisplayName("참가자가 블랙잭이 아닌 경우 딜러가 승리한다.")
        @Test
        void playerIsNotBlackJack() {
            Hand blackJackTwoCards = createBlackJackWithTwoCards();
            Hand twoCards = createNormalWithTwoCards();

            Dealer dealer = new Dealer(blackJackTwoCards);
            Gamer gamer = new Gamer("pobi", twoCards);
            GameRule rule = createGameRule(gamer, dealer);

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
            Hand twoCards = createNormalWithTwoCards();
            Hand blackJackTwoCards = createBlackJackWithTwoCards();

            Dealer dealer = new Dealer(twoCards);
            Gamer gamer = new Gamer("pobi", blackJackTwoCards);
            GameRule rule = createGameRule(gamer, dealer);

            List<Boolean> gameResult = rule.judge();

            assertThat(gameResult)
                    .hasSize(1)
                    .containsExactly(true);
        }

        @DisplayName("참가자의 점수가 21 미만인 경우 점수가 큰 사람이 승리한다.")
        @Test
        void playerIsNotBlackJack() {
            Hand twoCards = createNormalWithTwoCards();
            Hand threeCards = createNormalWithThreeCards();

            Dealer dealer = new Dealer(twoCards);
            Gamer gamer = new Gamer("pobi", threeCards);
            GameRule rule = createGameRule(gamer, dealer);

            List<Boolean> gameResult = rule.judge();

            assertThat(gameResult)
                    .hasSize(1)
                    .containsExactly(true);
        }

        @DisplayName("참가자의 점수가 21 미만이고 딜러와 점수가 같은 경우 카드 개수가 적은 사람이 승리한다.")
        @Test
        void playerScoreEqualsToDealer() {
            Hand threeCards = createNormalWithThreeCards();
            Hand twoCards = createSameScoreNormalWithTwoCards();

            Dealer dealer = new Dealer(threeCards);
            Gamer gamer = new Gamer("pobi", twoCards);
            GameRule rule = createGameRule(gamer, dealer);

            List<Boolean> gameResult = rule.judge();

            assertThat(gameResult)
                    .hasSize(1)
                    .containsExactly(true);
        }
    }

    private GameRule createGameRule(final Gamer gamer, final Dealer dealer) {
        Gamers gamers = new Gamers(List.of(gamer));

        return new GameRule(dealer, gamers);
    }

    private Hand createBlackJackWithTwoCards() {
        Hand hand = new Hand();
        hand.saveCards(List.of(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.ACE, Shape.DIAMOND)
        ));
        return hand;
    }

    private Hand createBlackJackWithThreeCards() {
        Hand hand = new Hand();
        hand.saveCards(new ArrayList<>(Arrays.asList(
                new Card(Score.FIVE, Shape.CLOVER),
                new Card(Score.FIVE, Shape.DIAMOND),
                new Card(Score.ACE, Shape.DIAMOND)
        )));
        return hand;
    }

    private Hand createNormalWithTwoCards() {
        Hand hand = new Hand();
        hand.saveCards(new ArrayList<>(Arrays.asList(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.SIX, Shape.DIAMOND)
        )));
        return hand;
    }

    private Hand createNormalWithThreeCards() {
        Hand hand = new Hand();
        hand.saveCards(new ArrayList<>(Arrays.asList(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.THREE, Shape.DIAMOND),
                new Card(Score.SIX, Shape.HEART)
        )));
        return hand;
    }

    private Hand createSameScoreNormalWithTwoCards() {
        Hand hand = new Hand();
        hand.saveCards(new ArrayList<>(Arrays.asList(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.NINE, Shape.DIAMOND)
        )));
        return hand;
    }

    private Hand createBustedCards() {
        Hand hand = new Hand();
        hand.saveCards(new ArrayList<>(Arrays.asList(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.FIVE, Shape.DIAMOND),
                new Card(Score.EIGHT, Shape.HEART)
        )));
        return hand;
    }
}
