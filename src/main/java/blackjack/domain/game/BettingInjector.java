package blackjack.domain.game;

@FunctionalInterface
public interface BettingInjector {
	int inject(String name);
}
