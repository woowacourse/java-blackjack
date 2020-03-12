package domain.user;

import domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    @Test
    @DisplayName("User 생성")
    void create() {
        assertThat(new User("이름")).isInstanceOf(User.class);
    }

    @Test
    @DisplayName("유저 승리 여부 확인 - 딜러와 동점일 경우 승리")
    void isWin() {
        Dealer dealer = new Dealer();
        User user = new User("user");

        dealer.addCard(Card.of("스페이드", "K"));
        user.addCard(Card.of("하트", "9"));
        assertThat(user.isWin(dealer)).isFalse();

        user.addCard(Card.of("하트", "10"));
        assertThat(user.isWin(dealer)).isTrue();

        dealer.addCard(Card.of("스페이드", "9"));
        assertThat(user.isWin(dealer)).isTrue();

        user.addCard(Card.of("하트", "Q"));
        assertThat(user.isWin(dealer)).isFalse();

        dealer.addCard(Card.of("스페이드", "Q"));
        assertThat(user.isWin(dealer)).isFalse();
    }
}
