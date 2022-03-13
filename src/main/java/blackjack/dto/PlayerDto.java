package blackjack.dto;

import static java.util.stream.Collectors.toList;

import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerDto {

    private final String name;
    private final int totalScore;
    private final List<CardDto> cards;

    private PlayerDto(String name, int totalScore, List<CardDto> cards) {
        this.name = name;
        this.totalScore = totalScore;
        this.cards = new ArrayList<>(cards);
    }

    public static PlayerDto from(Player player) {
        List<CardDto> cards = player.getCards()
                .stream()
                .map(CardDto::from)
                .collect(toList());

        return new PlayerDto(player.getName(), player.getTotalScore(), cards);
    }

    public String getName() {
        return name;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public List<CardDto> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
