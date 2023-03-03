package blackjack.domain.cardPicker;

@FunctionalInterface
public interface CardPicker {
    int pickIndex(final int size);
}
