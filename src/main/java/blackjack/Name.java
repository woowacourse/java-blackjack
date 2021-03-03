package blackjack;


import utils.StringUtils;

public class Name {
    private final String name;

    public Name(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("잘못된 형식의 이름입니다");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
