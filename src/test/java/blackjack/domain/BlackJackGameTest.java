package blackjack.domain;

import blackjack.domain.user.Status;
import blackjack.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackJackGameTest {
    @DisplayName("BlackJack 게임 시작 후 유저와 딜러에게 카드 2장씩 배분하는지 테스트")
    @Test
    void testStartGame() {
        //given
        User user = new User("욘");
        //when
        BlackjackGame blackJackGame = BlackjackGame.createAndFirstDraw(Arrays.asList(user));
        //then
        assertThat(blackJackGame.getDealerHandSize()).isEqualTo(2);
        assertThat(user.handSize()).isEqualTo(2);
    }

    @DisplayName("아직 게임 중인 user를 찾는다")
    @Test
    void testFindPlayingUser(){
        //given
        User user1 = new User("욘");
        User user2 = new User("웨지");
        User user3 = new User("포비");
        //when
        BlackjackGame blackJackGame = BlackjackGame.createAndFirstDraw(Arrays.asList(user1,user2,user3));
        //then
        assertThat(blackJackGame.findFirstUserByStatus(Status.PLAYING).get()).isEqualTo(user1);

        //when
        user1.stopUser();
        //then
        assertThat(blackJackGame.findFirstUserByStatus(Status.PLAYING).get()).isEqualTo(user2);
    }
}
