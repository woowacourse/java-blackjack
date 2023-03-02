package blackjack.domain;

import javax.print.attribute.standard.MediaSize;

public class Name {
    private static final String NAME_BLANK_ERROR_MESSAGE = "공백으로만 이루어진 이름은 사용할 수 없습니다.";
    private static final String BLANK = " ";
    private static final String EMPTY_STRING = "";
    private String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name.replaceAll(BLANK, EMPTY_STRING).length() == 0) {
            throw new IllegalArgumentException(NAME_BLANK_ERROR_MESSAGE);
        }
    }
}
