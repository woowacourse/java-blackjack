package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class PlayerNameTest {

    @Test
    void 이름의_길이가_10개가_넘어가면_예외_처리한다() {
        String name = "로오오오오오오옹닉네임";

        assertThatThrownBy(() -> PlayerName.of(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름의 길이는 10이 넘을 수 없습니다.");
    }

    @Test
    void 이름이_비어있으면_예외_처리한다() {
        String name = "";

        assertThatThrownBy(() -> PlayerName.of(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 1글자 이상이어야 합니다.");
    }

    @Test
    void 이름에_숫자가_들어가면_예외처리한다() {
        String name = "숫1자이름";

        assertThatThrownBy(() -> PlayerName.of(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 한글과 영어만 가능합니다.");
    }

    @Test
    void 이름에_특수문자가_들어가면_예외처리한다() {
        String name = "숫!자이름";

        assertThatThrownBy(() -> PlayerName.of(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 한글과 영어만 가능합니다.");
    }

    @Test
    void 조건에_맞는_이름인_경우_정상_생성된다() {
        String name = "홍길동";

        PlayerName playerName = PlayerName.of(name);

        assertThat(playerName).isNotNull();
    }

    @Test
    void 이름_사이에_공백이_존재해도_된다() {
        String name = "띄어쓰기 이름";

        PlayerName playerName = PlayerName.of(name);

        assertThat(playerName).isNotNull();
    }
}