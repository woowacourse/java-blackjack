package blackjack.dto;

import blackjack.domain.gamer.Name;
import blackjack.domain.card.Card;
import blackjack.domain.gamer.Player;
import java.util.List;

public class PlayerResponseDto {

    private final Name name;
    private final List<Card> cards;
    private final int score;

    public PlayerResponseDto(final Name name, final List<Card> cards, final int score) {
        this.name = name;
        this.cards = cards;
        this.score = score;
    }

    public Name getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }

    public static PlayerResponseDto toDto(final Player player) {
        return new PlayerResponseDto(player.getName(), player.getCards(), player.calculateScore());
    }
}
