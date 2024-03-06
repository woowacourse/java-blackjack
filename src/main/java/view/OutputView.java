package view;

import controller.dto.CardStatus;
import controller.dto.CardsStatus;
import java.util.ArrayList;
import java.util.List;

public class OutputView {
    public void printAfterStartGame(final CardsStatus status) {
        StringBuilder builder = new StringBuilder("딜러와 ");
        List<String> names = new ArrayList<>();
        for (CardStatus cardStatus : status.status()) {
            names.add(cardStatus.name());
        }
        builder.append(String.join(", ", names));
        builder.append("에게 2장을 나누었습니다.\n");

        for (CardStatus cardStatus : status.status()) {
            builder.append(cardStatus.getCardStatus());
        }

        System.out.println(builder);
    }

    public void printCardStatus(final CardStatus status) {
        System.out.print(status.getCardStatus());
    }

    public void printDealerPickMessage(final int count) {
        for (int index = 0; index < count; index++) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
    }
}
