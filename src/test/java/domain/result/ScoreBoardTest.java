package domain.result;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.CardInfo;
import domain.Money;
import domain.PlayedGameResult;
import domain.ProfitInfo;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreBoardTest {

    private static final int BLACK_JACK_NUMBER = 21;
    private static ScoreBoard scoreBoard;

    @BeforeEach
    void setup() {
        scoreBoard = new ScoreBoard();
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고, 딜러가 블랙잭이 아니라면 플레이어의 수익 금액은 베팅 금액의 1.5배이어야 한다.")
    void 플레이어가_블랙잭_딜러가_블랙잭이_아닐_시_수익_확인() {
        long bettingMoney = 1000L;
        setupBlackJackPlayer(bettingMoney);
        PlayedGameResult dealerResult = PlayedGameResult.from("딜러", List.of(), BLACK_JACK_NUMBER - 1);

        List<ProfitInfo> profitInfos = scoreBoard.evaluatePlayerProfitInfosWith(dealerResult);
        ProfitInfo playerProfitInfo = profitInfos.getFirst();
        long profit = playerProfitInfo.money().amount();

        assertThat(profit).isEqualTo((long) (bettingMoney * 1.5));
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고, 딜러가 블랙잭이라면 플레이어 수익 금액은 0이어야 한다.")
    void 플레이어가_블랙잭_딜러도_블랙잭_수익_확인() {
        long bettingMoney = 1000L;
        setupBlackJackPlayer(bettingMoney);
        PlayedGameResult dealerResult = PlayedGameResult.from("딜러", blackjackCardInfos, BLACK_JACK_NUMBER);

        List<ProfitInfo> profitInfos = scoreBoard.evaluatePlayerProfitInfosWith(dealerResult);
        ProfitInfo playerProfitInfo = profitInfos.getFirst();
        long profit = playerProfitInfo.money().amount();

        assertThat(profit).isEqualTo(0L);
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 블랙잭이 아니고, 버스트 되지 않았을 때, 플레이어의 점수가 크면 플레이어는 베팅금액만큼 수익 금액을 얻어야 한다.")
    void 플레이어와_딜러_모두_블랙잭_그리고_버스트_아닐_때_플레이어_승리() {
        long bettingMoney = 1000L;

        setupParticipant("pobi", bettingMoney, dummyCardInfos,15);
        PlayedGameResult dealerResult = getDealerResultWithScoreSum(11);

        List<ProfitInfo> profitInfos = scoreBoard.evaluatePlayerProfitInfosWith(dealerResult);
        ProfitInfo playerProfitInfo = profitInfos.getFirst();
        long profit = playerProfitInfo.money().amount();

        assertThat(profit).isEqualTo(bettingMoney);
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 블랙잭이 아니고, 버스트 되지 않았을 때, 플레이어의 점수와 딜러 점수가 같다면 수익 금액은 0이다.")
    void 플레이어와_딜러_모두_블랙잭이나_버스트가_아닐_때_플레이어_무승부() {
        long bettingMoney = 1000L;

        setupParticipant("pobi", bettingMoney, dummyCardInfos,20);
        PlayedGameResult dealerResult = getDealerResultWithScoreSum(20);

        List<ProfitInfo> profitInfos = scoreBoard.evaluatePlayerProfitInfosWith(dealerResult);
        ProfitInfo playerProfitInfo = profitInfos.getFirst();
        long profit = playerProfitInfo.money().amount();

        assertThat(profit).isEqualTo(0L);
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 블랙잭이 아니고, 버스트 되지 않았을 때, 플레이어의 점수와 딜러 점수가 같다면 수익 금액은 배팅 금액의 음수이다.")
    void 플레이어와_딜러_모두_블랙잭이나_버스트가_아닐_때_플레이어_패() {
        long bettingMoney = 1000L;

        setupParticipant("pobi", bettingMoney, dummyCardInfos,10);
        PlayedGameResult dealerResult = getDealerResultWithScoreSum(20);

        List<ProfitInfo> profitInfos = scoreBoard.evaluatePlayerProfitInfosWith(dealerResult);
        ProfitInfo playerProfitInfo = profitInfos.getFirst();
        long profit = playerProfitInfo.money().amount();

        assertThat(profit).isEqualTo(Math.negateExact(bettingMoney));
    }

    private PlayedGameResult getDealerResultWithScoreSum(int scoreSum) {
        return PlayedGameResult.from("딜러", dummyCardInfos, scoreSum);
    }

    private void setupBlackJackPlayer(long bettingMoney) {
        scoreBoard.setupPlayers("pobi", Money.of(bettingMoney));
        scoreBoard.record(PlayedGameResult.from("pobi", blackjackCardInfos, BLACK_JACK_NUMBER));
    }

    private void setupParticipant(String name, long bettingMoney, List<CardInfo> cardInfos, int scoreSum) {
        scoreBoard.setupPlayers(name, Money.of(bettingMoney));
        scoreBoard.record(PlayedGameResult.from(name, cardInfos, scoreSum));
    }

    private static List<CardInfo> blackjackCardInfos = List.of(
            new CardInfo("A" , "클로버"),
            new CardInfo("10" , "클로버")
    );

    private static List<CardInfo> nonBlackJackTwentyOne = List.of(
            new CardInfo("10" , "클로버"),
            new CardInfo("9" , "클로버"),
            new CardInfo("2" , "클로버")
    );

    private static List<CardInfo> scoreSumSix = List.of(
            new CardInfo("2" , "클로버"),
            new CardInfo("2" , "클로버"),
            new CardInfo("2" , "클로버")
    );
    private static List<CardInfo> dummyCardInfos = List.of(
            new CardInfo("1" , "클로버"),
            new CardInfo("1" , "클로버"),
            new CardInfo("1" , "클로버")
    );
}
