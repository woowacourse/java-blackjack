package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class NamesTest {

    @Test
    @DisplayName("이름을 중복으로 받으면 예외처리 한다.")
    void test_validate_duplication() {
        // then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Names("abc,abc"))
                .withMessage("중복된 이름은 입력할 수 없습니다.");
    }

    @Test
    @DisplayName("이름 수는 1~20 이어야 한다.")
    void test_validate_range_test() {
        // then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Names("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21"))
                .withMessage("이름의 수가 1이상 20이하여야 합니다.");
    }
}
