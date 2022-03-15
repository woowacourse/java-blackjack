package dto;

import domain.card.PlayingCard;
import domain.player.Player;
import java.util.List;

public class CardsDto {
    private final String name;
    private final List<PlayingCard> playingPlayingCards;

    private CardsDto(String name, List<PlayingCard> playingPlayingCards) {
        this.name = name;
        this.playingPlayingCards = List.copyOf(playingPlayingCards);
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

    public List<PlayingCard> getCards() {
        return playingPlayingCards;
    }
}
