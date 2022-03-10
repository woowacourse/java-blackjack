package blackjack.domain;

import blackjack.domain.card.PlayingCard;
import java.util.List;

public class PlayerDto {
    private final String name;
    private final List<PlayingCard> playingCards;

    private PlayerDto(String name, List<PlayingCard> playingCards) {
        this.name = name;
        this.playingCards = playingCards;
    }

    public static PlayerDto from(Player player) {
        return new PlayerDto(player.getName(), player.getCards());
    }

    public String getName() {
        return name;
    }

    public List<PlayingCard> getPlayingCards() {
        return playingCards;
    }

    public int getScore() {
        return playingCards.stream()
                .mapToInt(playingCard -> playingCard.getDenomination().getScore())
                .sum();
    }
}
