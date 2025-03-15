package domain.gamer;

import java.util.Objects;

public class Nickname implements Comparable<Nickname> {

    private static final String REGEX = "^[a-zA-Z가-힣]{2,5}$";
    private final String displayName;

    public Nickname(final String displayName) {
        validateNaming(displayName);
        this.displayName = displayName;
    }

    private void validateNaming(final String displayName) {
        if (displayName.matches(REGEX)) {
            return;
        }
        throw new IllegalArgumentException("이름은 영어로만 작성이 가능하며 2~5글자여야합니다.");
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final Nickname nickname)) {
            return false;
        }
        return Objects.equals(getDisplayName(), nickname.getDisplayName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getDisplayName());
    }

    @Override
    public int compareTo(final Nickname o) {
        return this.displayName.compareTo(o.getDisplayName());
    }
}
