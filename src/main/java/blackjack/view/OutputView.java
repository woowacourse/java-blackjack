package blackjack.view;

import blackjack.domain.card.CardDto;
import blackjack.domain.player.DealerDto;
import blackjack.domain.player.Player;
import blackjack.domain.player.PlayerDto;
import blackjack.domain.player.PlayersDto;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.ResultType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
    }

    public static void println(String value) {
        System.out.println(value);
    }

    public static void printGameStartMessage(DealerDto dealer, PlayersDto gamers) {
        String dealerName = dealer.getName();
        String gamerNames = gamers.getPlayerDtos().stream()
            .map(gamerDto -> gamerDto.getName())
            .collect(Collectors.joining(", "));
        System.out.printf("\n%s와 %s에게 2장을 나누었습니다.\n", dealerName, gamerNames);
        printDealerCardInfo(dealer);
        gamers.getPlayerDtos()
            .forEach(gamerDto -> printPlayerCardInfo(gamerDto));
        System.out.println();
    }

    private static void printDealerCardInfo(DealerDto dealer) {
        CardDto card = dealer.getCard();
        System.out.println(dealer.getName() + ": " + cardToString(card));
    }

    public static void printPlayerCardInfo(PlayerDto player) {
        System.out.println(playerInfoToString(player));
    }

    private static String playerInfoToString(PlayerDto player) {
        final String playerName = player.getName();
        final String playerCardInfo = player.getCardsDto().getCards()
            .stream()
            .map(card -> cardToString(card))
            .collect(Collectors.joining(", "));
        return playerName + " 카드: " + playerCardInfo;
    }

    private static String cardToString(CardDto card) {
        final String demomination = card.getDenomination().getName();
        final String shape = card.getShape().getName();
        return demomination + shape;
    }

    public static void printDealerOnemoreDrawedMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printPlayersScoreInfo(PlayerDto dealer, PlayersDto gamers) {
        System.out.println();
        System.out.printf("%s - 결과: %d\n", playerInfoToString(dealer), dealer.getScore());
        gamers.getPlayerDtos()
            .forEach(gamer -> System.out.printf("%s - 결과: %d\n", playerInfoToString(gamer), gamer.getScore()));
    }

    public static void printGameResult(GameResult gameResult) {
        System.out.println();
        System.out.println("## 최종 승패");
        List<ResultType> dealerResult = gameResult.getDealerResult();
        System.out.println("딜러: " + resultsStatisticToString(dealerResult));
        for (Entry<Player, ResultType> result : gameResult.getGamersResult().entrySet()) {
            System.out.printf("%s: %s\n", result.getKey().getName(), result.getValue().getValue());
        }
    }

    private static String resultsStatisticToString(List<ResultType> resultTypes) {
        Map<String, Long> result = resultTypes.stream()
            .collect(Collectors.groupingBy(ResultType::getValue, Collectors.counting()));
        return Arrays.stream(ResultType.values())
            .filter(resultType -> result.containsKey(resultType.getValue()))
            .map(key -> result.get(key.getValue()) + key.getValue())
            .collect(Collectors.joining(" "));
    }

    public static void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
