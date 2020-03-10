package domain.Gamer;

public enum YesOrNo {
    YES("y", true),
    NO("n", false);

    private String answerValue;
    private boolean isDrawable;

    YesOrNo(String answerValue, boolean isDrawable) {
        this.answerValue = answerValue;
        this.isDrawable = isDrawable;
    }

    public String getAnswerValue() {
        return answerValue;
    }

    public boolean isDrawable() {
        return isDrawable;
    }
}
