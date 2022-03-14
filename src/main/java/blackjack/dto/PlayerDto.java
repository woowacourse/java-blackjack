package blackjack.dto;

import blackjack.model.player.Player;
import java.util.List;

public abstract class PlayerDto {
    protected final CardsDto cards;

    public PlayerDto(Player player) {
        this.cards = CardsDto.from(player.getCards2());
    }

    public List<String> getCards() {
        return cards.getCards();
    }

    public int getScore() {
        return cards.getScore();
    }

    public int getAddedCardCount() {
        return countOf(cards.getCards()) - 2;
    }

    private int countOf(List<String> cards) {
        return cards.size();
    }

    public abstract String getName();
}
