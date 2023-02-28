package blackjack.domain;
@FunctionalInterface
public interface CardPicker {
    int pickIndex(int size);
}
