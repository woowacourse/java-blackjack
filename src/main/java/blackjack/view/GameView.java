package blackjack.view;

import blackjack.card.Card;
import blackjack.game.GameResult;
import blackjack.user.Dealer;
import blackjack.user.Participants;
import blackjack.user.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameView {

    public void printInitialCardResults(final Participants participants) {
        printDistributeMessage(participants.getPlayerNames());

        printDealerCardResult(participants.getDealer());
        for (Player player : participants.getPlayers()) {
            printPlayerCardResult(player);
        }
    }

    private void printDistributeMessage(final List<String> playerNames) {
        System.out.println();
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", String.join(", ", playerNames));
    }

    public void printDealerCardResult(final Dealer dealer) {
        Card firstCard = dealer.openInitialCards().getFirst();
        String cardResult = firstCard.denomination().getText() + firstCard.suit().getText();
        System.out.printf("딜러카드: %s%n", cardResult);
    }

    public void printPlayerCardResult(final Player player) {
        String cardResult = parseCardToString(player.openCards());
        System.out.printf("%s카드: %s%n", player.getName(), cardResult);
    }

    public void printAddExtraCardToDealer() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받습니다.");
    }

    public void printFinalCardResults(final Participants participants) {
        printDealerFinalCardResult(participants.getDealer());
        for (Player player : participants.getPlayers()) {
            printPlayerFinalCardResult(player);
        }
    }

    private void printDealerFinalCardResult(final Dealer dealer) {
        String cardResult = parseCardToString(dealer.openCards());
        System.out.println();
        System.out.printf("딜러카드: %s - 결과: %d%n", cardResult, dealer.calculateDenominations());
    }

    private void printPlayerFinalCardResult(final Player player) {
        String cardResult = parseCardToString(player.openCards());
        System.out.printf("%s카드: %s - 결과 %d%n", player.getName(), cardResult,
            player.calculateDenominations());
    }

    public void printResultTitle() {
        System.out.println();
        System.out.println("## 최종 승패");
    }

    public void printDealerResult(final Map<GameResult, Integer> dealerGameResult) {
        StringBuilder sb = new StringBuilder();
        for (GameResult gameResult : GameResult.values()) {
            sb.append(dealerGameResult.getOrDefault(gameResult, 0));
            sb.append(gameResult.getText());
        }

        System.out.printf("딜러: %s%n", sb);
    }

    public void printPlayerResult(final String name, final GameResult gameResult) {
        System.out.printf("%s: %s%n", name, gameResult.getText());
    }

    private String parseCardToString(final List<Card> cards) {
        return cards
            .stream()
            .map(card -> card.denomination().getText() + card.suit().getText())
            .collect(Collectors.joining(", "));
    }
}
