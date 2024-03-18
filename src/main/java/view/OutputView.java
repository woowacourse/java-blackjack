package view;

import domain.Card;
import domain.Player;
import domain.constant.GamerIdentifier;
import domain.dto.DealerDto;
import domain.dto.GameStatus;
import domain.dto.PlayerDto;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEALER_NAME = "딜러";

    public static void printInitialStatus(GameStatus gameStatus) {
        DealerDto dealerDto = gameStatus.getDealerDto();
        List<PlayerDto> playerDtos = gameStatus.getGamerDtos();
        System.out.printf("%n%s와 %s에게 2장을 나누었습니다.%n", DEALER_NAME, buildGamerNames(playerDtos));
        System.out.println(buildDealerInitialStatus(dealerDto));
        System.out.println(buildPlayersInitialStatus(playerDtos) + "\n");
    }

    private static String buildGamerNames(List<PlayerDto> playerDtos) {
        return playerDtos.stream()
                .map(PlayerDto::getName)
                .collect(Collectors.joining(", "));
    }

    private static String buildDealerInitialStatus(DealerDto dealerDto) {
        return String.join(": ",
                DEALER_NAME,
                dealerDto.getCards().get(0).toString());
    }

    private static String buildPlayersInitialStatus(List<PlayerDto> playerDtos) {
        return playerDtos.stream()
                .map(gamerDto -> buildNameCards(gamerDto.getName(), gamerDto.getCards()))
                .collect(Collectors.joining("\n"));
    }

    private static String buildNameCards(String name, List<Card> cards) {
        String cardString = cards.stream().map(Card::toString).collect(Collectors.joining(", "));
        return String.join("카드: ", name, cardString);
    }

    public static void printGamerStatus(PlayerDto gamer) {
        System.out.println(buildNameCards(gamer.getName(), gamer.getCards()));
    }

    public static void printDealerTurn(int dealerDrawCount) {
        System.out.println();
        System.out.printf("%s는 16이하라 한장의 카드를 더 받았습니다.%n".formatted(DEALER_NAME).repeat(dealerDrawCount));
    }

    public static void printTotalStatus(GameStatus gameStatus) {
        DealerDto dealerDto = gameStatus.getDealerDto();
        List<PlayerDto> playersDto = gameStatus.getGamerDtos();
        System.out.println();
        System.out.println(buildDealerTotalStatus(dealerDto));
        playersDto.stream().map(OutputView::buildPlayerTotalStatus)
                .forEach(System.out::println);
    }

    private static String buildDealerTotalStatus(DealerDto gamer) {
        String nameCards = buildNameCards(DEALER_NAME, gamer.getCards());
        String totalScores = buildTotalScore(gamer);
        return "%s - %s".formatted(nameCards, totalScores);
    }

    private static String buildPlayerTotalStatus(PlayerDto gamer) {
        String nameCards = buildNameCards(gamer.getName(), gamer.getCards());
        String totalScores = buildTotalScore(gamer);
        return "%s - %s".formatted(nameCards, totalScores);
    }

    private static String buildTotalScore(DealerDto gamer) {
        return "결과: %d".formatted(gamer.getTotalScore());
    }

    public static void printFinalProfit(Map<String, Double> totalProfit, List<Player> players) {
        System.out.println("\n## 최종 수익");
        printGamerProfit(DEALER_NAME, totalProfit.get(GamerIdentifier.DEALER_IDENTIFIER));
        players.stream()
                .map(Player::getName)
                .forEach(playerName -> printGamerProfit(playerName, totalProfit.get(playerName)));
    }

    private static void printGamerProfit(String gamerName, double profit) {
        DecimalFormat decimalFormat = new DecimalFormat("#");
        System.out.printf("%s: %s%n", gamerName, decimalFormat.format(profit));
    }
}
