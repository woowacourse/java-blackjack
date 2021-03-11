package blackjack.domain;

import blackjack.domain.user.Name;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackJackGameTest {
    @DisplayName("BlackJack 게임 시작 후 유저와 딜러에게 카드 2장씩 배분하는지 테스트")
    @Test
    void testStartGame() {
        //given
        User user = new User(Name.from("욘"));
        Users users = new Users(Arrays.asList(user));
        //when
        BlackjackGame blackjackGame = BlackjackGame.createAndFirstDraw(users);
        //then
        assertThat(blackjackGame.createDealerGameResult().getDealer().handSize()).isEqualTo(2);
        assertThat(user.handSize()).isEqualTo(2);
    }

    @DisplayName("유저 이름의 리스트를 반환하는지 테스트")
    @Test
    void testGetUserName() {
        User user1 = new User(Name.from("욘"));
        User user2 = new User(Name.from("pobi"));
        User user3 = new User(Name.from("jason"));
        User user4 = new User(Name.from("웨지"));

        Users users = new Users(Arrays.asList(user1, user2, user3, user4));

        BlackjackGame blackjackGame = BlackjackGame.createAndFirstDraw(users);
        List<String> names = Arrays.asList("욘", "pobi", "jason", "웨지");

        assertThat(blackjackGame.getUserNames()).isEqualTo(names);
    }

    @DisplayName("딜러에게 카드를 한 장 더 배분하는지 테스트")
    @Test
    void testDrawCardToDealer() {
        User user1 = new User(Name.from("욘"));
        User user2 = new User(Name.from("pobi"));
        User user3 = new User(Name.from("jason"));
        User user4 = new User(Name.from("웨지"));

        Users users = new Users(Arrays.asList(user1, user2, user3, user4));

        BlackjackGame blackjackGame = BlackjackGame.createAndFirstDraw(users);
        blackjackGame.drawCardToDealer();

        assertThat(blackjackGame.createDealerGameResult().getDealer().handSize()).isEqualTo(3);
    }


    @DisplayName("유저에게 카드를 한 장 더 배분하는지 테스트")
    @Test
    void testDrawCardToUser() {
        User user1 = new User(Name.from("욘"));
        User user2 = new User(Name.from("pobi"));
        User user3 = new User(Name.from("jason"));

        Users users = new Users(Arrays.asList(user1, user2, user3));

        BlackjackGame blackjackGame = BlackjackGame.createAndFirstDraw(users);
        blackjackGame.drawCardToUser(user2);
        blackjackGame.drawCardToUser(user3);
        blackjackGame.drawCardToUser(user3);

        assertThat(blackjackGame.getUsers().get(0).handSize()).isEqualTo(2);
        assertThat(blackjackGame.getUsers().get(1).handSize()).isEqualTo(3);
        assertThat(blackjackGame.getUsers().get(2).handSize()).isEqualTo(4);
    }
}
