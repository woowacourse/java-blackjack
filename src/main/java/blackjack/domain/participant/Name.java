package blackjack.domain.participant;

public class Name {

    private static final int MAX_NAME_LENGTH = 100;

    //이 부분도 Name name 이런 네이밍은 어떻게 바꿔보는 게 좋을까요? getName.getName 같은 방식으로 호출될 것 같아서 애매했어요
    private final String name;

    Name(final String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(final String name) {
        validateEmptyName(name);
        validateLength(name);
    }

    private void validateEmptyName(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름을 입력하지 않았습니다");
        }
    }

    private void validateLength(final String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("이름이 " + MAX_NAME_LENGTH + "글자를 초과했습니다");
        }
    }

    public String getName() {
        return name;
    }
}
