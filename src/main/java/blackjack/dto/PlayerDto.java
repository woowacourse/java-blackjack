package blackjack.dto;

import blackjack.model.player.Player;
import java.util.List;

public class PlayerDto {
    private final String name;
    private final DeckDto deck;

    private PlayerDto(Player player) {
        this.name = player.getName();
        this.deck = new DeckDto(player.getDeck());
    }

    public static PlayerDto from(Player player) {
        return new PlayerDto(player);
    }

    public String getName() {
        return name;
    }

    public List<CardDto> getCardsDto() {
        return deck.getCardsDto();
    }

    public CardDto getCardDtoInDeck() {
        return deck.getCardDto();
    }

    public int getCardCountInDeck() {
        return deck.getCardCount();
    }

    public int getTotalScore(){
        return deck.getTotalScore();
    }
}
