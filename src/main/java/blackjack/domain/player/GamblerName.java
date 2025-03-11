package blackjack.domain.player;

import java.util.Objects;

public record GamblerName(String name) {

    public GamblerName {
        validateName(name);
    }

    private void validateName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름의 길이는 1이상입니다.");
        }
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        GamblerName gamblerName1 = (GamblerName) object;
        return Objects.equals(name, gamblerName1.name);
    }
}
