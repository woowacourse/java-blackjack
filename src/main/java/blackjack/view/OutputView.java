package blackjack.view;

import static java.util.stream.Collectors.joining;

import blackjack.dto.CardDto;
import blackjack.dto.GameResultDto;
import blackjack.dto.GamerDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OutputView {

    private OutputView() {
    }

    public static void printGuideMessage(String message){
        System.out.println(message);
    }

    public static void printStartInfo(GamerDto dealer, List<GamerDto> players) {
        String names = players.stream()
                .map(GamerDto::getName)
                .collect(joining(", "));
        System.out.println("\n" + dealer.getName() + "와 " + names + "에게 2장씩 나누었습니다.");

        System.out.println(dealer.getName() + ": " + dealer.getCards().getValue().get(0).getValue());
        for (GamerDto player : players) {
            printPlayerCardInfo(player);
        }
        System.out.println();
    }

    public static void printPlayerCardInfo(GamerDto player) {
        String cardsInfo = player.getCards().getValue().stream()
                .map(CardDto::getValue)
                .collect(joining(", "));

        System.out.println(player.getName() + ": " + cardsInfo);
    }

    public static void printDealerDrawableInfo() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResultInfo(GamerDto dealer, List<GamerDto> players) {
        System.out.println();
        createDealerResultInfo(dealer);
        for (GamerDto player : players) {
            createPlayerResultInfo(player);
        }
    }

    private static void createPlayerResultInfo(GamerDto player) {
        String cardsInfo = player.getCards().getValue().stream()
                .map(CardDto::getValue)
                .collect(joining(", "));

        System.out.println(player.getName() + ": " + cardsInfo + " - 결과: " + player.getCards().getTotalScore());
    }

    private static void createDealerResultInfo(GamerDto dealer) {
        String cardsInfo = dealer.getCards().getValue().stream()
                .map(CardDto::getValue)
                .collect(joining(", "));

        System.out.println(dealer.getName() + ": " + cardsInfo + " - 결과: " + dealer.getCards().getTotalScore());
    }

    public static void printDealerGameResult(Map<GameResultDto, Long> result) {
        Map<GameResultDto, Long> results = new LinkedHashMap<>(result);
        String resultInfo = results.keySet().stream()
                .map(score -> result.get(score) + score.getValue())
                .collect(joining(" "));

        System.out.printf("\n## 최종 승패\n딜러: %s\n", resultInfo);
    }

    public static void printPlayerGameResult(Map<String, GameResultDto> results) {
        String resultInfo = results.keySet()
                .stream()
                .map(name -> name + ": " + results.get(name).getValue())
                .collect(joining("\n"));

        System.out.println(resultInfo);
    }
}

