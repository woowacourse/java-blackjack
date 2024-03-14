package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.stategy.NoShuffleStrategy;
import blackjack.dto.ProfitResult;
import blackjack.strategy.ShuffleStrategy;
import blackjack.util.JudgeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("결과 판단")
class JudgeUtilTest {

    private final ShuffleStrategy shuffleStrategy = new NoShuffleStrategy();

    private Deck deck;
    private Dealer dealer;
    private final List<String> playerNames = List.of("choco");
    private final List<String> bettings = List.of("1000");
    private Players players;
    private Player choco;
    private ProfitResult profitResult;

    @DisplayName("딜러가 버스트된 경우")
    @Nested
    class whenDealerBust {
        @BeforeEach
        void setUp() {
            deck = new Deck(shuffleStrategy);

            deckDrawLoop(6);

            dealer = new Dealer(deck);
            bustDealer();

            profitResult = new ProfitResult();
        }

        @DisplayName("플레이어가 버스트된 상황은 딜러는 무승부로 판단한다.")
        @Test
        void drawWhenBustTogether() {
            //given
            players = Players.of(playerNames, bettings, dealer);
            choco = players.getPlayers().get(0);

            //when
            bustPlayerChoco(dealer);
            JudgeUtil.judge(profitResult, dealer, choco);

            //then
            assertThat(isDraw(profitResult))
                    .isTrue();
        }

        @DisplayName("플레이어가 블랙잭인 경우 딜러가 패배한다.")
        @Test
        void loseWhenPlayerBlackjack() {
            //given
            deckDrawLoop(3);

            players = Players.of(playerNames, bettings, dealer);
            choco = players.getPlayers().get(0);

            //when
            JudgeUtil.judge(profitResult, dealer, choco);

            //then
            assertThat(isPlayerResultWin(profitResult))
                    .isTrue();
        }

        @DisplayName("플레이어가 일반 카드인 경우 딜러가 패배한다.")
        @Test
        void loseWhenPlayerNormal() {
            //given
            players = Players.of(playerNames, bettings, dealer);
            choco = players.getPlayers().get(0);

            //when
            JudgeUtil.judge(profitResult, dealer, choco);

            //then
            assertThat(isPlayerResultWin(profitResult))
                    .isTrue();
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
            profitResult = new ProfitResult();
        }

        @DisplayName("플레이어가 버스트되면 딜러가 승리한다.")
        @Test
        void winWhenPlayerBust() {
            //given
            players = Players.of(playerNames, bettings, dealer);
            choco = players.getPlayers().get(0);

            //when

            bustPlayerChoco(dealer);
            JudgeUtil.judge(profitResult, dealer, choco);

            //then
            assertThat(isPlayerResultLose(profitResult))
                    .isTrue();
        }

        @DisplayName("플레이어가 블랙잭이면 무승부로 판단한다.")
        @Test
        void drawWhenPlayerBlackjack() {
            //given
            deckDrawLoop(11);

            players = Players.of(playerNames, bettings, dealer);
            choco = players.getPlayers().get(0);

            //when
            JudgeUtil.judge(profitResult, dealer, choco);

            //then
            assertThat(isDraw(profitResult))
                    .isTrue();
        }

        @DisplayName("플레이어가 일반이면 딜러가 승리한다.")
        @Test
        void winWhenPlayerNormal() {
            //given
            players = Players.of(playerNames, bettings, dealer);
            choco = players.getPlayers().get(0);

            //when
            JudgeUtil.judge(profitResult, dealer, choco);

            //then
            assertThat(isPlayerResultLose(profitResult))
                    .isTrue();
        }
    }

    @DisplayName("딜러가 블랙잭도 아니고, 버스트되지 않은 일반 경우")
    @Nested
    class whenDealerNormal {
        @BeforeEach
        void setUp() {
            deck = new Deck(shuffleStrategy);
            dealer = new Dealer(deck);
            profitResult = new ProfitResult();
        }

        @DisplayName("플레이어가 버스트되면 딜러가 승리한다.")
        @Test
        void winWhenPlayerBust() {
            //given
            players = Players.of(playerNames, bettings, dealer);
            choco = players.getPlayers().get(0);

            //when
            bustPlayerChoco(dealer);
            JudgeUtil.judge(profitResult, dealer, choco);

            //then
            assertThat(isPlayerResultLose(profitResult))
                    .isTrue();
        }

        @DisplayName("플레이어가 블랙잭이면 딜러가 패배한다.")
        @Test
        void loseWhenPlayerBlackjack() {
            //given
            deckDrawLoop(10);

            players = Players.of(playerNames, bettings, dealer);
            choco = players.getPlayers().get(0);

            //when
            JudgeUtil.judge(profitResult, dealer, choco);

            //then
            assertThat(isPlayerResultWin(profitResult))
                    .isTrue();
        }

        @DisplayName("플레이어가 일반이면 딜러 카드가 클 경우 딜러가 승리한다.")
        @Test
        void winWhenPlayerNormalWithSmallerScore() {
            //given
            players = Players.of(playerNames, bettings, dealer);
            choco = players.getPlayers().get(0);

            //when
            JudgeUtil.judge(profitResult, dealer, choco);

            //then
            assertThat(isPlayerResultLose(profitResult))
                    .isTrue();
        }

        @DisplayName("플레이어가 일반이면 딜러 카드가 작을 경우 딜러가 패배한다.")
        @Test
        void loseWhenPlayerNormalWithBiggerScore() {
            //given
            deckDrawLoop(5);
            players = Players.of(playerNames, bettings, dealer);
            choco = players.getPlayers().get(0);

            //when
            JudgeUtil.judge(profitResult, dealer, choco);

            //then
            assertThat(isPlayerResultWin(profitResult))
                    .isTrue();
        }

        @DisplayName("플레이어와 점수가 같을 경우 딜러는 무승부로 판정한다.")
        @Test
        void drawWhenPlayerNormalWithSameScore() {
            //given
            deckDrawLoop(3);
            players = Players.of(playerNames, bettings, dealer);
            choco = players.getPlayers().get(0);

            //when
            JudgeUtil.judge(profitResult, dealer, choco);

            //then
            assertThat(isDraw(profitResult))
                    .isTrue();
        }
    }

    private void bustPlayerChoco(final Dealer dealer) {
        for (int i = 0; i < 10; i++) {
            choco.draw(dealer);
        }
    }

    private void bustDealer() {
        dealer.drawExtraCard();
    }

    private void deckDrawLoop(final int count) {
        for (int i = 0; i < count; i++) {
            deck.draw();
        }
    }

    private boolean isPlayerResultLose(final ProfitResult profitResult) {
        return profitResult.findByPlayer(choco) == choco.calculateProfit(GameResult.LOSE);
    }

    private boolean isPlayerResultWin(final ProfitResult profitResult) {
        return profitResult.findByPlayer(choco) == choco.calculateProfit(GameResult.WIN);
    }

    private boolean isDraw(final ProfitResult profitResult) {
        return profitResult.findByPlayer(choco) == choco.calculateProfit(GameResult.DRAW);
    }
}
