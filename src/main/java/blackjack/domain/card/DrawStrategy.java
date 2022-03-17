package blackjack.domain.card;

@FunctionalInterface
public interface DrawStrategy {
	Card draw();
}
