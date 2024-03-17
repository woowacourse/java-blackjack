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
        StringBuilder sb = new StringBuilder();
        sb.append(initialHitMessage(dealer, players));
        sb.append(dealerCardsMessage(dealer));
        players.forEach(player -> sb.append(playerCardsMessage(player)));

        System.out.println(sb);
    }

    private String initialHitMessage(Dealer dealer, List<Player> players) {
        return String.format("%n%s와 %s에게 2장을 나누었습니다.%n", dealer.getName(), playersNameMessage(players));
    }

    private String playersNameMessage(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    private String dealerCardsMessage(Dealer dealer) {
        return String.format("%s카드: %s%n", dealer.getName(), cardsMessage(dealer.getOpenCards()));
    }

    public void printPlayerCards(Player player) {
        System.out.println(playerCardsMessage(player));
    }

    private String playerCardsMessage(Player player) {
        return String.format("%s카드: %s%n", player.getName(), cardsMessage(player.getCards()));
    }

    public void printDealerHitMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printAllCardsWithScore(List<Participant> participants) {
        StringBuilder sb = new StringBuilder(System.lineSeparator());
        participants.forEach(participant -> sb.append(cardsWithScoreMessage(participant)));

        System.out.println(sb);
    }

    private String cardsWithScoreMessage(Participant participant) {
        return String.format("%s카드: %s - 결과: %d%n",
                participant.getName(),
                cardsMessage(participant.getCards()),
                participant.calculateScore());
    }

    private String cardsMessage(List<Card> cards) {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));
    }

    public void printProfit(Map<String, Double> profitResults) {
        StringBuilder sb = new StringBuilder();
        sb.append("## 최종 수익");
        sb.append(System.lineSeparator());
        profitResults.forEach((name, profit) -> sb.append(profitMessage(name, profit)));

        System.out.println(sb);
    }

    private String profitMessage(String name, Double profit) {
        if (Math.floor(profit) != profit) {
            return String.format("%s: %f%n", name, profit);
        }

        return String.format("%s: %.0f%n", name, profit);
    }
}
