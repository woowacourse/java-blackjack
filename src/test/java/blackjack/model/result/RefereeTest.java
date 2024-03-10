package blackjack.model.result;

import static blackjack.model.deck.Score.ACE;
import static blackjack.model.deck.Score.FIVE;
import static blackjack.model.deck.Score.FOUR;
import static blackjack.model.deck.Score.NINE;
import static blackjack.model.deck.Score.SEVEN;
import static blackjack.model.deck.Score.SIX;
import static blackjack.model.deck.Score.TEN;
import static blackjack.model.deck.Score.THREE;
import static blackjack.model.deck.Score.TWO;
import static blackjack.model.deck.Shape.CLOVER;
import static blackjack.model.deck.Shape.DIA;
import static blackjack.model.deck.Shape.HEART;
import static blackjack.model.deck.Shape.SPADE;
import static blackjack.model.result.ResultCommand.DRAW;
import static blackjack.model.result.ResultCommand.LOSE;
import static blackjack.model.result.ResultCommand.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.deck.Card;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Hand;
import blackjack.model.participant.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RefereeTest {
    @Nested
    @DisplayName("딜러의 합이 21일 미만이면")
    class under21 {

        @Test
        @DisplayName("플레이어의 합이 딜러보다 크면 플레이어가 승리한다.")
        void playerWinWhenBiggerThanDealer() {
            Players players = Players.of(
                    List.of("몰리"),
                    List.of(new Hand(List.of(new Card(CLOVER, TEN), new Card(SPADE, TEN)))));
            Dealer dealer = new Dealer(
                    new Hand(List.of(new Card(SPADE, SEVEN), new Card(DIA, TEN))));

            Referee referee = new Referee(new Rule(dealer), players);
            assertThat(referee.judgePlayerResult()).containsExactlyEntriesOf(Map.of("몰리", WIN));
        }

        @Test
        @DisplayName("플레이어 결과가 딜러의 결과와 동일하지만 카드 수는 적은 경우 플레이어가 승리한다.")
        void playerWinWhenHasLittleCardsThanDealer() {
            Players players = Players.of(
                    List.of("몰리"),
                    List.of(new Hand(List.of(new Card(CLOVER, TEN), new Card(SPADE, TEN)))));
            Dealer dealer = new Dealer(new Hand(
                    List.of(new Card(SPADE, SEVEN), new Card(DIA, TEN),
                            new Card(DIA, THREE))));

            Referee referee = new Referee(new Rule(dealer), players);
            assertThat(referee.judgePlayerResult()).containsExactlyEntriesOf(Map.of("몰리", WIN));
        }
    }

    @Nested
    @DisplayName("딜러가 21인 경우")
    class DealerIs21 {

        @Test
        @DisplayName("플레이어 카드만 블랙잭인 경우 플레이어가 승리한다.")
        void playerWinWhenOnlyPlayerBlackJack() {
            Players players = Players.of(
                    List.of("몰리"),
                    List.of(new Hand(List.of(new Card(CLOVER, TEN), new Card(SPADE, ACE)))));
            Dealer dealer = new Dealer(new Hand(
                    List.of(new Card(SPADE, SEVEN), new Card(DIA, TEN),
                            new Card(DIA, FOUR))));

            Referee referee = new Referee(new Rule(dealer), players);
            assertThat(referee.judgePlayerResult()).containsExactlyEntriesOf(Map.of("몰리", WIN));
        }

        @Test
        @DisplayName("플레이어의 카드 수가 딜러의 카드 수보다 적은 경우 플레이어가 승리한다.")
        void playerWinWhenHasLittleCardsThanDealer() {
            Players players = Players.of(
                    List.of("몰리"),
                    List.of(new Hand(List.of(new Card(CLOVER, TEN), new Card(CLOVER, NINE),
                            new Card(SPADE, TWO)))));
            Dealer dealer = new Dealer(new Hand(
                    List.of(new Card(SPADE, SEVEN), new Card(DIA, FIVE),
                            new Card(CLOVER, FIVE), new Card(DIA, FOUR))));

            Referee referee = new Referee(new Rule(dealer), players);
            assertThat(referee.judgePlayerResult()).containsExactlyEntriesOf(Map.of("몰리", WIN));
        }
    }

    @Nested
    @DisplayName("딜러가 21 초과했을 경우(버스트)")
    class DealerOver21 {

        @Test
        @DisplayName("플레이어 결과가 21이하인 경우 플레이어가 승리한다.")
        void playerWinWhenOnlyPlayerNotBust() {
            Players players = Players.of(
                    List.of("몰리"),
                    List.of(new Hand(List.of(new Card(CLOVER, TEN), new Card(SPADE, FOUR),
                            new Card(DIA, SIX)))));
            Dealer dealer = new Dealer(new Hand(
                    List.of(new Card(SPADE, FOUR), new Card(DIA, TEN),
                            new Card(DIA, TEN))));

            Referee referee = new Referee(new Rule(dealer), players);
            assertThat(referee.judgePlayerResult()).containsExactlyEntriesOf(Map.of("몰리", WIN));
        }

        @Test
        @DisplayName("플레이어가 21을 초과하는 경우 플레이어가 패배한다.")
        void playerLoseWhenAllBust() {
            Players players = Players.of(
                    List.of("몰리"),
                    List.of(new Hand(List.of(new Card(CLOVER, TEN), new Card(SPADE, FOUR),
                            new Card(CLOVER, TEN)))));
            Dealer dealer = new Dealer(new Hand(
                    List.of(new Card(SPADE, FOUR), new Card(DIA, TEN),
                            new Card(DIA, TEN))));

            Referee referee = new Referee(new Rule(dealer), players);
            assertThat(referee.judgePlayerResult()).containsExactlyEntriesOf(Map.of("몰리", LOSE));
        }
    }

    @Nested
    @DisplayName("딜러와 플레이어가 무승부인 경우")
    class draw {

        @Test
        @DisplayName("플레이어와 딜러 모두 블랙잭이면 딜러가 승리한다.")
        void bothBlackJack() {
            Players players = Players.of(
                    List.of("몰리"),
                    List.of(new Hand(List.of(new Card(CLOVER, TEN), new Card(SPADE, ACE)))));
            Dealer dealer = new Dealer(
                    new Hand(List.of(new Card(HEART, ACE), new Card(DIA, TEN))));

            Referee referee = new Referee(new Rule(dealer), players);
            assertThat(referee.judgePlayerResult()).containsExactlyEntriesOf(Map.of("몰리", DRAW));
        }

        @Test
        @DisplayName("플레이어와 딜러의 결과, 카드 수가 모두 동일한 경우")
        void sameScoreAndCount() {
            Players players = Players.of(
                    List.of("몰리"),
                    List.of(new Hand(List.of(new Card(CLOVER, FIVE), new Card(SPADE, FIVE)))));
            Dealer dealer = new Dealer(
                    new Hand(List.of(new Card(HEART, FIVE), new Card(DIA, FIVE))));

            Referee referee = new Referee(new Rule(dealer), players);
            assertThat(referee.judgePlayerResult()).containsExactlyEntriesOf(Map.of("몰리", DRAW));
        }
    }

    @Test
    @DisplayName("플레이어들의 결과를 반환한다.")
    void getPlayerResult() {
        List<Hand> cards = List.of(
                new Hand(List.of(new Card(CLOVER, TEN), new Card(SPADE, TEN),
                        new Card(DIA, FOUR))),
                new Hand(List.of(new Card(CLOVER, TEN), new Card(SPADE, ACE))),
                new Hand(List.of(new Card(CLOVER, TEN), new Card(SPADE, TEN)))
        );

        Players players = Players.of(List.of("몰리", "리브", "포비"), cards);
        Dealer dealer = new Dealer(
                new Hand(List.of(new Card(CLOVER, TEN), new Card(SPADE, TEN))));

        Referee referee = new Referee(new Rule(dealer), players);
        Map<String, ResultCommand> expected = new LinkedHashMap<>();
        expected.put("몰리", LOSE);
        expected.put("리브", WIN);
        expected.put("포비", DRAW);
        assertThat(referee.judgePlayerResult()).containsExactlyEntriesOf(expected);
    }

    @Test
    @DisplayName("딜러의 결과를 반환한다.")
    void getDealerResult() {
        List<Hand> cards = List.of(
                new Hand(List.of(new Card(CLOVER, TEN), new Card(SPADE, TEN),
                        new Card(DIA, FOUR))),
                new Hand(List.of(new Card(CLOVER, TEN), new Card(SPADE, ACE))),
                new Hand(List.of(new Card(CLOVER, TEN), new Card(SPADE, TEN)))
        );

        Players players = Players.of(List.of("몰리", "리브", "포비"), cards);
        Dealer dealer = new Dealer(
                new Hand(List.of(new Card(CLOVER, TEN), new Card(SPADE, TEN))));

        Referee referee = new Referee(new Rule(dealer), players);
        assertThat(referee.judgeDealerResult()).containsAllEntriesOf(Map.of(WIN, 1, LOSE, 1, DRAW, 1));
    }
}
