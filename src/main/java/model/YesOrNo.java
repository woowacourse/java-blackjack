package model;

import exception.IllegalYesOrNoInputException;
import java.util.Arrays;

public enum YesOrNo {
    YES("y"),
    No("n"),
    NONE("wrongAnswer");

    private String answer;

    YesOrNo(String answer) {
        this.answer = answer;
    }

    public static YesOrNo findAnswer(String inputAnswer) {
        YesOrNo answer = Arrays.stream(YesOrNo.values())
            .filter(yesOrNo -> inputAnswer.equalsIgnoreCase(yesOrNo.answer))
            .findFirst()
            .orElse(YesOrNo.NONE);

        if (answer == YesOrNo.NONE) {
            throw new IllegalYesOrNoInputException("y 또는 n를 입력해 주세요.");
        }

        return answer;
    }

    public boolean isYes() {
        return answer.equals(YesOrNo.YES.answer);
    }
}
