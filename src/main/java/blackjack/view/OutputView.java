package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR]";

    public void printErrorMessage(String message) {
        System.out.printf("%s %s%n", ERROR_PREFIX, message);
    }

    public void printInitialDeal(Dealer dealer, List<Player> players) {
        System.out.println();
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealer.getName(), buildPlayerNameMessage(players));

        printCards(dealer.getName(), dealer.getVisibleCards());
        for (Player player : players) {
            printCards(player.getName(), player.getCards());
        }
    }

    private String buildPlayerNameMessage(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    public void printCards(String name, List<Card> cards) {
        System.out.printf("%s카드: %s%n", name, buildCardsMessage(cards));
    }

    public void printDealerHitMessage() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printAllCardsWithScore(List<Participant> participants) {
        System.out.println();
        for (Participant participant : participants) {
            System.out.println(buildCardsWithScoreMessage(participant));
        }
    }

    private String buildCardsWithScoreMessage(Participant participant) {
        return String.format("%s카드: %s - 결과: %d",
                participant.getName(),
                buildCardsMessage(participant.getCards()),
                participant.calculateScore());
    }

    private String buildCardsMessage(List<Card> cards) {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));
    }

    public void printProfits(Map<Player, Integer> playerProfits, int dealerProfit) {
        System.out.println();
        System.out.println("## 최종 수익");
        System.out.printf("딜러: %d%n", dealerProfit);
        for (Map.Entry<Player, Integer> entry : playerProfits.entrySet()) {
            System.out.printf("%s: %d%n", entry.getKey().getName(), entry.getValue());
        }
    }
}
