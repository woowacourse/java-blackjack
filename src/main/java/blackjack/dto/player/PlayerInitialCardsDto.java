package blackjack.dto.player;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Player;
import blackjack.dto.CardDto;

public class PlayerInitialCardsDto {

    private final String playerName;
    private final List<CardDto> cardDtos;

    private PlayerInitialCardsDto(final String playerName, final List<Card> cards) {
        this.playerName = playerName;
        this.cardDtos = cards.stream()
                .map(CardDto::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    public static PlayerInitialCardsDto toDto(final Player player) {
        final String playerName = player.getName();
        final List<Card> playerCards = player.getCards();
        return new PlayerInitialCardsDto(playerName, playerCards);
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<String> getCardNames() {
        return cardDtos.stream()
                .map(CardDto::getCardName)
                .collect(Collectors.toUnmodifiableList());
    }

}
