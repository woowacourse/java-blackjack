package blackjack.domain.participant;


import utils.StringUtils;

public class Name {
    private final String name;

    public Name(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("공백이 들어와서는 안됩니다.");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}