package blackjack.dto;

import blackjack.model.player.Dealer;
import blackjack.model.Game;

public class DealerDTO extends PlayerDTO {
    private final int addedCount;

    private DealerDTO(String name, DeckDTO deck, int addedCount, int score) {
        super(name, deck, score);
        this.addedCount = addedCount;
    }

    public static DealerDTO from(Game game) {
        return from(game.getDealer());
    }

    private static DealerDTO from(Dealer dealer) {
        return new DealerDTO(
                dealer.getName(), DeckDTO.from(dealer), dealer.countAddedCards(), dealer.getScore());
    }

    public int getAddedCount() {
        return addedCount;
    }
}
