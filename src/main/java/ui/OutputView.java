package ui;

import domain.Card;
import domain.Dealer;
import domain.Participant;
import domain.Player;
import domain.Players;
import domain.ResultStatus;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void printCardHand(Dealer dealer, Players players) {
        String result = String.join(", ", players.getPlayerNames());
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealer.getName(), result);

        printDealerCardHand(dealer);
        for (int i = 0; i < players.count(); i++) {
            Player player = players.getPlayerByIndex(i);
            String participantCardHand = generateParticipantCardHand(player);
            System.out.println(participantCardHand);
        }
    }

    private String generateParticipantCardHand(Participant participant) {
        String name = participant.getName();
        String cardHandMessage = participant.getCardHand()
                .stream()
                .map(card -> generateCardMessage(card))
                .collect(Collectors.joining(", "));
        return String.format("%s카드: %s", name, cardHandMessage);
    }

    private String generateCardMessage(Card card) {
        return String.format("%s%s", card.getDenomination(), card.getEmblem());
    }

    private void printDealerCardHand(Dealer dealer) {
        String dealerName = dealer.getName();
        Card card = dealer.getFirstCardHand();
        System.out.printf("%s: %s%s%n", dealerName, card.getDenomination(), card.getEmblem());
    }

    public void printCardHandAfterHit(Player player) {
        System.out.println(generateParticipantCardHand(player));
    }

    public void printDealerReceiveCardMessage() {
        System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
    }

    public void printCardHandWithScore(Dealer dealer, Players players) {
        String dealerCardHand = generateParticipantCardHand(dealer);
        System.out.printf("%s - 결과: %d%n", dealerCardHand, dealer.calculateScore());

        for (int i = 0; i < players.count(); i++) {
            Player player = players.getPlayerByIndex(i);
            String playerCardHand = generateParticipantCardHand(player);
            System.out.printf("%s - 결과: %d%n", playerCardHand, player.calculateScore());
        }
        System.out.println();
    }

    public void printParticipantResult(Map<ResultStatus, Integer> dealerResult,
                                       Map<Player, ResultStatus> playerResult) {
        System.out.println("## 최종 승패");
        String dealerResultMessage = generateDealerResultMessage(dealerResult);
        System.out.printf("딜러: %s%n", dealerResultMessage);
        playerResult.keySet()
                .forEach(player -> System.out.printf("%s: %s%n", player.getName(), playerResult.get(player).getName()));
    }

    private String generateDealerResultMessage(Map<ResultStatus, Integer> dealerResult) {
        return dealerResult.entrySet()
                .stream()
                .map(entry -> String.format("%d%s", entry.getValue(), entry.getKey().getName()))
                .collect(Collectors.joining(" "));
    }
}
