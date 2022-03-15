package dto;

import domain.player.Player;
import java.util.Objects;

public class NameDto {
    private static final String ERROR_FOR_NULL_OR_EMPTY_NAME = "[ERROR] 이름은 빈 값일 수 없습니다.";

    private final String name;
    private final boolean isDealer;

    private NameDto(String name, boolean isDealer) {
        this.name = name;
        this.isDealer = isDealer;

        if (Objects.isNull(this.name) || this.name.isBlank()) {
            throw new IllegalArgumentException(ERROR_FOR_NULL_OR_EMPTY_NAME);
        }
    }

    public static NameDto from(Player player) {
        return new NameDto(player.getName(), player.isDealer());
    }

    public String getName() {
        return name;
    }

    public boolean isDealer() {
        return isDealer;
    }

    public boolean isGambler() {
        return !isDealer;
    }
}
