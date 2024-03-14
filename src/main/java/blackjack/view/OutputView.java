package blackjack.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

public class OutputView {

    public void printInitialDeal(Dealer dealer, List<Player> players) {
        System.out.println();
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealer.getName(), buildPlayerNameMessage(players));
        System.out.printf("%s카드: %s%n", dealer.getName(), buildCardsMessage(dealer.getOpenCards()));
        players.forEach(this::printCards);
        System.out.println();
    }

    private String buildPlayerNameMessage(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    public void printCards(Player player) {
        System.out.printf("%s카드: %s%n", player.getName(), buildCardsMessage(player.getCards()));
    }

    public void printDealerHitMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printAllCardsWithScore(List<Participant> participants) {
        System.out.println();
        participants.forEach(participant -> System.out.println(buildCardsWithScoreMessage(participant)));
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

    public void printProfit(Map<String, Double> profitResults) {
        System.out.println();
        System.out.println("## 최종 수익");
        profitResults.forEach((name, profit) -> System.out.printf("%s: %.0f%n", name, profit));
    }
}
