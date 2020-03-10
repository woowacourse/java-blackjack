package controller;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.User;

class BlackJackGameControllerTest {
    @Test
    @DisplayName("입력된 이름들로 유저가 올바르게 생성되는지 테스트")
    void makeUsers() {
        String names = "a,b,c";
        List<User> result = new ArrayList<>();
        result.add(new User("a"));
        result.add(new User("b"));
        result.add(new User("c"));

        assertThat(BlackJackGameController.userNamesSetting(names)).isEqualTo(result);
    }
}