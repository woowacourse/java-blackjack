package view;

import domain.card.Card;
import domain.card.Hand;
import domain.result.ResultStatus;
import domain.user.GameMember;
import domain.user.Playable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    
    public static final String FINAL_STATUS_MESSAGE = "## 최종 승패";
    public static final String DEALER_GET_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    
    public static void printReadyMessage(GameMember gameMember) {
        List<String> playerNames = gameMember.getPlayers().stream()
                .map(Playable::getName)
                .collect(Collectors.toList());
        final String stringBuilder = "딜러와 "
                + String.join(", ", playerNames)
                + "에게 2장을 나누었습니다.";
        System.out.println(stringBuilder);
    }
    
    public static void printDealerReceivedCard() {
        System.out.println(System.lineSeparator() + DEALER_GET_CARD_MESSAGE);
    }
    
    
    public static void printDealerGameResult(Map<ResultStatus, Integer> dealerResult) {
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
    
    
    public static void printReadyParticipantsNameAndCards(GameMember gameMember) {
        gameMember.forEach(
                (participant) -> OutputView.printNameAndCards(participant.getName(),
                        participant.getReadyCards()));
        System.out.println();
    }
    
    public static void printNameAndCards(String name, Hand cards) {
        String formattedNameAndCards = name + ": " + cards.stream()
                .map(OutputView::formatCard)
                .collect(Collectors.joining(", "));
        System.out.println(formattedNameAndCards);
    }
    
    private static String formatCard(Card card) {
        return card.getCardNumber().getName()
                + card.getCardShape().getName();
    }
    
    public static void printPlayersGameResult(final Map<Playable, ResultStatus> resultMap) {
        System.out.println(System.lineSeparator() + FINAL_STATUS_MESSAGE);
        resultMap.forEach(OutputView::printPlayerStatusResult);
    }
    
    private static void printPlayerStatusResult(final Playable player, final ResultStatus result) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(player.getName());
        stringBuilder.append(": ");
        if (result == ResultStatus.WIN || result == ResultStatus.WIN_BLACKJACK) {
            stringBuilder.append("승");
        } else if (result == ResultStatus.LOSE) {
            stringBuilder.append("패");
        } else {
            stringBuilder.append("무");
        }
        System.out.println(stringBuilder);
    }
    
    public static void printParticipantsNameCardsAndScore(final GameMember gameMember) {
        System.out.println();
        gameMember.forEach((participant) -> OutputView.printNameCardsScore(participant.getName(),
                participant.getCards(), participant.getScore()));
        System.out.println();
    }
    
    public static void printNameCardsScore(String name, Hand cards, int score) {
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
