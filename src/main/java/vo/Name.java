package vo;

import java.util.Objects;

public class Name {
    private final String name;

    private Name(String name) {
        this.name = name;
        validateNullAndBlank(name);
    }

    private void validateNullAndBlank(String name) {
        if (Objects.isNull(name) || name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름은 빈 값일 수 없습니다");
        }
    }

    public static Name from(String name) {
        return new Name(name);
    }

    public String getName() {
        return name;
    }
}
