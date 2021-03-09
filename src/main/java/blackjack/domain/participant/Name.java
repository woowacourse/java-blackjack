package blackjack.domain.participant;


import utils.StringUtils;

public class Name {
    public static final String ILLEGAL_FORMAT_NAME_ERROR = "잘못된 형식의 이름입니다";
    private final String name;

    public Name(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException(ILLEGAL_FORMAT_NAME_ERROR);
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
