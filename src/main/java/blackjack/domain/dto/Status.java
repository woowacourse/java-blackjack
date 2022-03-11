package blackjack.domain.dto;

import blackjack.domain.player.Player;

import java.util.List;
import java.util.stream.Collectors;

public class Status {

    private final String name;
    private final List<CardDto> cardDtos;
    private final int score;

    public Status(String name, List<CardDto> cardDtos, int score) {
        this.name = name;
        this.cardDtos = cardDtos;
        this.score = score;
    }

    public static Status of(Player player) {
        return new Status(
                player.getName(),
                player.getCardsToList().stream()
                        .map(CardDto::of)
                        .collect(Collectors.toList()),
                player.getScore());
    }

    public String getName() {
        return name;
    }

    public List<CardDto> getCardDtos() {
        return cardDtos;
    }

    public int getScore() {
        return score;
    }
}
