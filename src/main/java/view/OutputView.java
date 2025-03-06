package view;

import domain.dto.OpenCardsResponse;
import domain.dto.PlayerResponse;
import java.util.List;

public class OutputView {
    public static void printInitialCardsDistribution(OpenCardsResponse openCardsResponse) {
        List<String> names = openCardsResponse.participants().stream()
                .map(PlayerResponse::name)
                .toList();

        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", openCardsResponse.dealer().name(), String.join(", ", names));

        System.out.printf("%s카드: %s%n",
                openCardsResponse.dealer().name(),
                String.join(", ", openCardsResponse.dealer().cards().stream()
                        .map(card -> String.format("%s%s", card.getCardNumber().getTitle(),
                                card.getCardShape().getTitle()))
                        .toList()));

        openCardsResponse.participants().forEach(OutputView::printPlayerCards);
    }

    public static void printPlayerCards(PlayerResponse player) {
        System.out.printf("%s카드: %s%n",
                player.name(),
                String.join(", ", player.cards().stream()
                        .map(card -> String.format("%s%s", card.getCardNumber().getTitle(),
                                card.getCardShape().getTitle()))
                        .toList()));
    }

    public static void printAddCardToDealer() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
