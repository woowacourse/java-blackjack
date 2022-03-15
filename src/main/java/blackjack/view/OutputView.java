package blackjack.view;

import static java.util.stream.Collectors.joining;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.DealerResult;
import blackjack.dto.PlayerResult;
import java.util.List;
import java.util.Map;

public class OutputView {

    private OutputView() {
    }

    public static void printStart(Dealer dealer, List<Player> players) {
        System.out.println("\n딜러와 " + generateNames(players) + "에게 2장씩 나누었습니다.");
        printDealerCard(dealer);

        players.forEach(OutputView::printPlayerCard);
        System.out.println();
    }

    private static String generateNames(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(joining(", "));
    }

    private static void printDealerCard(Dealer dealer) {
        System.out.println(dealer.getName() + " 카드: " + generateCardMessage(dealer.getCards().get(0)));
    }

    public static void printPlayerCard(Player player) {
        System.out.println(player.getName() + " 카드: " + generateCardsMessage(player.getCards()));
    }

    public static void printDealerDrawable() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResult(Dealer dealer, List<Player> players) {
        System.out.println();
        printDealerResult(dealer);
        players.forEach(OutputView::printPlayerResult);
    }

    private static void printDealerResult(Dealer dealer) {
        String cardsInfo = generateCardsMessage(dealer.getCards());
        System.out.println("딜러: " + cardsInfo + " - 결과: " + dealer.getTotalScore());
    }

    private static void printPlayerResult(Player player) {
        String cardsInfo = generateCardsMessage(player.getCards());
        System.out.println(player.getName() + ": " + cardsInfo + " - 결과: " + player.getTotalScore());
    }

    private static String generateCardsMessage(List<Card> cards) {
        return cards.stream()
                .map(OutputView::generateCardMessage)
                .collect(joining(", "));
    }

    private static String generateCardMessage(Card card) {
        return card.getDenomination().getName() + card.getSuit().getName();
    }

    public static void printGameResult(DealerResult dealerResult, List<PlayerResult> playerResults) {
        System.out.println("\n## 최종 승패");
        System.out.println("딜러: " + generateDealerResultMessage(dealerResult.getResults()));
        System.out.println(generatePlayerResultsMessage(playerResults));
    }

    private static String generateDealerResultMessage(Map<GameResult, Long> results) {
        return results.keySet()
                .stream()
                .map(gameResult -> generateResultMessage(results, gameResult))
                .collect(joining(" "));
    }

    private static String generateResultMessage(Map<GameResult, Long> results, GameResult gameResult) {
        return results.get(gameResult).toString() + gameResult.getValue();
    }

    private static String generatePlayerResultsMessage(List<PlayerResult> playerResults) {
        return playerResults.stream()
                .map(playerResult -> playerResult.getName() + ": " + playerResult.getGameResult())
                .collect(joining("\n"));
    }
}

