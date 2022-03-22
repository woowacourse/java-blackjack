package blackjack.dto;

import blackjack.domain.user.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PlayerDto {

    private final String name;
    private final Set<CardDto> cards;

    public PlayerDto(String name, Set<CardDto> cards) {
        this.name = name;
        this.cards = new HashSet<>(cards);
    }

    public String getName() {
        return name;
    }

    public Set<String> getCards() {
        return cards.stream()
                .map(CardDto::getCardInfo)
                .collect(Collectors.toUnmodifiableSet());
    }

    public static PlayerDto from(Player player) {
        Set<CardDto> collect = player.getCards().getCards().stream()
                .map(CardDto::from)
                .collect(Collectors.toUnmodifiableSet());

        return new PlayerDto(player.getName(), collect);
    }
}

