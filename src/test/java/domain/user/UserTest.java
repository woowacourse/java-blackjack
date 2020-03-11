package domain.user;

import domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    @Test
    @DisplayName("카드 추가 확인")
    void addCard() {
        User user = new Player("player");
        assertThat(user.getCards().size()).isEqualTo(0);

        user.addCard(Card.of("스페이드", "A"));
        assertThat(user.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("이름 확인")
    void getName() {
        User user = new Player("player");
        assertThat(user.getName()).isEqualTo("player");
    }

    @Test
    @DisplayName("점수 확인")
    void getScore() {
        User user = new Player("player");
        user.addCard(Card.of("스페이드", "A"));
        user.addCard(Card.of("하트", "10"));

        assertThat(user.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("카드 사이즈 확인")
    void getCardSize() {
        User user = new Player("player");
        assertThat(user.getCardSize()).isEqualTo(0);

        user.addCard(Card.of("스페이드", "A"));
        assertThat(user.getCardSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("카드 리스트 확인")
    void getCards() {
        User user = new Player("player");
        user.addCard(Card.of("스페이드", "A"));
        assertThat(user.getCards()).isInstanceOf(List.class);
    }

    @Test
    @DisplayName("버스트 확인")
    void isBust() {
        User user = new Player("player");
        user.addCard(Card.of("스페이드", "10"));
        user.addCard(Card.of("스페이드", "A"));
        assertThat(user.isBust()).isFalse();

        user.addCard(Card.of("하트", "10"));
        assertThat(user.isBust()).isFalse();

        user.addCard(Card.of("하트", "A"));
        assertThat(user.isBust()).isTrue();
    }

    @Test
    @DisplayName("버스트 아님 확인")
    void isNotBust() {
        User user = new Player("player");
        user.addCard(Card.of("스페이드", "10"));
        user.addCard(Card.of("스페이드", "A"));
        assertThat(user.isNotBust()).isTrue();

        user.addCard(Card.of("하트", "10"));
        assertThat(user.isNotBust()).isTrue();

        user.addCard(Card.of("하트", "A"));
        assertThat(user.isNotBust()).isFalse();
    }
}
