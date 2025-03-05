package domain;

public class Nickname {

    private final String displayName;

    public Nickname(final String displayName) {
        validateNaming(displayName);
        this.displayName = displayName;
    }

    private void validateNaming(final String displayName) {
        String regex = "^[a-zA-Z가-힣]{2,5}$";
        if (displayName.matches(regex)) {
            return;
        }
        throw new IllegalArgumentException("이름은 영어로만 작성이 가능하며 2~5글자여야합니다.");
    }
}
