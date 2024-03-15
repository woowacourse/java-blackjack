package ui;

import domain.blackjackgame.ResultStatus;
import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void printCards(Dealer dealer, Players players) {
        String result = String.join(", ", players.getPlayerNames());
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealer.getName(), result);

        System.out.println(formatCardsMessage(dealer, generateCardMessage(dealer.getFirstCard())));
        for (int i = 0; i < players.count(); i++) {
            Player player = players.findPlayerByIndex(i);
            System.out.println(generateCardMessage(player));
        }
        System.out.println();
    }

    private String generateCardMessage(Card card) {
        return card.getDenominationExpression() + card.getSuitName();
    }

    private String generateCardMessage(Participant participant) {
        String message = joinCards(participant.getAllCards());
        return formatCardsMessage(participant, message);
    }

    private String joinCards(List<Card> cards) {
        return cards.stream()
                .map(this::generateCardMessage)
                .collect(Collectors.joining(", "));
    }

    private String formatCardsMessage(Participant participant, String message) {
        return String.format("%s카드: %s", participant.getName(), message);
    }

    public void printCardsAfterHit(Player player) {
        System.out.println(generateCardMessage(player));
    }

    public void printDealerReceiveCardMessage() {
        System.out.println();
        System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public void printCardsWithScore(Dealer dealer, Players players) {
        String dealerCardsMessage = generateCardMessage(dealer);
        System.out.println(formatScoreMessage(dealerCardsMessage, dealer.calculateScore().getValue()));

        for (int i = 0; i < players.count(); i++) {
            Player player = players.findPlayerByIndex(i);
            String playerCardsMessage = generateCardMessage(player);
            System.out.println(formatScoreMessage(playerCardsMessage, player.calculateScore().getValue()));
        }
        System.out.println();
    }

    private String formatScoreMessage(String cardsMessage, int score) {
        return String.format("%s - 결과: %d", cardsMessage, score);
    }

    public void printParticipantResult(Map<ResultStatus, Integer> dealerResult,
                                       Map<Player, ResultStatus> playerResult) {
        System.out.println("## 최종 승패");
        printDealerResult(dealerResult);
        printPlayerResult(playerResult);
    }

    private void printDealerResult(Map<ResultStatus, Integer> dealerResult) {
        String dealerResultMessage = dealerResult.entrySet()
                .stream()
                .map(entry -> String.format("%d%s", entry.getValue(), entry.getKey().getName()))
                .collect(Collectors.joining(" "));
        System.out.printf("딜러: %s%n", dealerResultMessage);
    }

    private void printPlayerResult(Map<Player, ResultStatus> playerResult) {
        playerResult.forEach((key, value) -> System.out.printf("%s: %s%n", key.getName(), value.getName()));
    }
}
