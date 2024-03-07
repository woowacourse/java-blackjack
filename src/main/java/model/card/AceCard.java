package model.card;

import java.util.Objects;

public class AceCard extends Card {
    private static final int SOFT_ACE_VALUE = Number.ACE.getValue();
    private static final int HARD_ACE_VALUE = Number.ACE.getValue() - 10;

    private boolean softAce;

    public AceCard(Number number, Emblem emblem) {
        super(number, emblem);
        softAce = true;
    }

    public void changeToHardAce() {
        if (softAce) {
            softAce = false;
            return;
        }
        throw new IllegalStateException("Cannot Change Again");
    }

    public boolean isSoftAce() {
        return softAce;
    }

    @Override
    public int getCardActualValue() {
        if (softAce) {
            return SOFT_ACE_VALUE;
        }
        return HARD_ACE_VALUE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AceCard aceCard)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        return softAce == aceCard.softAce;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), softAce);
    }
}
