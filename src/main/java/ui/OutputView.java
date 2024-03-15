package ui;

import domain.blackjackgame.ResultStatus;
import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    public void printCards(Participants participants) {
        String playerNames = String.join(", ", participants.getPlayerNames());
        Dealer dealer = participants.getDealer();
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealer.getName(), playerNames);

        System.out.println(formatCardsMessage(dealer, generateCardMessage(dealer.getFirstCard())));
        for (Player player : participants.getPlayers()) {
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
        System.out.println(); // todo 개행 삭제
    }

    public void printCardsWithScore(Participants participants) {
        Dealer dealer = participants.getDealer();
        String dealerCardsMessage = generateCardMessage(dealer);
        System.out.println(formatScoreMessage(dealerCardsMessage, dealer.calculateScore().getValue()));

        for (Player player : participants.getPlayers()) {
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
