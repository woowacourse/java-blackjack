package blackjack.view;

import blackjack.domain.result.GameBattingManager;
import blackjack.domain.card.Card;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String SEPARATOR = ", ";

    private OutputView() {
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printInitialDrawResult(Dealer dealer, List<Player> players) {
        String separatedNames = convertPlayerNames(players);
        String initialMessage = String.format(NEW_LINE + "딜러와 %s에게 2장을 나누었습니다.", separatedNames);
        String dealerCardMessage = dealerCardMessage(List.of(dealer.getFirstCard()));

        printMessage(initialMessage);
        printMessage(dealerCardMessage);
        for (Player player : players) {
            String playerCardMessage = playerCardMessage(player);
            printMessage(playerCardMessage);
        }
    }

    public static void printPlayerCard(Player player) {
        printMessage(playerCardMessage(player));
    }

    public static void printDealerHit() {
        System.out.println(NEW_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardScore(Dealer dealer, List<Player> players) {
        List<Card> dealerCard = dealer.getHandDeck();
        int dealerScore = dealer.calculateTotalScore();
        String dealerScoreMessage = String.format(dealerCardMessage(dealerCard) + " - 결과: %d", dealerScore);
        printMessage(NEW_LINE + dealerScoreMessage);

        for (Player player : players) {
            int playerScore = player.calculateTotalScore();
            String playerScoreMessage = String.format(playerCardMessage(player) + " - 결과: %d", playerScore);
            printMessage(playerScoreMessage);
        }
    }

    public static void printResult(GameBattingManager gameBattingManager) {
        System.out.println(NEW_LINE + "## 최종 수익");
        printDealerResult(gameBattingManager);
        printPlayerResult(gameBattingManager);
    }

    private static String dealerCardMessage(List<Card> cards) {
        String separatedDealerCardName = convertCardNames(cards);
        return String.format("딜러 카드: %s", separatedDealerCardName);
    }

    private static String playerCardMessage(Player player) {
        String separatedPlayerCardName = convertCardNames(player.getHandDeck());
        return String.format("%s 카드: %s", player.getPlayerName(), separatedPlayerCardName);
    }


    private static void printDealerResult(GameBattingManager gameBattingManager) {
        NumberFormat formatter = new DecimalFormat("0");
        double dealerResult = gameBattingManager.getDealerResult();

        System.out.printf("딜러 : %s" + NEW_LINE, formatter.format(dealerResult) );
    }

    private static void printPlayerResult(GameBattingManager gameBattingManager) {
        NumberFormat formatter = new DecimalFormat("0");
        Map<Player, Double> playersResult = gameBattingManager.getPlayersResult();

        for (Player player : playersResult.keySet()) {
            String playerName = player.getPlayerName();
            Double playerBattingResult = playersResult.get(player);
            System.out.printf("%s : %s" + NEW_LINE, playerName, formatter.format(playerBattingResult));
        }
    }

    private static String convertPlayerNames(List<Player> players) {
        List<String> playersName = players.stream()
                .map(Player::getPlayerName)
                .toList();
        return String.join(SEPARATOR, playersName);
    }

    private static String convertCardNames(List<Card> cards) {
        List<String> cardNames = cards.stream()
                .map(OutputView::convertCardNameFormat)
                .toList();

        return String.join(SEPARATOR, cardNames);
    }

    private static String convertCardNameFormat(Card card) {
        return RankView.convertRankName(card.getCardNumber()) + PatternView.convertPatternName(card.getCardPattern());
    }
}
