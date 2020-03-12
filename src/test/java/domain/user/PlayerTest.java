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
        Player player = new User("user");
        assertThat(player.getCards().size()).isEqualTo(0);

        player.addCard(Card.of("스페이드", "A"));
        assertThat(player.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("이름 확인")
    void getName() {
        Player player = new User("user");
        assertThat(player.getName()).isEqualTo("user");
    }

    @Test
    @DisplayName("점수 확인")
    void getScore() {
        Player player = new User("user");
        player.addCard(Card.of("스페이드", "A"));
        player.addCard(Card.of("하트", "10"));

        assertThat(player.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("카드 사이즈 확인")
    void getCardSize() {
        Player player = new User("user");
        assertThat(player.getCardSize()).isEqualTo(0);

        player.addCard(Card.of("스페이드", "A"));
        assertThat(player.getCardSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("카드 리스트 확인")
    void getCards() {
        Player player = new User("user");
        player.addCard(Card.of("스페이드", "A"));
        assertThat(player.getCards()).isInstanceOf(List.class);
    }

    @Test
    @DisplayName("버스트 확인")
    void isBust() {
        Player player = new User("user");
        player.addCard(Card.of("스페이드", "10"));
        player.addCard(Card.of("스페이드", "A"));
        assertThat(player.isBust()).isFalse();

        player.addCard(Card.of("하트", "10"));
        assertThat(player.isBust()).isFalse();

        player.addCard(Card.of("하트", "A"));
        assertThat(player.isBust()).isTrue();
    }

    @Test
    @DisplayName("버스트 아님 확인")
    void isNotBust() {
        Player player = new User("user");
        player.addCard(Card.of("스페이드", "10"));
        player.addCard(Card.of("스페이드", "A"));
        assertThat(player.isNotBust()).isTrue();

        player.addCard(Card.of("하트", "10"));
        assertThat(player.isNotBust()).isTrue();

        player.addCard(Card.of("하트", "A"));
        assertThat(player.isNotBust()).isFalse();
    }

    @Test
    @DisplayName("첫 카드 확인")
    void getFirstCardInfo() {
        Player player = new Dealer();
        player.addCard(Card.of("스페이드", "10"));
        player.addCard(Card.of("스페이드", "A"));

        assertThat(player.getFirstCardInfo()).isEqualTo("10스페이드");
    }
}
