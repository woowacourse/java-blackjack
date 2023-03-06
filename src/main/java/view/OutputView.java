package view;

import domain.Card;
import domain.CardShape;
import domain.GameResult;
import domain.user.Participant;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    
    public static void printDealerReceivedCard() {
        System.out.println(System.lineSeparator() + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
    
    public static void printDealerGameResult(GameResult dealerResult, int playerCount) {
        System.out.println(System.lineSeparator() + "## 최종 승패");
        int winCount = dealerResult.getWinCount();
        int loseCount = dealerResult.getLoseCount();
        int drawCount = playerCount - winCount - loseCount;
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
    
    public static void printAllParticipantsStatus(List<Participant> allPlayers) {
        allPlayers.forEach(
                (participant) -> OutputView.printNameCardsScore(participant.getName(), participant.getCards(),
                        participant.calculateScore()));
    }
    
    public static void printNameCardsScore(String name, List<Card> cards, int score) {
        String formattedNameAndScore = name
                + ": "
                + cards.stream().map(OutputView::formatCard).collect(Collectors.joining(", "))
                + " - "
                + "결과: "
                + score;
        System.out.println(formattedNameAndScore);
    }
    
    private static String formatCard(Card card) {
        return card.getCardNumber().getName()
                + formatCardShape(card.getCardShape());
    }
    
    private static String formatCardShape(CardShape cardShape) {
        if (cardShape == CardShape.SPADE) {
            return "스페이드";
        }
        if (cardShape == CardShape.CLOVER) {
            return "클로버";
        }
        if (cardShape == CardShape.HEART) {
            return "하트";
        }
        return "다이야몬드";
    }
    
    public static void printPlayerResult(String name, GameResult gameResult) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name);
        stringBuilder.append(": ");
        int winCount = gameResult.getWinCount();
        if (winCount == 1) {
            stringBuilder.append("승");
        } else if (gameResult.getLoseCount() == 1) {
            stringBuilder.append("패");
        } else {
            stringBuilder.append("무");
        }
        System.out.println(stringBuilder);
    }
    
    public static void printReadyMessage(List<Participant> participants) {
        List<String> participantNames = participants.stream().map(Participant::getName).collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("딜러와 ");
        List<String> playerNames = participantNames.subList(1, participantNames.size());
        stringBuilder.append(String.join(", ", playerNames));
        stringBuilder.append("에게 2장을 나누었습니다.");
        stringBuilder.append(System.lineSeparator());
        System.out.println(stringBuilder);
    }
    
    public static void printParticipantsNameAndCards(List<Participant> allParticipant) {
        allParticipant.forEach(
                (participant) -> OutputView.printNameAndCards(participant.getName(), participant.getReadyCards()));
    }
    
    public static void printNameAndCards(String name, List<Card> cards) {
        String formattedNameAndCards = name
                + ": "
                + cards.stream().map(OutputView::formatCard).collect(Collectors.joining(", "));
        System.out.println(formattedNameAndCards);
    }
}
