package view;

import domain.*;
import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {
    public void printInitialDistribution(Players players, Dealer dealer) {
        printDistributionMessage(players);
        printDealerInitialCard(dealer);
        printPlayersInitialCards(players);
    }

    public void printParticipantCards(Participant participant) {
        System.out.println(formatParticipantCards(participant));
    }

    public void printDealerReceiveMessage() {
        System.out.println("딜러는 16이하의 한장의 카드를 더 받았습니다.");
    }

    public void printFinalResult(Participant participant) {
        String cardFormat = formatParticipantCards(participant);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cardFormat).append(" - 결과: ").append(participant.score());

        System.out.println(stringBuilder);
    }

    public void printWinOrLose(Map<String, String> outcome) {
        for (String s : outcome.keySet()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s).append(": ").append(outcome.get(s));
            System.out.println(stringBuilder);
        }
    }

    private void printDistributionMessage(Players players) {
        List<String> playerNames = players.getPlayerNames();
        StringBuilder stringBuilder = new StringBuilder();

        String names = String.join(", ", playerNames);
        stringBuilder.append("딜러와 ").append(names).append("에게 2장을 나누었습니다.");

        System.out.println(stringBuilder);
    }

    private void printDealerInitialCard(Dealer dealer) {
        Card firstCard = dealer.getFirstCard();
        String dealerCard = firstCard.name();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("딜러카드: ").append(dealerCard);

        System.out.println(stringBuilder);
    }

    private void printPlayersInitialCards(Players players) {
        for (Player player : players.getPlayers()) {
            printParticipantCards(player);
        }
    }

    private String formatParticipantCards(Participant participant) {
        List<String> cards = new ArrayList<>();

        for (Card card : participant.getAllCards()) {
            cards.add(card.name());
        }

        String cardsOnHand = String.join(", ", cards);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(participant.name() + "카드: " + cardsOnHand);

        return stringBuilder.toString();
    }

    public void printWinOrLoseMessage() {
        System.out.println("## 최종 승패");
    }
}
