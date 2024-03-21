package model.participant.state;

@FunctionalInterface
public interface EarningCalculator {
    long calculate(long bettingAmount);
}
