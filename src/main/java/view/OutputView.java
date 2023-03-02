package view;

import domain.participant.Player;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static void printAfterDeal(final List<? extends Player> participants) {
        System.out.println("딜러와" + participants.stream().map(it -> it.name().value()).collect(Collectors.joining(", ")) + "에게 2장을 나누었습니다");
    }

    public static void showPlayersState(final List<? extends Player> participants) {
        participants.forEach((participant) -> System.out.println(makeStateMessage(participant)));
    }

    public static void showPlayerState(final Player player) {
        System.out.println(makeStateMessage(player));
    }

    private static String makeStateMessage(final Player player) {
        return player.cardArea().cards().stream()
                .map(card -> String.format("%s  %s", card.cardValue().name(), card.cardShape().name()))
                .collect(Collectors.joining(", ", player.name().value() + "카드: ", ""));
    }

    public static void showParticipantsStateResult(final List<? extends Player> participants) {
        participants.forEach(OutputView::showPlayerStateResult);
    }

    public static void showPlayerStateResult(final Player player) {
        final String message = player.cardArea().cards().stream()
                .map(card -> String.format("%s  %s", card.cardValue().name(), card.cardShape().name()))
                .collect(Collectors.joining(", ", player.name().value() + "카드: ", String.format(" - 결과: %d", player.cardArea().calculate())));
        System.out.println(message);
    }

    public static void dealerOneMoreCard() {
        System.out.println("딜러는 16 이하라 한장의 카드를 더 받았습니다.");
    }
}
