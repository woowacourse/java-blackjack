package blackjack.view;


import blackjack.card.Card;
import blackjack.participant.GameParticipant;
import blackjack.participant.GameParticipants;
import blackjack.result.GameStatistics;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printCards(GameParticipants participants) {
        String playerNames = String.join(", ",
                participants.getPlayers().stream()
                        .map(player -> player.getNickname().getValue())
                        .toList());

        System.out.println(participants.getDealer().getNickname().getValue() + "와 " + playerNames + "에게 2장을 나누었습니다.");

        participants.getGameParticipants().forEach(participant -> System.out.println(buildParticipantCards(participant)));
        System.out.println();
    }

    public void printParticipantCards(GameParticipant participant) {
        System.out.println(buildParticipantCards(participant));
    }

    public String buildParticipantCards(GameParticipant participant) {
        return participant.getNickname().getValue() + ": " + buildCardContent(participant.getHand().getCards());
    }

    private String buildCardContent(List<Card> cards) {
        return cards.stream()
                .filter(Card::isNotHidden)
                .map(card -> String.format("%s%s", card.getRank().getDescription(), card.getSuit().getDescription()))
                .collect(Collectors.joining(", "));
    }

    public void printDealerHit() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printCardsWithSum(GameParticipants participants) {
        System.out.println();
        participants.getGameParticipants().forEach(participant ->
                printSum(buildParticipantCards(participant), participant.calculateSumOfCards()));
    }

    private void printSum(String cardContent, int sum) {
        System.out.printf("%s - 결과: %s%n", cardContent, sum);
    }

    public void printCorrectInput(String message) {
        System.out.println(message + "올바른 값을 입력해주세요.");
        System.out.println();
    }

    public void printProfit(GameParticipants participants, GameStatistics gameStatistics) {
        System.out.println();
        System.out.println("## 최종 수익");
        System.out.printf("딜러: %s %n", gameStatistics.getDealerProfit().getAmount());

        participants.getPlayers()
                .forEach(player ->
                        System.out.printf("%s: %s %n",
                                player.getNickname().getValue(),
                                gameStatistics.getProfit(player).getAmount()));
        System.out.println();
    }
}
