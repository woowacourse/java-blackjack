package blackjack.model.result;

import static blackjack.model.deck.Score.ACE;
import static blackjack.model.deck.Score.FIVE;
import static blackjack.model.deck.Score.FOUR;
import static blackjack.model.deck.Score.NINE;
import static blackjack.model.deck.Score.SEVEN;
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
import blackjack.model.deck.Deck;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import blackjack.model.participant.Players;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RefereeTest {
    @Nested
    @DisplayName("딜러 카드 총 스코어가 21일 미만일 때")
    class under21 {
        private Player player;
        private Dealer dealer;
        private Referee referee;

        @BeforeEach
        void init() {
            player = new Player("몰리");
            dealer = new Dealer(new Deck());
            referee = new Referee(new Rule(dealer));
        }

        @Test
        @DisplayName("플레이어의 합이 딜러보다 크면 플레이어가 승리한다.")
        void playerWinWhenBiggerThanDealer() {
            player.receiveInitialCards(List.of(new Card(CLOVER, TEN), new Card(SPADE, TEN)));
            dealer.receiveInitialCards(List.of(new Card(SPADE, SEVEN), new Card(DIA, TEN)));

            assertThat(referee.judgePlayerResult(player)).isEqualTo(WIN);
        }

        @Test
        @DisplayName("플레이어 결과가 딜러의 결과와 동일하지만 카드 수는 적은 경우 플레이어가 승리한다.")
        void playerWinWhenHasLittleCardsThanDealer() {
            player.receiveInitialCards(List.of(new Card(CLOVER, TEN), new Card(SPADE, TEN)));
            dealer.receiveInitialCards(List.of(new Card(SPADE, SEVEN), new Card(DIA, TEN), new Card(DIA, THREE)));

            assertThat(referee.judgePlayerResult(player)).isEqualTo(WIN);
        }
    }

    @Nested
    @DisplayName("딜러가 21인 경우")
    class DealerIs21 {
        private Player player;
        private Dealer dealer;
        private Referee referee;

        @BeforeEach
        void init() {
            player = new Player("몰리");
            dealer = new Dealer(new Deck());
            referee = new Referee(new Rule(dealer));
        }

        @Test
        @DisplayName("플레이어 카드만 블랙잭인 경우 플레이어가 승리한다.")
        void playerWinWhenOnlyPlayerBlackJack() {
            player.receiveInitialCards(List.of(new Card(CLOVER, TEN), new Card(SPADE, ACE)));
            dealer.receiveInitialCards(List.of(new Card(SPADE, SEVEN), new Card(DIA, TEN), new Card(DIA, FOUR)));

            assertThat(referee.judgePlayerResult(player)).isEqualTo(WIN);
        }

        @Test
        @DisplayName("플레이어도 21이면 플레이어의 카드 수가 딜러의 카드 수보다 적은 경우 플레이어가 승리한다.")
        void playerWinWhenHasLittleCardsThanDealer() {
            player.receiveInitialCards(List.of(new Card(CLOVER, TEN), new Card(CLOVER, NINE), new Card(SPADE, TWO)));
            dealer.receiveInitialCards(
                    List.of(new Card(SPADE, SEVEN), new Card(DIA, FIVE), new Card(CLOVER, FIVE), new Card(DIA, FOUR)));

            assertThat(referee.judgePlayerResult(player)).isEqualTo(WIN);
        }
    }

    @Nested
    @DisplayName("딜러가 21 초과했을 경우(버스트)")
    class DealerOver21 {
        private Player player;
        private Dealer dealer;
        private Referee referee;

        @BeforeEach
        void init() {
            player = new Player("몰리");
            dealer = new Dealer(new Deck());
            referee = new Referee(new Rule(dealer));
        }

        @Test
        @DisplayName("플레이어 결과가 21이하인 경우 플레이어가 승리한다.")
        void playerWinWhenOnlyPlayerNotBust() {
            player.receiveInitialCards(List.of(new Card(CLOVER, TEN), new Card(SPADE, FOUR)));
            dealer.receiveInitialCards(List.of(new Card(SPADE, FOUR), new Card(DIA, TEN), new Card(DIA, TEN)));

            assertThat(referee.judgePlayerResult(player)).isEqualTo(WIN);
        }

        @Test
        @DisplayName("플레이어가 21을 초과하는 경우 플레이어가 패배한다.")
        void playerLoseWhenAllBust() {
            player.receiveInitialCards(List.of(new Card(CLOVER, TEN), new Card(SPADE, FOUR), new Card(CLOVER, TEN)));
            dealer.receiveInitialCards(List.of(new Card(SPADE, FOUR), new Card(DIA, TEN), new Card(DIA, TEN)));

            assertThat(referee.judgePlayerResult(player)).isEqualTo(LOSE);
        }
    }

    @Nested
    @DisplayName("딜러와 플레이어가 무승부인 경우")
    class Draw {
        private Player player;
        private Dealer dealer;
        private Referee referee;

        @BeforeEach
        void init() {
            player = new Player("몰리");
            dealer = new Dealer(new Deck());
            referee = new Referee(new Rule(dealer));
        }

        @Test
        @DisplayName("플레이어와 딜러 모두 블랙잭이면 무승부이다.")
        void bothBlackJack() {
            player.receiveInitialCards(List.of(new Card(CLOVER, TEN), new Card(SPADE, ACE)));
            dealer.receiveInitialCards(List.of(new Card(HEART, ACE), new Card(DIA, TEN)));

            assertThat(referee.judgePlayerResult(player)).isEqualTo(DRAW);
        }

        @Test
        @DisplayName("플레이어와 딜러의 결과, 카드 수가 모두 동일하면 무승부이다.")
        void sameScoreAndCount() {
            player.receiveInitialCards(List.of(new Card(CLOVER, FIVE), new Card(SPADE, FIVE)));
            dealer.receiveInitialCards(List.of(new Card(HEART, FIVE), new Card(DIA, FIVE)));

            assertThat(referee.judgePlayerResult(player)).isEqualTo(DRAW);
        }
    }

    @ParameterizedTest
    @MethodSource("generateCardsSupplierExpected")
    @DisplayName("딜러의 결과를 반환한다.")
    void getDealerResult(List<Card> cards, ResultCommand expected) {
        Dealer dealer = new Dealer(new Deck());
        dealer.receiveInitialCards(List.of(new Card(CLOVER, TEN), new Card(SPADE, TEN)));
        Players players = Players.from(List.of("몰리", "리브", "포비"));
        players.receiveInitialCards(() -> cards);

        Referee referee = new Referee(new Rule(dealer));
        assertThat(referee.judgeDealerResult(players)).containsAllEntriesOf(Map.of(expected, 3));
    }

    static Stream<Arguments> generateCardsSupplierExpected() {
        return Stream.of(
                Arguments.of(List.of(new Card(CLOVER, TEN), new Card(SPADE, TEN), new Card(DIA, FOUR)), WIN),
                Arguments.of(List.of(new Card(CLOVER, TEN), new Card(SPADE, ACE)), LOSE),
                Arguments.of(List.of(new Card(CLOVER, TEN), new Card(SPADE, TEN)), DRAW)
        );
    }
}
