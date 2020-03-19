package domain.user;

public enum WantToHit {
    YES("y"),
    NO("n");

    private final String value;

    WantToHit(String value) {
        this.value = value;
    }

    static WantToHit findByValue(String value) {
        for (WantToHit wantToHit : values()) {
            if (wantToHit.value.equals(value)) {
                return wantToHit;
            }
        }

        throw new IllegalArgumentException("해당하는 값이 없습니다.");
    }
}
