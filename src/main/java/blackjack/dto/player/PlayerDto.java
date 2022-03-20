package blackjack.dto.player;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Player;
import blackjack.dto.CardDto;

public class PlayerDto {

    private final String name;
    private final List<CardDto> cardDtos;
    private final int score;

    public PlayerDto(final String name, final List<Card> cards, final int score) {
        this.name = name;
        this.cardDtos = cards.stream()
                .map(CardDto::toDto)
                .collect(Collectors.toUnmodifiableList());
        this.score = score;
    }

    public static PlayerDto toDto(final Player player) {
        return new PlayerDto(player.getName(), player.getCards(), player.getScore());
    }

    public String getName() {
        return name;
    }

    public List<String> getCardNames() {
        return cardDtos.stream()
                .map(CardDto::getCardName)
                .collect(Collectors.toUnmodifiableList());
    }

    public int getScore() {
        return score;
    }

}