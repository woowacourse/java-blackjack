package model.casino;

@FunctionalInterface
public interface HitOrHoldPolicy {
    boolean hold();
}
