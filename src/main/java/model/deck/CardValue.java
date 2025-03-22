package model.deck;

public interface CardValue {
    int getDefaultValue();

    boolean hasMaxValue();

    int getMaxValue();
}
