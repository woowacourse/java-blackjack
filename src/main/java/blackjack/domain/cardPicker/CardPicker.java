package blackjack.domain.cardPicker;

@FunctionalInterface
public interface CardPicker {
    int pickIndex(int size);
}
