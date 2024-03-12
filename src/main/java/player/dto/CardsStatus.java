package player.dto;

import card.Cards;
import player.Name;

public record CardsStatus(Name name, Cards cards) {

    public int getCardsScore() {
        return cards.countMaxScore();
    }
}
