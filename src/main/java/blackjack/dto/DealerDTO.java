package blackjack.dto;

import blackjack.model.player.Dealer;
import blackjack.model.Game;

public final class DealerDTO extends PlayerDTO {
    private final int addedCount;

    private DealerDTO(String name, DeckDTO deck, int addedCount, int score) {
        super(name, deck, score);
        this.addedCount = addedCount;
    }

    public static DealerDTO from(Game game) {
        return from(game.getDealer(), game.countCardsAddedToDealer());
    }

    private static DealerDTO from(Dealer dealer, int addedCount) {
        return new DealerDTO(
                dealer.getName(), DeckDTO.from(dealer), addedCount, dealer.getScore());
    }

    public int getAddedCount() {
        return addedCount;
    }
}
