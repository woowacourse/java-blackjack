package blackjack.view;

import blackjack.model.dto.CardDTO;
import blackjack.model.dto.PlayerDTO;
import blackjack.model.dto.PlayersDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static final String DISTRIBUTE_CARD_MSG = "%s와 %s에게 2장을 나누었습니다.%n";
    public static final String CARD_DISPLAY_MSG = "%s: %s%n";
    public static final String PLAYER_CARD_OPEN_MSG = "%s 카드: %s%n";
    public static final String DEALER_TAKE_CARD_MSG = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    public static final String DISPLAY_RESULT_MSG = "%s 카드: %s - 결과: %d%n";
    public static final String GAME_RESULT_GUIDE_MSG = "## 최종 승패";
    public static final String DEALER_GAME_RESULT_MSG = "딜러: %d%s %d%s %d%s%n";

    private OutputView() {
    }

    public static void printOpenCard(PlayerDTO dealer, PlayersDTO gamers) {
        System.out.printf("\n" + DISTRIBUTE_CARD_MSG, dealer.getName(), printGamerNames(gamers));
        System.out.printf(CARD_DISPLAY_MSG, dealer.getName(), printOpenCards(dealer.getCards()));
        for (PlayerDTO gamer : gamers.getPlayers()) {
            System.out.printf(PLAYER_CARD_OPEN_MSG, gamer.getName(), printOpenCards(gamer.getCards()));
        }
        System.out.println();
    }

    private static String printGamerNames(PlayersDTO gamers) {
        return gamers.getPlayers().stream()
                .map(PlayerDTO::getName)
                .collect(Collectors.joining(", "));
    }

    private static String printOpenCards(List<CardDTO> openCard) {
        return openCard.stream()
                .map(OutputView::getCardText)
                .collect(Collectors.joining(", "));
    }

    private static String getCardText(CardDTO card) {
        return RankSymbol.getMappingSymbol(card.getRank()) + SuitSymbol.getMappingSymbol(card.getSuit());
    }

    public static void printCard(PlayerDTO player) {
        System.out.printf(CARD_DISPLAY_MSG, player.getName(), getTakenCards(player));
    }

    private static String getTakenCards(PlayerDTO player) {
        return player.getCards().stream()
                .map(OutputView::getCardText)
                .collect(Collectors.joining(", "));
    }

    public static void printDealerTakeCardMessage() {
        System.out.println("\n" + DEALER_TAKE_CARD_MSG);
    }

    public static void printTotalScore(PlayerDTO dealer, PlayersDTO gamers) {
        System.out.printf("\n" + DISPLAY_RESULT_MSG, dealer.getName(), getTakenCards(dealer), dealer.getScore());
        for (PlayerDTO player : gamers.getPlayers()) {
            System.out.printf(DISPLAY_RESULT_MSG, player.getName(), getTakenCards(player), player.getScore());
        }
    }

    public static void printResults(Map<String, String> gamersResult) {
        printDealerResult(createDealerResult(gamersResult));
        for (String name : gamersResult.keySet()) {
            printGamerResult(name, ResultSymbol.getMappingSymbol(gamersResult.get(name)));
        }
    }

    private static Map<ResultSymbol, Integer> createDealerResult(Map<String, String> gamersResult) {
        Map<ResultSymbol, Integer> dealerResult = new HashMap<>();
        for (String result : gamersResult.values()) {
            dealerResult.merge(ResultSymbol.getOpposite(result), 1, Integer::sum);
        }
        return dealerResult;
    }

    public static void printDealerResult(Map<ResultSymbol, Integer> result) {
        System.out.println("\n" + GAME_RESULT_GUIDE_MSG);
        System.out.printf(DEALER_GAME_RESULT_MSG,
                result.getOrDefault(ResultSymbol.WIN, 0), ResultSymbol.WIN.getSymbol(),
                result.getOrDefault(ResultSymbol.DRAW, 0), ResultSymbol.DRAW.getSymbol(),
                result.getOrDefault(ResultSymbol.LOSE, 0), ResultSymbol.LOSE.getSymbol());
    }

    public static void printGamerResult(String name, String result) {
        System.out.printf(CARD_DISPLAY_MSG, name, result);
    }
}
