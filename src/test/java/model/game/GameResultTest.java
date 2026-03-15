package model.game;

import static org.assertj.core.api.Assertions.assertThat;
import static util.ParticipantProvider.*;

import java.math.BigDecimal;
import model.participant.Dealer;
import model.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class GameResultTest {
    private static final BigDecimal BLACKJACK_PROFIT_RATE = new BigDecimal("1.5");
    private static final BigDecimal WIN_PROFIT_RATE = new BigDecimal("1.0");
    private static final BigDecimal PUSH_PROFIT_RATE = new BigDecimal("0.0");
    private static final BigDecimal LOSE_PROFIT_RATE = new BigDecimal("-1.0");

    private BettingAmount bettingAmount;

    @BeforeEach
    void setUp() {
        bettingAmount = BettingAmount.from(10000);
    }

    @Nested
    class 블랙잭_분기의_승패_판정 {
        @Test
        void 플레이어와_딜러_모두_블랙잭이면_무승부이다() {
            Player playerWithBlackjack = createPlayerWithBlackjack();
            Dealer dealerWithBlackjack = createDealerWithBlackjack();

            assertThat(GameResult.calculateResult(playerWithBlackjack, dealerWithBlackjack))
                    .isEqualTo(GameResult.PUSH);
        }

        @Test
        void 플레이어만_블랙잭이면_블랙잭_승리이다() {
            Player playerWithBlackjack = createPlayerWithBlackjack();
            Dealer dealerWithScore21 = createDealerWithScore21();

            assertThat(GameResult.calculateResult(playerWithBlackjack, dealerWithScore21))
                    .isEqualTo(GameResult.BLACKJACK);
        }

        @Test
        void 딜러만_블랙잭이면_플레이어의_패배이다() {
            Player playerWithScore21 = createPlayerWithScore21();
            Dealer dealerWithBlackjack = createDealerWithBlackjack();

            assertThat(GameResult.calculateResult(playerWithScore21, dealerWithBlackjack))
                    .isEqualTo(GameResult.LOSE);
        }
    }

    @Nested
    class 버스트_분기의_승패_판정 {
        @Nested
        class 플레이어가_버스트면_딜러의_결과와_관계없이_패배이다 {
            @Test
            void 플레이어만_버스트_패배() {
                Player playerWithBust = createPlayerWithBust();
                Dealer dealerWithScore21 = createDealerWithScore21();

                assertThat(GameResult.calculateResult(playerWithBust, dealerWithScore21))
                        .isEqualTo(GameResult.LOSE);
            }

            @Test
            void 딜러까지_버스트여도_패배() {
                Player playerWithBust = createPlayerWithBust();
                Dealer dealerWithBust = createDealerWithBust();

                assertThat(GameResult.calculateResult(playerWithBust, dealerWithBust))
                        .isEqualTo(GameResult.LOSE);
            }
        }

        @Test
        void 딜러가_버스트이고_플레이어는_아니라면_승리() {
            Player playerWithScore19 = createPlayerWithScore19();
            Dealer dealerWithBust = createDealerWithBust();

            assertThat(GameResult.calculateResult(playerWithScore19, dealerWithBust))
                    .isEqualTo(GameResult.WIN);
        }
    }

    @Nested
    class 점수_비교_분기의_승패_판정 {
        @Test
        void 점수가_같으면_무승부() {
            Player playerWithScore21 = createPlayerWithScore21();
            Dealer dealerWithScore21 = createDealerWithScore21();

            assertThat(GameResult.calculateResult(playerWithScore21, dealerWithScore21))
                    .isEqualTo(GameResult.PUSH);
        }

        @Test
        void 플레이어가_딜러보다_점수가_높으면_승리() {
            Player playerWithScore21 = createPlayerWithScore21();
            Dealer dealerWithScore20 = createDealerWithScore20();

            assertThat(GameResult.calculateResult(playerWithScore21, dealerWithScore20))
                    .isEqualTo(GameResult.WIN);
        }

        @Test
        void 플레이어가_딜러보다_점수가_낮으면_패배() {
            Player playerWithScore20 = createPlayerWithScore20();
            Dealer dealerWithScore21 = createDealerWithScore21();

            assertThat(GameResult.calculateResult(playerWithScore20, dealerWithScore21))
                    .isEqualTo(GameResult.LOSE);
        }
    }

    @DisplayName("블랙잭으로 승리하면 1.5배의 수익을 얻는다.")
    @Test
    void 블랙잭으로_승리할_때의_수익_계산_테스트() {
        GameResult blackjack = GameResult.BLACKJACK;
        BigDecimal profit = blackjack.getProfitFrom(bettingAmount);

        BigDecimal expectedProfit = bettingAmount.getAmount().multiply(BLACKJACK_PROFIT_RATE);
        assertThat(profit).isEqualByComparingTo(expectedProfit);
    }

    @DisplayName("블랙잭이 아닌데 승리하면 1.0배의 수익을 얻는다.")
    @Test
    void 블랙잭이_아닌데_승리할_때의_수익_계산_테스트() {
        GameResult win = GameResult.WIN;
        BigDecimal profit = win.getProfitFrom(bettingAmount);

        BigDecimal expectedProfit = bettingAmount.getAmount().multiply(WIN_PROFIT_RATE);
        assertThat(profit).isEqualByComparingTo(expectedProfit);
    }

    @DisplayName("무승부라면 베팅 금액을 돌려 받는다")
    @Test
    void 무승부라면_베팅_금액을_돌려_받는다() {
        GameResult push = GameResult.PUSH;
        BigDecimal profit = push.getProfitFrom(bettingAmount);

        BigDecimal expectedProfit = bettingAmount.getAmount().multiply(PUSH_PROFIT_RATE);
        assertThat(profit).isEqualByComparingTo(expectedProfit);
    }

    @DisplayName("패배한다면 베팅 금액을 잃는다")
    @Test
    void 패배한다면_베팅_금액을_잃는다() {
        GameResult push = GameResult.LOSE;
        BigDecimal profit = push.getProfitFrom(bettingAmount);

        BigDecimal expectedProfit = bettingAmount.getAmount().multiply(LOSE_PROFIT_RATE);
        assertThat(profit).isEqualByComparingTo(expectedProfit);
    }
}
