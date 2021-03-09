package blackjack.domain;

import blackjack.domain.user.User;
import blackjack.domain.user.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackJackGameTest {
    @DisplayName("BlackJack 게임 시작 후 유저에게 카드 2장을 배분하는지 테스트")
    @Test
    void testStartGame() {
        //given
        User user = new User("욘", 0);
        //when
        BlackjackGame blackJackGame = BlackjackGame.create(new Users(Arrays.asList(user)));
        blackJackGame.firstDraw();
        //then
        assertThat(user.isSameHandSize(2)).isTrue();
    }

    @DisplayName("아직 게임 중인 user를 찾는다")
    @Test
    void testFindPlayingUser() {
        //given
        User firstUser = new User("욘", 0);
        User secondUser = new User("웨지", 0);
        User thirdUser = new User("포비", 0);
        //when
        BlackjackGame blackJackGame = BlackjackGame.create(new Users(Arrays.asList(firstUser, secondUser, thirdUser)));
        blackJackGame.firstDraw();
        //then
        assertThat(blackJackGame.findFirstCanPlayUser()).isEqualTo(firstUser);

        //when
        firstUser.stopUser();
        //then
        assertThat(blackJackGame.findFirstCanPlayUser()).isEqualTo(secondUser);
    }
}
