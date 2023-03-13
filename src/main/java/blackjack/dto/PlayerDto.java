package blackjack.dto;

import blackjack.domain.user.player.Player;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerDto {

    private final String name;
    private final List<CardDto> cards;
    private final int score;

    public PlayerDto(final Player player) {
        this.name = player.getName();
        this.score = player.getScore().getValue();
        this.cards = player.showCards().stream()
                .map(CardDto::new)
                .collect(Collectors.toList());
    }

    public List<CardDto> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
