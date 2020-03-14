package blackjack.view.dto;

import blackjack.card.domain.CardBundle;
import blackjack.player.domain.Gambler;
import blackjack.player.domain.Player;

import java.util.Arrays;
import java.util.List;
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
                .map(name -> new Gambler(CardBundle.emptyBundle(), name))
                .collect(Collectors.toList());
    }

}
