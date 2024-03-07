package blackjack.domain.gamer;

public class Player extends Gamer {
    // TODO: 에러 메세지 분리
    private static final String NAME_EMPTY_ERROR = "공백이 아닌 플레이어를 입력해 주세요.";

    public Player(String name) {
        // TODO: validate 후 필드값 초기화할 수 있는 방법 있는지 찾아보기
        super(name);
        validateEmpty(name);
    }

    private void validateEmpty(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(NAME_EMPTY_ERROR);
        }
    }
}
