package blackjack.dto;

import blackjack.model.Game;
import blackjack.model.player.Dealer;

public final class DealerDTO extends PlayerDTO {
    private final int addedCount;

    private DealerDTO(String name, HandDTO deck, int addedCount, int score) {
        super(name, deck, score);
        this.addedCount = addedCount;
    }

    public static DealerDTO from(Game game) {
        return from(game.getDealer(), game.countCardsAddedToDealer());
    }

    public static DealerDTO fromNameOf(Dealer dealer) {
        return from(dealer, 0);
    }

    private static DealerDTO from(Dealer dealer, int addedCount) {
        return new DealerDTO(
                dealer.getName(), HandDTO.from(dealer), addedCount, dealer.getScore());
    }


    public int getAddedCount() {
        return addedCount;
    }
}
