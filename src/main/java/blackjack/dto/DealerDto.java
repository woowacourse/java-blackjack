package blackjack.dto;

import blackjack.model.BlackJackGame;
import blackjack.model.player.Player;

public class DealerDto extends PlayerDto {
    private final String name;

    private DealerDto(Player dealer) {
        super(dealer);
        this.name = dealer.getName();
    }

    public static DealerDto from(Player dealer) {
        return new DealerDto(dealer);
    }

    public String getName() {
        return name;
    }
}
