package player.dto;

import card.Cards;
import player.Name;

public record SinglePlayerStatusDto(Name name, Cards cards) {

    public int getCardsScore() {
        return cards.countMaxScore();
    }
}
