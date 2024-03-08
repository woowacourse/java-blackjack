package blackjack.view;

import blackjack.model.card.Card;
import blackjack.model.card.CardProperties;
import blackjack.model.gamer.Dealer;
import blackjack.model.gamer.Player;
import blackjack.model.result.GameResult;
import blackjack.model.result.Result;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String SEPARATOR = ", ";

    public static void printInitialDrawResult(Dealer dealer, List<Player> players) {
        String separatedNames = convertPlayerNames(players);
        System.out.printf(NEW_LINE + "딜러와 %s에게 2장을 나누었습니다." + NEW_LINE, separatedNames);

        printDealerCard(List.of(dealer.getFirstCard()));
        for (Player player : players) {
            printPlayerCard(player);
        }
    }

    public static void printDealerCard(List<Card> cards) {
        String separatedNames = convertCardNames(cards);
        System.out.printf("딜러 카드: %s" + NEW_LINE, separatedNames);
    }

    public static void printPlayerCard(Player player) {
        String separatedNames = convertCardNames(player.getHandDeck());
        System.out.printf("%s 카드: %s" + NEW_LINE, player.getName(), separatedNames);
    }

    public static void printDealerHit() {
        System.out.println(NEW_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다." + NEW_LINE);
    }

    public static void printCardScore(Dealer dealer, List<Player> players) {
        List<Card> dealerCard = dealer.getHandDeck();
        int dealerScore = dealer.calculateTotalScore();
        printDealerCard(dealerCard);
        System.out.printf("- 결과: %d", dealerScore);

        for (Player player : players) {
            printPlayerCard(player);
            System.out.printf("- 결과: %d", player.calculateTotalScore());
        }
    }

    public static void printResult(GameResult gameResult) {
        System.out.println("## 최종 승패");
        printDealerResult(gameResult);
        printPlayerResult(gameResult);
    }

    private static void printDealerResult(GameResult gameResult) {
        int dealerWins = gameResult.countDealerWins();
        int dealerLoses = gameResult.countDealerLoses();
        int dealerTies = gameResult.countDealerTies();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dealerWinFormat(dealerWins));
        stringBuilder.append(dealerLoseFormat(dealerLoses));
        stringBuilder.append(dealerTiesFormat(dealerTies));

        System.out.printf("딜러 : %s", stringBuilder);
    }

    private static void printPlayerResult(GameResult gameResult) {
        Map<Player, Result> playersResult = gameResult.getPlayersResult();

        for (Player player : playersResult.keySet()) {
            String playerName = player.getName();
            String playerResult = playersResult.get(player).getName();
            System.out.printf("%s : %s", playerName, playerResult);
        }
    }

    private static String convertPlayerNames(List<Player> players) {
        List<String> playersName = players.stream()
                .map(Player::getName)
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
        CardProperties cardProperties = card.getCardProperties();
        return cardProperties.getCardNumber().getName() + cardProperties.getCardPattern().getName();
    }

    private static String dealerWinFormat( int dealerWins) {
        if (dealerWins > 0) {
            return String.format("%d승", dealerWins);
        }

        return "";
    }

    private static String dealerLoseFormat( int dealerLoses) {
        if (dealerLoses > 0) {
            return String.format("%d패", dealerLoses);
        }

        return "";
    }

    private static String dealerTiesFormat( int dealerTies) {
        if (dealerTies > 0) {
            return String.format("%d무", dealerTies);
        }

        return "";
    }
}
