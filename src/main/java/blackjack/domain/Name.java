package blackjack.domain;

class Name {

    private final String name;

    public Name(String name) {
        validateNameSize(name);

        this.name = name;
    }

    private void validateNameSize(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("사용자의 이름은 공백을 제외한 1글자 이상입니다.");
        }
    }
}
