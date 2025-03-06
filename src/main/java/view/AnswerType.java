package view;

public enum AnswerType {
    YES,
    NO;

    public boolean isYes() {
        return this == YES;
    }

    public boolean isNo() {
        return this == NO;
    }
}
