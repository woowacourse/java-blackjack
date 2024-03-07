import static org.assertj.core.api.Assertions.assertThat;

import controller.dto.CardStatus;
import controller.dto.CardsStatus;
import domain.Card;
import domain.Cards;
import domain.Dealer;
import domain.Game;
import domain.Participant;
import domain.Player;
import domain.constants.CardValue;
import domain.constants.Shape;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GameTest {

    @DisplayName("참가자들에게 2장 씩 카드를 분배한다.")
    @Test
    void startBlackJack() {
        List<String> playerNames = Arrays.asList("pobi", "jason");
        Dealer dealer = new Dealer("딜러");
        Game game = new Game(dealer, playerNames);

        CardsStatus cardsStatus = game.initiateGameCondition();
        List<CardStatus> statuses = cardsStatus.status();

        for (CardStatus status : statuses) {
            assertThat(status.cards()).hasSize(2);
        }
    }

    @DisplayName("딜러의 점수가 16 이하인동안 반복해서 카드를 받는다.")
    @Test
    void giveCardsUntilDealerScoreOverThreshold() {
        // given
        Dealer dealer = new Dealer("딜러");
        dealer.saveCards(createNormalWithTwoCards());
        
        List<Player> players = List.of(new Player("pobi"));
        Participant participant = new Participant(dealer, players);

        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardValue.THREE, Shape.DIAMOND));

        Game game = new Game(participant, new Cards(cards));

        // when
        int count = game.giveCardsToDealer();

        // then
        assertThat(count).isEqualTo(1);
    }

    @DisplayName("참가자들의 승패 여부를 판단한다.")
    @Test
    void judgeWinners() {
        // given
        Dealer dealer = new Dealer("딜러");
        dealer.saveCards(createNormalWithTwoCards());

        Player player = new Player("pobi");
        player.saveCards(createNormalWithThreeCards());

        Game game = createGame(player, dealer);

        // when
        List<Boolean> isWinner = game.compareScore();

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

            Game game = createGame(player, dealer);

            List<Boolean> gameResult = game.judgePlayersIfDealerBusted();

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

            Game game = createGame(player, dealer);

            List<Boolean> gameResult = game.judgePlayersIfDealerBusted();

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

            Game game = createGame(player, dealer);

            List<Boolean> gameResult = game.judge();

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

            Game game = createGame(player, dealer);

            List<Boolean> gameResult = game.judge();

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

            Game game = createGame(player, dealer);

            List<Boolean> gameResult = game.judge();

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

            Game game = createGame(player, dealer);

            List<Boolean> gameResult = game.judge();

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

            Game game = createGame(player, dealer);

            List<Boolean> gameResult = game.judge();

            Assertions.assertThat(gameResult).hasSize(1);
            Assertions.assertThat(gameResult).containsExactly(true);
        }
    }

    private static Game createGame(final Player player, final Dealer dealer) {
        List<Player> players = List.of(player);
        Participant participant = new Participant(dealer, players);

        Game game = new Game(participant, new Cards());
        return game;
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
