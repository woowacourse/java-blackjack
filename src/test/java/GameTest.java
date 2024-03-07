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
        dealer.saveCard(new Card(CardValue.FIVE, Shape.DIAMOND));
        dealer.saveCard(new Card(CardValue.FIVE, Shape.CLOVER));
        List<Player> players = List.of(new Player("pobi"));
        Participant participant = new Participant(dealer, players);

        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardValue.THREE, Shape.DIAMOND));
        cards.add(new Card(CardValue.THREE, Shape.CLOVER));
        cards.add(new Card(CardValue.TWO, Shape.HEART));

        Game game = new Game(participant, new Cards(cards));

        // when
        int count = game.giveCardsToDealer();

        // then
        assertThat(count).isEqualTo(3);
    }

    @DisplayName("참가자들의 승패 여부를 판단한다.")
    @Test
    void judgeWinners() {
        // given
        Dealer dealer = new Dealer("딜러");
        dealer.saveCard(new Card(CardValue.TEN, Shape.DIAMOND));
        dealer.saveCard(new Card(CardValue.SEVEN, Shape.CLOVER));

        Player player = new Player("pobi");
        player.saveCard(new Card(CardValue.TEN, Shape.HEART));
        player.saveCard(new Card(CardValue.EIGHT, Shape.SPADE));

        List<Player> players = List.of(player);
        Participant participant = new Participant(dealer, players);

        Game game = new Game(participant, new Cards());

        // when
        List<Boolean> isWinner = game.compareScore();

        // then
        assertThat(isWinner).containsExactly(true);
    }

    @DisplayName("딜러의 점수가 21을 초과한 경우")
    @Nested
    class dealerBusted {
        @DisplayName("참가자의 점수가 21을 초과하면 패배한다.")
        @Test
        void playerBusted() {
            Dealer dealer = new Dealer("딜러");
            dealer.saveCard(new Card(CardValue.TEN, Shape.DIAMOND));
            dealer.saveCard(new Card(CardValue.TEN, Shape.CLOVER));
            dealer.saveCard(new Card(CardValue.TEN, Shape.HEART));

            Player player = new Player("pobi");
            player.saveCard(new Card(CardValue.TEN, Shape.DIAMOND));
            player.saveCard(new Card(CardValue.TEN, Shape.CLOVER));
            player.saveCard(new Card(CardValue.TEN, Shape.HEART));
            List<Player> players = List.of(player);
            Participant participant = new Participant(dealer, players);

            Game game = new Game(participant, new Cards());

            List<Boolean> gameResult = game.judgePlayersIfDealerBusted();

            Assertions.assertThat(gameResult).hasSize(1);
            Assertions.assertThat(gameResult).containsExactly(false);
        }

        @DisplayName("참가자의 점수가 21 미만인 경우 승리한다.")
        @Test
        void playerDoesNotBusted() {
            Dealer dealer = new Dealer("딜러");
            dealer.saveCard(new Card(CardValue.TEN, Shape.DIAMOND));
            dealer.saveCard(new Card(CardValue.TEN, Shape.CLOVER));
            dealer.saveCard(new Card(CardValue.TEN, Shape.HEART));

            Player player = new Player("pobi");
            player.saveCard(new Card(CardValue.TEN, Shape.DIAMOND));
            player.saveCard(new Card(CardValue.TEN, Shape.CLOVER));
            List<Player> players = List.of(player);
            Participant participant = new Participant(dealer, players);

            Game game = new Game(participant, new Cards());

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
            dealer.saveCard(new Card(CardValue.TEN, Shape.DIAMOND));
            dealer.saveCard(new Card(CardValue.NINE, Shape.CLOVER));
            dealer.saveCard(new Card(CardValue.TWO, Shape.CLOVER));

            Player player = new Player("pobi");
            player.saveCard(new Card(CardValue.TEN, Shape.DIAMOND));
            player.saveCard(new Card(CardValue.SEVEN, Shape.CLOVER));
            player.saveCard(new Card(CardValue.TWO, Shape.CLOVER));
            player.saveCard(new Card(CardValue.TWO, Shape.HEART));

            List<Player> players = List.of(player);
            Participant participant = new Participant(dealer, players);

            Game game = new Game(participant, new Cards());

            List<Boolean> gameResult = game.judge();

            Assertions.assertThat(gameResult).hasSize(1);
            Assertions.assertThat(gameResult).containsExactly(false);
        }

        @DisplayName("참가자가 블랙잭이 아닌 경우 딜러가 승리한다.")
        @Test
        void playerIsNotBlackJack() {
            Dealer dealer = new Dealer("딜러");
            dealer.saveCard(new Card(CardValue.TEN, Shape.DIAMOND));
            dealer.saveCard(new Card(CardValue.NINE, Shape.CLOVER));
            dealer.saveCard(new Card(CardValue.TWO, Shape.CLOVER));

            Player player = new Player("pobi");
            player.saveCard(new Card(CardValue.TEN, Shape.DIAMOND));
            player.saveCard(new Card(CardValue.SEVEN, Shape.CLOVER));
            player.saveCard(new Card(CardValue.TWO, Shape.CLOVER));

            List<Player> players = List.of(player);
            Participant participant = new Participant(dealer, players);

            Game game = new Game(participant, new Cards());

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
            dealer.saveCard(new Card(CardValue.TEN, Shape.DIAMOND));
            dealer.saveCard(new Card(CardValue.NINE, Shape.CLOVER));

            Player player = new Player("pobi");
            player.saveCard(new Card(CardValue.TEN, Shape.DIAMOND));
            player.saveCard(new Card(CardValue.SEVEN, Shape.CLOVER));
            player.saveCard(new Card(CardValue.FOUR, Shape.CLOVER));

            List<Player> players = List.of(player);
            Participant participant = new Participant(dealer, players);

            Game game = new Game(participant, new Cards());

            List<Boolean> gameResult = game.judge();

            Assertions.assertThat(gameResult).hasSize(1);
            Assertions.assertThat(gameResult).containsExactly(true);
        }

        @DisplayName("참가자의 점수가 21 미만인 경우 점수가 큰 사람이 승리한다.")
        @Test
        void playerIsNotBlackJack() {
            Dealer dealer = new Dealer("딜러");
            dealer.saveCard(new Card(CardValue.TEN, Shape.DIAMOND));
            dealer.saveCard(new Card(CardValue.NINE, Shape.CLOVER));

            Player player = new Player("pobi");
            player.saveCard(new Card(CardValue.TEN, Shape.DIAMOND));
            player.saveCard(new Card(CardValue.SEVEN, Shape.CLOVER));

            List<Player> players = List.of(player);
            Participant participant = new Participant(dealer, players);

            Game game = new Game(participant, new Cards());

            List<Boolean> gameResult = game.judge();

            Assertions.assertThat(gameResult).hasSize(1);
            Assertions.assertThat(gameResult).containsExactly(false);
        }

        @DisplayName("참가자의 점수가 21 미만이고 딜러와 점수가 같은 경우 카드 개수가 적은 사람이 승리한다.")
        @Test
        void playerScoreEqualsToDealer() {
            Dealer dealer = new Dealer("딜러");
            dealer.saveCard(new Card(CardValue.TEN, Shape.DIAMOND));
            dealer.saveCard(new Card(CardValue.NINE, Shape.CLOVER));

            Player player = new Player("pobi");
            player.saveCard(new Card(CardValue.TEN, Shape.DIAMOND));
            player.saveCard(new Card(CardValue.FOUR, Shape.CLOVER));
            player.saveCard(new Card(CardValue.FIVE, Shape.CLOVER));

            List<Player> players = List.of(player);
            Participant participant = new Participant(dealer, players);

            Game game = new Game(participant, new Cards());

            List<Boolean> gameResult = game.judge();

            Assertions.assertThat(gameResult).hasSize(1);
            Assertions.assertThat(gameResult).containsExactly(false);
        }
    }
}
