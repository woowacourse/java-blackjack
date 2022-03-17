package dto;

import static java.util.stream.Collectors.toList;

import domain.card.PlayingCard;
import domain.player.Player;
import java.util.List;
import util.NameMapper;

public class CardsDto {
    private final String name;
    private final List<String> cards;

    private CardsDto(String name, List<PlayingCard> cards) {
        this.name = name;
        this.cards = parsePlayingCards(cards);
    }

    private List<String> parsePlayingCards(List<PlayingCard> cards) {
        return cards.stream()
                .map(NameMapper::getCardName)
                .collect(toList());
    }

    public static CardsDto from(Player player) {
        return new CardsDto(player.getName(), player.getOpenCards());
    }

    static CardsDto forResult(Player player) {
        return new CardsDto(player.getName(), player.getHoldingCards());
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }
}
