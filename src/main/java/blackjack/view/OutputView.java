package blackjack.view;

import blackjack.domain.MatchResult;
import blackjack.domain.Player;
import blackjack.dto.CardDto;
import blackjack.dto.DealResultDto;
import blackjack.dto.DealerScoreDto;
import blackjack.dto.GameResultDto;
import blackjack.dto.PlayerHandDto;
import blackjack.dto.PlayerScoreDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {
    }

    public static void printDealResult(DealResultDto dealResultDto) {
        System.out.println("딜러카드: " + dealResultDto.dealerOpenCard().display());
        for (PlayerHandDto playerHand : dealResultDto.playerHands()) {
            printCurrentPlayerHand(playerHand);
        }
        System.out.println();
    }

    public static void printCurrentPlayerHand(PlayerHandDto playerHand) {
        String cards = formatCards(playerHand.cards());
        System.out.println(playerHand.name() + "카드: " + cards);
    }

    public static void printGameResult(GameResultDto gameResultDto) {
        DealerScoreDto dealer = gameResultDto.dealerResult();
        System.out.println("딜러카드: " + formatCards(dealer.cards())
                + " - 결과: " + dealer.score());

        for (PlayerScoreDto player : gameResultDto.playerResults()) {
            System.out.println(player.name() + "카드: " + formatCards(player.cards())
                    + " - 결과: " + player.score());
        }
        System.out.println();
    }

    private static String formatCards(List<CardDto> cards) {
        return cards.stream()
                .map(CardDto::display)
                .collect(Collectors.joining(", "));
    }

    public static void printDealerDrawMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public static void printFinalResult(Map<Player, MatchResult> playerFinalResult, Map<String, Long> dealerFinalResult) {
        System.out.println("## 최종 승패");
        printDealerFinalResult(dealerFinalResult.get("승"), dealerFinalResult.get("패"));
        for (Player player : playerFinalResult.keySet()) {
            printPlayerFinalResult(player.name(), playerFinalResult.get(player).getDisplay());
        }
    }

    private static void printDealerFinalResult(long dealerWinCount, long dealerLoseCount) {
        System.out.println("딜러: " + dealerWinCount + "승 " + dealerLoseCount + "패");
    }

    private static void printPlayerFinalResult(String playerName, String result) {
        System.out.println(playerName + ": " + result);
    }
}
