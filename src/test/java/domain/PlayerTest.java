package domain;

import domain.participant.Hands;
import domain.participant.Name;
import domain.participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @DisplayName("이름으로 참여자를 생성한다.")
    @Test
    void createPlayerWithName() {
        Assertions.assertThatCode(() -> new Player(new Name("pobi"), Hands.createEmptyHands()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("참여자 이름이 딜러이면 예외가 발생한다.")
    void validateName() {
        final Name name = new Name("딜러");
        final Hands hands = Hands.createEmptyHands();

        Assertions.assertThatThrownBy(() -> new Player(name, hands))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 사용할 수 없는 이름입니다.");
    }
}
