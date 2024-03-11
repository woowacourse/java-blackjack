package model.participant;

import java.util.Objects;

public class Name {
    private final String value;

    public Name(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String inputName) {
        validateBlank(inputName);
        validateContainBlankInName(inputName);
        validateAllowedName(inputName);
    }

    private void validateBlank(String initialInput) {
        if (initialInput.isBlank()) {
            throw new IllegalArgumentException("공백으로 이루어진 값은 입력할 수 없습니다.");
        }
    }

    private void validateContainBlankInName(String inputName) {
        if (inputName.contains(" ")) {
            throw new IllegalArgumentException("중간에 공백이 포함된 값은 입력할 수 없습니다.");
        }
    }

    private void validateAllowedName(String inputName){
        if (inputName.contains("딜러")){
            throw new IllegalArgumentException("'딜러'가 포함된 사용자명은 사용할 수 없습니다.");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
