package view;

import domain.Card.Card;
import domain.Card.CardCollection;
import domain.game.ResultStatus;
import domain.user.Participants;
import domain.user.Playable;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    
    public static final String FINAL_STATUS_MESSAGE = "## 최종 승패";
    public static final String DEALER_GET_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    
    public static void printReadyMessage(Participants participants) {
        List<String> participantNames = participants.stream()
                .map(Playable::getName)
                .collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("딜러와 ");
        List<String> playerNames = participantNames.subList(1, participantNames.size());
        stringBuilder.append(String.join(", ", playerNames));
        stringBuilder.append("에게 2장을 나누었습니다.");
        System.out.println(stringBuilder);
    }
    
    public static void printDealerReceivedCard() {
        System.out.println(System.lineSeparator() + DEALER_GET_CARD_MESSAGE);
    }
    
    
    public static void printDealerGameResult(HashMap<ResultStatus, Integer> dealerResult) {
        System.out.println(System.lineSeparator() + FINAL_STATUS_MESSAGE);
        int winCount = dealerResult.get(ResultStatus.WIN);
        int loseCount = dealerResult.get(ResultStatus.LOSE);
        int drawCount = dealerResult.get(ResultStatus.DRAW);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("딜러: ");
        if (winCount > 0) {
            stringBuilder.append(winCount).append("승 ");
        }
        if (drawCount > 0) {
            stringBuilder.append(drawCount).append("무 ");
        }
        if (loseCount > 0) {
            stringBuilder.append(loseCount).append("패");
        }
        System.out.println(stringBuilder);
    }
    
    
    public static void printReadyParticipantsNameAndCards(Participants participants) {
        participants.forEach(
                (participant) -> OutputView.printNameAndCards(participant.getName(),
                        participant.getReadyCards()));
        System.out.println();
    }
    
    public static void printNameAndCards(String name, CardCollection cards) {
        String formattedNameAndCards = name
                + ": "
                + cards.stream()
                .map(OutputView::formatCard)
                .collect(Collectors.joining(", "));
        System.out.println(formattedNameAndCards);
    }
    
    private static String formatCard(Card card) {
        return card.getCardNumber().getName()
                + card.getCardShape().getName();
    }
    
    public static void printPlayersGameResult(final HashMap<String, ResultStatus> resultMap) {
        System.out.println(System.lineSeparator() + FINAL_STATUS_MESSAGE);
        resultMap.forEach((name, result) -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(name);
            stringBuilder.append(": ");
            if (result == ResultStatus.WIN) {
                stringBuilder.append("승");
            } else if (result == ResultStatus.LOSE) {
                stringBuilder.append("패");
            } else {
                stringBuilder.append("무");
            }
            System.out.println(stringBuilder);
        });
    }
    
    public static void printParticipantsNameCardsAndScore(final Participants participants) {
        participants.forEach((participant) -> OutputView.printNameCardsScore(participant.getName(),
                participant.getCards(), participant.getScore()));
        System.out.println();
    }
    
    public static void printNameCardsScore(String name, CardCollection cards, int score) {
        String formattedNameAndScore = name
                + ": "
                + cards.stream()
                .map(OutputView::formatCard)
                .collect(Collectors.joining(", "))
                + " - "
                + "결과: "
                + score;
        System.out.println(formattedNameAndScore);
    }
}
