package view;

import java.util.List;

public class InputValidator {

    private static final String BLANK_EXCEPTION_MESSAGE = "[ERROR] 하나 이상의 문자를 입력해야 합니다.";

    public void validateNotBlank(final String target) {
        if (target.isBlank()) {
            throw new IllegalArgumentException(BLANK_EXCEPTION_MESSAGE);
        }
    }

    public void validateDuplicate(List<String> participantsName) {
        int distinctSize = (int) participantsName.stream()
                .distinct()
                .count();
        boolean hasDuplicate = participantsName.size() != distinctSize;
        if(hasDuplicate){
            throw new IllegalArgumentException("[ERROR] 이름은 중복으로 입력할 수 없습니다.");
        }
    }
}
