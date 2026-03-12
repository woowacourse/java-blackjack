package blackjack.view;

import blackjack.domain.MatchResult;
import blackjack.domain.Player;
import blackjack.dto.CardInfo;
import blackjack.dto.DealResult;
import blackjack.dto.DealerScoreResult;
import blackjack.dto.GameResult;
import blackjack.dto.PlayerHandResult;
import blackjack.dto.PlayerProfitResult;
import blackjack.dto.PlayerScoreResult;
import blackjack.dto.ProfitResult;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {
    }

    public static void printDealResult(DealResult dealResult) {
        System.out.println("딜러와" + dealResult.playersNames() + "에게 2장을 나누었습니다.");
        System.out.println("딜러카드: " + dealResult.dealerOpenCard().display());
        for (PlayerHandResult playerHand : dealResult.playerHands()) {
            printCurrentPlayerHand(playerHand);
        }
        printLineBreak();
    }

    public static void printCurrentPlayerHand(PlayerHandResult playerHand) {
        String cards = formatCards(playerHand.cards());
        System.out.println(playerHand.name() + "카드: " + cards);
    }

    public static void printLineBreak() {
        System.out.println();
    }

    public static void printGameResult(GameResult gameResult) {
        DealerScoreResult dealer = gameResult.dealerResult();
        System.out.println("딜러카드: " + formatCards(dealer.cards())
                + " - 결과: " + dealer.score());

        for (PlayerScoreResult player : gameResult.playerResults()) {
            System.out.println(player.name() + "카드: " + formatCards(player.cards())
                    + " - 결과: " + player.score());
        }
        printLineBreak();
    }

    private static String formatCards(List<CardInfo> cards) {
        return cards.stream()
                .map(CardInfo::display)
                .collect(Collectors.joining(", "));
    }

    public static void printDealerDrawMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        printLineBreak();
    }

    public static void printFinalResult(Map<Player, MatchResult> playerFinalResult,
                                        Map<String, Long> dealerFinalResult) {
        System.out.println("## 최종 승패");
        printDealerFinalResult(dealerFinalResult.get("승"), dealerFinalResult.get("패"));
        for (Player player : playerFinalResult.keySet()) {
            printPlayerFinalResult(player.name(), playerFinalResult.get(player).getDisplay());
        }
    }

    public static void printFinalResult(ProfitResult result) {
        System.out.println("## 최종 수익");
        System.out.println("딜러: " + result.dealerProfit());
        for (PlayerProfitResult playerProfitResult : result.playerProfits()) {
            System.out.println(playerProfitResult.name() + ": " + playerProfitResult.profit());
        }
    }

    private static void printDealerFinalResult(long dealerWinCount, long dealerLoseCount) {
        System.out.println("딜러: " + dealerWinCount + "승 " + dealerLoseCount + "패");
    }

    private static void printPlayerFinalResult(String playerName, String result) {
        System.out.println(playerName + ": " + result);
    }
}