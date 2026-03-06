package blackjack.view;

import blackjack.dto.CardInfo;
import blackjack.dto.DealResult;
import blackjack.dto.DealerScoreResult;
import blackjack.dto.GameResult;
import blackjack.dto.PlayerHandResult;
import blackjack.dto.PlayerScoreResult;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {
    }

    public static void printDealResult(DealResult dealResult) {
        System.out.println("딜러카드: " + dealResult.dealerOpenCard().display());
        for (PlayerHandResult playerHand : dealResult.playerHands()) {
            printCurrentPlayerHand(playerHand);
        }
        System.out.println();
    }

    public static void printCurrentPlayerHand(PlayerHandResult playerHand) {
        String cards = formatCards(playerHand.cards());
        System.out.println(playerHand.name() + "카드: " + cards);
    }

    public static void printGameResult(GameResult gameResult) {
        DealerScoreResult dealer = gameResult.dealerResult();
        System.out.println("딜러카드: " + formatCards(dealer.cards())
                + " - 결과: " + dealer.score());

        for (PlayerScoreResult player : gameResult.playerResults()) {
            System.out.println(player.name() + "카드: " + formatCards(player.cards())
                    + " - 결과: " + player.score());
        }
    }

    private static String formatCards(List<CardInfo> cards) {
        return cards.stream()
                .map(CardInfo::display)
                .collect(Collectors.joining(", "));
    }

    public static void printDealerFinalResult(int dealerWinCount, int dealerLoseCount) {
        System.out.println("딜러: " + dealerWinCount + "승 " + dealerLoseCount + "패");
    }

    // 플레이어 최종 승패 출력
    public static void printPlayerFinalResult(String playerName, String result) {
        System.out.println(playerName + ": " + result);
    }
}
