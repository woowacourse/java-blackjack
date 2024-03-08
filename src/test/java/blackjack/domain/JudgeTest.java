package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.stategy.TestShuffleStrategy;
import blackjack.dto.DealerResult;
import blackjack.dto.PlayerResult;
import blackjack.strategy.ShuffleStrategy;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("결과 판단")
class JudgeTest {

    private final ShuffleStrategy shuffleStrategy = new TestShuffleStrategy();

    private Deck deck;
    private Dealer dealer;
    private List<String> playerNames = List.of("choco");
    private Players players;
    private Player player1;
    private int bustDrawCount = 10;

    @DisplayName("딜러가 버스트된 경우")
    @Nested
    class whenDealerBust {
        @BeforeEach
        void setUp() {
            deck = new Deck(shuffleStrategy);
            deckDrawLoop(6);
            dealer = new Dealer(deck);
            bustDealer();
        }

        @DisplayName("플레이어가 버스트된 상황은 딜러는 무승부로 판단한다.")
        @Test
        void drawWhenBustTogether() {
            //given
            players = Players.of(playerNames, dealer);
            player1 = players.getPlayers().get(0);
            PlayerResult playerResult = new PlayerResult();
            ResultStatus resultStatus = new ResultStatus(0, 0, 0);

            //when
            bustPlayer1(dealer);
            DealerResult dealerResult = Judge.judge(resultStatus, player1, dealer, playerResult);

            //then
            assertThat(dealerResult.getWins())
                    .isEqualTo(0);
            assertThat(dealerResult.getLoses())
                    .isEqualTo(0);
            assertThat(dealerResult.getDraws())
                    .isEqualTo(1);
        }

        @DisplayName("플레이어가 블랙잭인 경우 딜러가 패배한다.")
        @Test
        void loseWhenPlayerBlackjack() {
            //given
            deckDrawLoop(3);
            players = Players.of(playerNames, dealer);
            player1 = players.getPlayers().get(0);
            PlayerResult playerResult = new PlayerResult();
            ResultStatus resultStatus = new ResultStatus(0, 0, 0);

            //when
            DealerResult dealerResult = Judge.judge(resultStatus, player1, dealer, playerResult);

            //then
            assertThat(dealerResult.getWins())
                    .isEqualTo(0);
            assertThat(dealerResult.getLoses())
                    .isEqualTo(1);
            assertThat(dealerResult.getDraws())
                    .isEqualTo(0);
        }

        @DisplayName("플레이어가 일반 카드인 경우 딜러가 패배한다.")
        @Test
        void loseWhenPlayerNormal() {
            //given
            players = Players.of(playerNames, dealer);
            player1 = players.getPlayers().get(0);
            PlayerResult playerResult = new PlayerResult();
            ResultStatus resultStatus = new ResultStatus(0, 0, 0);

            //when
            DealerResult dealerResult = Judge.judge(resultStatus, player1, dealer, playerResult);

            //then
            assertThat(dealerResult.getWins())
                    .isEqualTo(0);
            assertThat(dealerResult.getLoses())
                    .isEqualTo(1);
            assertThat(dealerResult.getDraws())
                    .isEqualTo(0);
        }
    }

    @DisplayName("딜러가 블랙잭인 경우")
    @Nested
    class whenDealerBlackjack {
        @BeforeEach
        void setUp() {
            deck = new Deck(shuffleStrategy);
            deckDrawLoop(12);
            dealer = new Dealer(deck);
        }

        @DisplayName("플레이어가 버스트되면 딜러가 승리한다.")
        @Test
        void winWhenPlayerBust() {
            //given
            players = Players.of(playerNames, dealer);
            player1 = players.getPlayers().get(0);

            PlayerResult playerResult = new PlayerResult();
            ResultStatus resultStatus = new ResultStatus(0, 0, 0);

            //when
            bustPlayer1(dealer);
            DealerResult dealerResult = Judge.judge(resultStatus, player1, dealer, playerResult);

            //then
            assertThat(dealerResult.getWins())
                    .isEqualTo(1);
            assertThat(dealerResult.getLoses())
                    .isEqualTo(0);
            assertThat(dealerResult.getDraws())
                    .isEqualTo(0);
        }

        @DisplayName("플레이어가 블랙잭이면 무승부로 판단한다.")
        @Test
        void drawWhenPlayerBlackjack() {
            //given
            deckDrawLoop(11);

            players = Players.of(playerNames, dealer);
            player1 = players.getPlayers().get(0);

            PlayerResult playerResult = new PlayerResult();
            ResultStatus resultStatus = new ResultStatus(0, 0, 0);

            //when
            DealerResult dealerResult = Judge.judge(resultStatus, player1, dealer, playerResult);

            //then
            assertThat(dealerResult.getWins())
                    .isEqualTo(0);
            assertThat(dealerResult.getLoses())
                    .isEqualTo(0);
            assertThat(dealerResult.getDraws())
                    .isEqualTo(1);
        }

        @DisplayName("플레이어가 일반이면 딜러가 승리한다.")
        @Test
        void winWhenPlayerNormal() {
            //given
            players = Players.of(playerNames, dealer);
            player1 = players.getPlayers().get(0);

            PlayerResult playerResult = new PlayerResult();
            ResultStatus resultStatus = new ResultStatus(0, 0, 0);

            //when
            DealerResult dealerResult = Judge.judge(resultStatus, player1, dealer, playerResult);

            //then
            assertThat(dealerResult.getWins())
                    .isEqualTo(1);
            assertThat(dealerResult.getLoses())
                    .isEqualTo(0);
            assertThat(dealerResult.getDraws())
                    .isEqualTo(0);
        }
    }

    @DisplayName("딜러가 블랙재도 아니고, 버스트되지 않은 일반 경우")
    @Nested
    class whenDealerNormal {
        @BeforeEach
        void setUp() {
            deck = new Deck(shuffleStrategy);
            dealer = new Dealer(deck);
        }

        @DisplayName("플레이어가 버스트되면 딜러가 승리한다.")
        @Test
        void winWhenPlayerBust() {
            //given
            players = Players.of(playerNames, dealer);
            player1 = players.getPlayers().get(0);

            PlayerResult playerResult = new PlayerResult();
            ResultStatus resultStatus = new ResultStatus(0, 0, 0);

            //when
            bustPlayer1(dealer);
            DealerResult dealerResult = Judge.judge(resultStatus, player1, dealer, playerResult);

            //then
            assertThat(dealerResult.getWins())
                    .isEqualTo(1);
            assertThat(dealerResult.getLoses())
                    .isEqualTo(0);
            assertThat(dealerResult.getDraws())
                    .isEqualTo(0);
        }

        @DisplayName("플레이어가 블랙잭이면 딜러가 패배한다.")
        @Test
        void loseWhenPlayerBlackjack() {
            //given
            deckDrawLoop(10);

            players = Players.of(playerNames, dealer);
            player1 = players.getPlayers().get(0);

            PlayerResult playerResult = new PlayerResult();
            ResultStatus resultStatus = new ResultStatus(0, 0, 0);

            //when
            DealerResult dealerResult = Judge.judge(resultStatus, player1, dealer, playerResult);

            //then
            assertThat(dealerResult.getWins())
                    .isEqualTo(0);
            assertThat(dealerResult.getLoses())
                    .isEqualTo(1);
            assertThat(dealerResult.getDraws())
                    .isEqualTo(0);
        }

        @DisplayName("플레이어가 일반이면 딜러 카드가 클 경우 딜러가 승리한다.")
        @Test
        void winWhenPlayerNormalWithSmallerScore() {
            //given
            players = Players.of(playerNames, dealer);
            player1 = players.getPlayers().get(0);

            PlayerResult playerResult = new PlayerResult();
            ResultStatus resultStatus = new ResultStatus(0, 0, 0);

            //when
            DealerResult dealerResult = Judge.judge(resultStatus, player1, dealer, playerResult);

            //then
            assertThat(dealerResult.getWins())
                    .isEqualTo(1);
            assertThat(dealerResult.getLoses())
                    .isEqualTo(0);
            assertThat(dealerResult.getDraws())
                    .isEqualTo(0);
        }

        @DisplayName("플레이어가 일반이면 딜러 카드가 작을 경우 딜러가 패배한다.")
        @Test
        void loseWhenPlayerNormalWithBiggerScore() {
            //given
            deckDrawLoop(5);
            players = Players.of(playerNames, dealer);
            player1 = players.getPlayers().get(0);

            PlayerResult playerResult = new PlayerResult();
            ResultStatus resultStatus = new ResultStatus(0, 0, 0);

            //when
            DealerResult dealerResult = Judge.judge(resultStatus, player1, dealer, playerResult);

            //then
            assertThat(dealerResult.getWins())
                    .isEqualTo(0);
            assertThat(dealerResult.getLoses())
                    .isEqualTo(1);
            assertThat(dealerResult.getDraws())
                    .isEqualTo(0);
        }
    }

    private void bustPlayer1(final Dealer dealer) {
        IntStream.range(0, bustDrawCount)
                .forEach(i -> player1.draw(dealer));
    }

    private void bustDealer() {
        dealer.isCardAdded();
    }

    private void deckDrawLoop(final int count) {
        IntStream.range(0, count)
                .forEach(i -> deck.drawn());
    }
}
