package domain.user;

import domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    @DisplayName("카드 추가 확인")
    void addCard() {
        Player Player = new User("user");
        assertThat(Player.getCards().size()).isEqualTo(0);

        Player.addCard(Card.of("스페이드", "A"));
        assertThat(Player.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("이름 확인")
    void getName() {
        Player Player = new User("user");
        assertThat(Player.getName()).isEqualTo("user");
    }

    @Test
    @DisplayName("점수 확인")
    void getScore() {
        Player Player = new User("user");
        Player.addCard(Card.of("스페이드", "A"));
        Player.addCard(Card.of("하트", "10"));

        assertThat(Player.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("카드 사이즈 확인")
    void getCardSize() {
        Player Player = new User("user");
        assertThat(Player.getCardSize()).isEqualTo(0);

        Player.addCard(Card.of("스페이드", "A"));
        assertThat(Player.getCardSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("카드 리스트 확인")
    void getCards() {
        Player Player = new User("user");
        Player.addCard(Card.of("스페이드", "A"));
        assertThat(Player.getCards()).isInstanceOf(List.class);
    }

    @Test
    @DisplayName("버스트 확인")
    void isBust() {
        Player Player = new User("user");
        Player.addCard(Card.of("스페이드", "10"));
        Player.addCard(Card.of("스페이드", "A"));
        assertThat(Player.isBust()).isFalse();

        Player.addCard(Card.of("하트", "10"));
        assertThat(Player.isBust()).isFalse();

        Player.addCard(Card.of("하트", "A"));
        assertThat(Player.isBust()).isTrue();
    }

    @Test
    @DisplayName("버스트 아님 확인")
    void isNotBust() {
        Player Player = new User("user");
        Player.addCard(Card.of("스페이드", "10"));
        Player.addCard(Card.of("스페이드", "A"));
        assertThat(Player.isNotBust()).isTrue();

        Player.addCard(Card.of("하트", "10"));
        assertThat(Player.isNotBust()).isTrue();

        Player.addCard(Card.of("하트", "A"));
        assertThat(Player.isNotBust()).isFalse();
    }
}
