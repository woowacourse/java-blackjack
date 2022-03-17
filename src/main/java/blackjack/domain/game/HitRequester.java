package blackjack.domain.game;

@FunctionalInterface
public interface HitRequester {
	boolean request(String name);
}
