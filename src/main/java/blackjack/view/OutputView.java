package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;

import java.util.ArrayList;
import java.util.List;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String DELIMITER = ", ";
    private static final String SEPARATOR = ": ";

    private OutputView() {
    }

    public static void printParticipate(List<String> names) {
        System.out.println("딜러와 " + String.join(DELIMITER, names) + "에게 2장을 나누었습니다.");
    }

    public static void printCards(Participant participant) {
        List<Card> cardList = participant.getCards().cards();
        List<String> result = new ArrayList<>();
        for (Card card : cardList) {
            result.add(card.toString());
        }
        System.out.println(participant.getName() + "카드: " + String.join(DELIMITER, result));
    }

    public static void printCards(Participant participant, int showCardCount) {
        List<Card> cardList = participant.getCards().cards(showCardCount);
        List<String> result = new ArrayList<>();
        for (Card card : cardList) {
            result.add(card.toString());
        }
        System.out.println(participant.getName() + "카드: " + String.join(DELIMITER, result));
    }

    public static void printCardsWithScore(Participant participant) {
        List<Card> cardList = participant.getCards().cards();
        List<String> output = new ArrayList<>();
        for (Card card : cardList) {
            output.add(card.toString());
        }
        System.out.println(participant.getName()
                + "카드: "
                + String.join(DELIMITER, output)
                + " - 결과: "
                + participant.getScore());
    }

    public static void printOneMoreCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printDealerResult(int winCount, int drawCount, int loseCount) {
        System.out.print(NEW_LINE);
        System.out.println("## 최종 승패");
        System.out.print("딜러" + SEPARATOR);
        if (winCount > 0) {
            System.out.print(winCount + "승 ");
        }
        if (drawCount > 0) {
            System.out.print(drawCount + "무 ");
        }
        if (loseCount > 0) {
            System.out.print(loseCount + "패 ");
        }
        System.out.print(NEW_LINE);
    }

    public static void printPlayerResult(List<Player> players) {
        for (Player player : players) {
            System.out.println(player.getName() + SEPARATOR + player.getResult().getResultName());
        }
    }

    public static void printNewLine() {
        System.out.print(NEW_LINE);
    }
}
