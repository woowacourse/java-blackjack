package view;

import domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {
    public static void printParticipants(Participants participants) {
        List<Name> names = participants.getNames();
        List<String> names2 = new ArrayList<>();
        for (Name name : names) {
            names2.add(name.getValue());
        }
        System.out.println();
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.", String.join(", ", names2));
        System.out.println();
        System.out.println();
    }

    public static void printDealerCard(Dealer dealer) {
        Name name = dealer.getName();
        List<Card> cards = dealer.getCards();
        System.out.printf("%s카드: %s", name.getValue(), cards.get(0).getRank().getName() + cards.get(0).getShape().getName());
        System.out.println();
    }

    public static void printCard(Participant participant) {
        Name name = participant.getName();
        List<Card> cards = participant.getCards();
        List<String> cardNames = new ArrayList<>();
        for (Card card : cards) {
            cardNames.add(card.getRank().getName() + card.getShape().getName());
        }
        System.out.printf("%s카드: %s", name.getValue(), String.join(", ", cardNames));
        System.out.println();
    }

    public static void printPlayerScore(Participant participant) {
        Name name = participant.getName();
        List<Card> cards = participant.getCards();
        List<String> cardNames = new ArrayList<>();
        for (Card card : cards) {
            cardNames.add(card.getRank().getName() + card.getShape().getName());
        }
        System.out.printf("%s카드: %s - 결과: %d", name.getValue(), String.join(", ", cardNames), participant.calculateScore());

        System.out.println();
    }

    public static void printFinalResult(Map<Participant, Boolean> result) {

        long dealerWinCount = result.values().stream()
                .filter(isWin -> false == isWin)
                .count();

        int totalResult = result.keySet().size();

        System.out.println();
        System.out.println("## 최종 승패");
        System.out.printf("딜러: %d승 %d패", (int)dealerWinCount, totalResult - (int)dealerWinCount);
        System.out.println();

        for (Map.Entry<Participant, Boolean> entry : result.entrySet()) {
            if (entry.getValue() == true) {
                System.out.printf("%s: 승", entry.getKey().getName().getValue());
                System.out.println();
            }
            if (entry.getValue() == false) {
                System.out.printf("%s: 패", entry.getKey().getName().getValue());
                System.out.println();
            }
        }
    }

    public static void printDealerHit() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }
}
