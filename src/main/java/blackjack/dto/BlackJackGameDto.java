package blackjack.dto;

import blackjack.BlackJackGame;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGameDto {
    private final PlayerDto dealer;
    private final List<PlayerDto> gamers;

    public BlackJackGameDto(BlackJackGame game) {
        this.dealer = PlayerDto.from(game.getDealer());
        this.gamers = game.getGamers().stream()
                .map(PlayerDto::from)
                .collect(Collectors.toList());
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public PlayerDto getDealer() {
        return dealer;
    }

    public List<PlayerDto> getGamers() {
        return gamers;
    }

    public int getDealerAddCardCount() {
        return dealer.getCardCountInDeck() - 2;
    }
}
