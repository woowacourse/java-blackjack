package domain.result;

import domain.gamer.Gamer;

@FunctionalInterface
public interface ResultPolicy {
	boolean compare(Gamer gamer, Gamer other);
}
