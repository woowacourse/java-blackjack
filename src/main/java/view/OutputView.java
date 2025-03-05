package view;

import domain.Card;
import domain.Nickname;
import java.util.List;

public final class OutputView {

    private OutputView() {
    }

    public static void printInitialSettingMessage(final String dealerName, final List<Nickname> playerNames,
                                                  final int cardAmount) {

        final List<String> nicknames = playerNames.stream()
                .map(Nickname::getDisplayName)
                .toList();
        String message = String.format(
                "%s와 %s에게 %d장을 나누었습니다.",
                dealerName,
                String.join(", ", nicknames),
                cardAmount
        );
        print(message);
    }

    public static void printCardsInHand(String name, List<Card> cards) {

        final List<String> cardGroup = cards.stream()
                .map(card -> card.getScore() + card.getShape().getTitle())
                .toList();
        String message = String.format("%s카드: %s", name, String.join(", ", cardGroup));
        print(message);
    }

    public static void printBustMessage(final String name) {
        print(String.format("%s는 버스트입니다.", name));
    }

    private static void print(final String message) {
        System.out.println(message);
    }
}

