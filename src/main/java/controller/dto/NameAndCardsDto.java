package controller.dto;

import domain.Card;
import domain.Player;
import domain.User;
import java.util.List;

public record NameAndCardsDto(
        String name,
        List<Card> cards
) {

    public static NameAndCardsDto toNameAndOpenedCards(Player player) {
        return new NameAndCardsDto(player.getName(), player.getOpenedCards());
    }

    public static List<NameAndCardsDto> toNameAndOpenedCards(List<User> users) {
        return users.stream()
                .map(NameAndCardsDto::toNameAndOpenedCards)
                .toList();
    }

    public static NameAndCardsDto toNameAndCards(Player player) {
        return new NameAndCardsDto(player.getName(), player.getCards());
    }

    public static List<NameAndCardsDto> toNameAndCards(List<User> users) {
        return users.stream()
                .map(NameAndCardsDto::toNameAndCards)
                .toList();
    }
}
