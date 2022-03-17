package blackjack.dto;

import blackjack.model.Game;
import blackjack.model.player.Dealer;

public final class DealerDto extends PlayerDto {
    private final int addedCount;

    private DealerDto(String name, HandDto deck, int addedCount, int score) {
        super(name, deck, score);
        this.addedCount = addedCount;
    }

    public static DealerDto from(Game game) {
        return from(game.getDealer(), game.countCardsAddedToDealer());
    }

    public static DealerDto fromNameOf(Dealer dealer) {
        return from(dealer, 0);
    }

    private static DealerDto from(Dealer dealer, int addedCount) {
        return new DealerDto(
                dealer.getName(), HandDto.from(dealer), addedCount, dealer.getScore());
    }


    public int getAddedCount() {
        return addedCount;
    }
}
