package client;

public enum AnswerType {
    YES,
    NO;

    public boolean isEqualTo(AnswerType answerType) {
        return this == answerType;
    }
}
