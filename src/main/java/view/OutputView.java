package view;

import controller.dto.CardStatus;
import controller.dto.CardsStatus;
import java.util.List;

public class OutputView {
    public void printAfterStartGame(final CardsStatus status) {
        System.out.println();
        StringBuilder builder = new StringBuilder("딜러와 ");

        List<String> playerNames = status.getPlayerNames();
        builder.append(String.join(", ", playerNames));
        builder.append("에게 2장을 나누었습니다.\n");

        for (CardStatus cardStatus : status.status()) {
            builder.append(cardStatus.getCardInitStatus());
            builder.append("\n");
        }

        System.out.print(builder);
    }

    public void printCardStatus(final CardStatus status) {
        System.out.print(status.getCardInitStatus());
    }

    public void printDealerPickMessage(final int count) {
        System.out.println();
        for (int index = 0; index < count; index++) {
            System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
    }

    public void printResult(final CardsStatus currentCardsStatus, final List<Integer> scores) {
        System.out.println();
        for (int i = 0; i < scores.size(); i++) {
            CardStatus cardStatus = currentCardsStatus.status().get(i);
            System.out.println(cardStatus.getCardFinalStatus() + " - 결과: " + scores.get(i));
        }
    }
}
