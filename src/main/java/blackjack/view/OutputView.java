package blackjack.view;

import blackjack.domain.vo.GameResult;
import blackjack.domain.vo.MatchResult;
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

    public static void printPlayerHand(String name, List<String> cards) {
        String cardDisplay = String.join(", ", cards);
        System.out.println(name + "카드: " + cardDisplay);
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

    public static void printFinalResult(Map<String, MatchResult> matchResults, Map<String, Long> dealerResult) {
        System.out.println("## 최종 승패");
        printDealerFinalResult(dealerResult);
        matchResults.forEach((name, result) ->
                System.out.println(name + ": " + result.getDisplay())
        );
    }

    private static void printDealerFinalResult(Map<String, Long> dealerResult) {
        System.out.println("딜러: " + dealerResult.get("승") + "승 "
                + dealerResult.get("패") + "패");
    }

    public static void printFinalResult(List<GameResult> gameResults, int dealerProfit) {
        System.out.println("## 최종 승패");
        printDealerFinalResult(dealerProfit);
        gameResults.forEach(result ->
                System.out.println(result.name() + ": " + result.profit()));
    }

    private static void printDealerFinalResult(int dealerProfit) {
        System.out.println("딜러: " + dealerProfit);
    }

    public static void printEmptyLine(){
        System.out.println();
    }
}
