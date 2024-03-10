package view;

import domain.BlackJackResult;
import domain.Card;
import domain.constant.GamerResult;
import domain.dto.GameStatus;
import domain.dto.GamerDto;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    public static void printInitialStatus(GameStatus gameStatus) {
        GamerDto dealerDto = gameStatus.getDealerDto();
        List<GamerDto> gamerDtos = gameStatus.getGamerDtos();
        System.out.println("%n딜러와 %s에게 2장을 나누었습니다.".formatted(buildGamerNames(gamerDtos)));
        System.out.println(buildDealerInitialStatus(dealerDto));
        System.out.println(buildPlayersInitialStatus(gamerDtos) + "\n");
    }

    private static String buildGamerNames(List<GamerDto> gamerDtos) {
        return gamerDtos.stream()
                .map(GamerDto::getName)
                .collect(Collectors.joining(", "));
    }

    private static String buildDealerInitialStatus(GamerDto dealerDto) {
        return String.join(": ",
                dealerDto.getName(),
                dealerDto.getCards().get(0).toString());
    }

    private static String buildPlayersInitialStatus(List<GamerDto> gamerDtos) {
        return gamerDtos.stream()
                .map(gamerDto -> buildNameCards(gamerDto.getName(), gamerDto.getCards()))
                .collect(Collectors.joining("\n"));
    }

    private static String buildNameCards(String name, List<Card> cards) {
        String cardString = cards.stream().map(Card::toString).collect(Collectors.joining(", "));
        return String.join("카드: ", name, cardString);
    }

    public static void printGamerStatus(GamerDto gamer) {
        System.out.println(buildNameCards(gamer.getName(), gamer.getCards()));
    }

    public static void printDealerTurn(int dealerDrawCount) {
        System.out.println();
        System.out.print("딜러는 16이하라 한장의 카드를 더 받았습니다.\n".repeat(dealerDrawCount));
    }

    public static void printTotalStatus(GameStatus gameStatus) {
        GamerDto dealerDto = gameStatus.getDealerDto();
        List<GamerDto> playersDto = gameStatus.getGamerDtos();
        System.out.println();
        System.out.println(buildTotalStatus(dealerDto));
        playersDto.stream().map(OutputView::buildTotalStatus)
                .forEach(System.out::println);
    }

    private static String buildTotalStatus(GamerDto gamer) {
        String nameCards = buildNameCards(gamer.getName(), gamer.getCards());
        String totalScores = buildTotalScore(gamer);
        return "%s - %s".formatted(nameCards, totalScores);
    }

    private static String buildTotalScore(GamerDto gamer) {
        return "결과: %d".formatted(gamer.getTotalScore());
    }

    public static void printGameResult(BlackJackResult gameResult) {
        System.out.println("\n## 최종 승패");
        Map<GamerResult, Integer> dealerGameResult = gameResult.getDealerResult();
        Map<String, GamerResult> playersGameResult = gameResult.getPlayersResult();

        System.out.println("딜러:" + buildDealerResult(dealerGameResult));
        playersGameResult.forEach(
                (name, result) -> System.out.println("%s: %s".formatted(name, result.getResult())));

    }

    private static String buildDealerResult(Map<GamerResult, Integer> dealerGameResult) {
        return Arrays.stream(GamerResult.values())
                .filter(dealerGameResult::containsKey)
                .map(result -> "%d%s".formatted(dealerGameResult.get(result), result.getResult()))
                .collect(Collectors.joining(" "));
    }
}
