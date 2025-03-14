package domain;

@FunctionalInterface
public interface HitOrHoldPolicy {
    boolean hold();
}
