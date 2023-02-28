package balckjack.domain;

class Name {

    private final String name;

    public Name(String name) {
        validateName(name);

        this.name = name;
    }

    private void validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("이름이 입력되지 않았습니다.");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름은 공백이 될 수 없습니다.");
        }
    }

}
