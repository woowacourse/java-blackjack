package domain;

public final class Dealer extends Participant {
    private final ResultCalculator resultCalculator;

    public Dealer() {
        super();
        this.resultCalculator = new ResultCalculator();
    }

    public int getCount(Outcome outcome) {
        return resultCalculator.getCount(outcome);
    }

    public void addResult(Outcome outcome) {
        resultCalculator.addResult(outcome);
    }
}
