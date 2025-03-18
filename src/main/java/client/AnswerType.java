package client;

public enum AnswerType {
    YES,
    NO;

    public boolean isPositive() {
        return this == YES;
    }
}
