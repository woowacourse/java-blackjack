package blackjack.card;

public enum CardSide {

    FRONT(false, "앞면"),
    BACK(true, "뒷면");

    private final boolean isHidden;
    private final String description;

    CardSide(boolean isHidden, String description) {
        this.isHidden = isHidden;
        this.description = description;
    }

    public static CardSide getDefault() {
        return CardSide.FRONT;
    }

    public CardSide reverse() {
        if (this.equals(FRONT)) {
            return BACK;
        }
        return FRONT;
    }

    public boolean isHidden() {
        return this.isHidden;
    }

    public String getDescription() {
        return description;
    }
}
