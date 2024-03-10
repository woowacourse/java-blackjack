package blackjack.dto;

import blackjack.domain.gamer.Name;
import blackjack.domain.card.Card;
import blackjack.domain.gamer.Player;
import java.util.List;

public class PlayerDto {

    private final Name name;
    private final List<Card> cards;
    private final int score;

    public PlayerDto(final Name name, final List<Card> cards, final int score) {
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

    public static PlayerDto toDto(final Player player) {
        return new PlayerDto(player.getName(), player.getCards(), player.calculateScore());
    }
}
