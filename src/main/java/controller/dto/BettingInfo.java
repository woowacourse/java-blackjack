package controller.dto;

import model.participant.Name;

public record BettingInfo(
        Name name,
        Long bettingAmount
) {

    public String getNameAsString() {
        return name.getValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return name.equals(((BettingInfo) obj).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
