package controller.dto;

import domain.Card;
import domain.Player;
import java.util.List;

public record NameAndCards(
        String name,
        List<Card> cards
) {

    public static NameAndCards toNameAndCards(Player player) {
        return new NameAndCards(player.getName(), player.getCards());
    }

    public static NameAndCards toNameAndOpenedCards(Player player) {
        return new NameAndCards(player.getName(), player.getOpenedCards());
    }

    public static List<NameAndCards> toNameAndCards(List<Player> players) {
        return players.stream()
                .map(NameAndCards::toNameAndCards)
                .toList();
    }
}
