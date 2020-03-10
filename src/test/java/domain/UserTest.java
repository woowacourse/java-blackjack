package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.player.User;

public class UserTest {
    @Test
    @DisplayName("유저 이름 입력이 공란일 때 예외를 잘 뱉어내는지")
    void inputBlankName() {
        String blankName = "";
        assertThatThrownBy(() ->
            new User(blankName)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("유저 이름 입력이 null일 때 예외를 잘 뱉어내는지")
    void inputNullName() {
        assertThatThrownBy(() ->
            new User(null)
        ).isInstanceOf(IllegalArgumentException.class);
    }

}
