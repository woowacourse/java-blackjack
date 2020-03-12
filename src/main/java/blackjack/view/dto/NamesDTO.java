package blackjack.view.dto;

import blackjack.card.domain.CardBundle;
import blackjack.player.domain.Gambler;
import blackjack.player.domain.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class NamesDTO {
    private static final String DELIMITER = ",";

    private final List<String> names;

    public NamesDTO(String inputNames) {
        validate(inputNames);
        this.names = Arrays.stream(inputNames.split(DELIMITER))
                .collect(Collectors.toList());
    }

    private void validate(String inputNames) {
        if (inputNames == null || inputNames.trim().isEmpty()) {
            throw new IllegalArgumentException(String.format("%s 비어있는 값을 입력했습니다.", inputNames));
        }
    }

    public List<Player> toPlayers() {
        return names.stream()
                .map(name -> new Gambler(new CardBundle(), name))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NamesDTO namesDTO = (NamesDTO) o;
        return Objects.equals(names, namesDTO.names);
    }

    @Override
    public int hashCode() {
        return Objects.hash(names);
    }
}
