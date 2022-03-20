package blackjack.domain.card;

@FunctionalInterface
public interface CardComparator {
    boolean compare(HoldCards playerCards, HoldCards dealerCards);
}
