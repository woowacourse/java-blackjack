package domain.result;

import domain.gamer.Gamer;

@FunctionalInterface
public interface ResultPolicy {
	boolean canApply(Gamer gamer, Gamer other);
}
