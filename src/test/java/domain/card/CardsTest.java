package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {
    @Test
    @DisplayName("Cards 생성")
    void create() {
        assertThat(new Cards()).isInstanceOf(Cards.class);
    }

    @Test
    @DisplayName("카드 추가")
    void addCard() {
        Cards cards = new Cards();
        assertThat(cards.getSize()).isEqualTo(0);

        cards.addCard(Card.of("스페이드", "K"));
        assertThat(cards.getSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("점수 산출")
    void getScore() {
        Cards cards = new Cards();
        cards.addCard(Card.of("스페이드", "K"));
        cards.addCard(Card.of("스페이드", "3"));
        cards.addCard(Card.of("스페이드", "A"));

        assertThat(cards.getScore()).isEqualTo(14);
    }

    @Test
    @DisplayName("블랙잭 확인")
    void isBlackJack() {
        Cards cards = new Cards();
        cards.addCard(Card.of("스페이드", "K"));
        cards.addCard(Card.of("스페이드", "A"));

        assertThat(cards.isBlackJack()).isTrue();
    }

    @Test
    @DisplayName("미만 점수 확인")
    void isLessThan() {
        Cards cards = new Cards();
        cards.addCard(Card.of("스페이드", "K"));
        cards.addCard(Card.of("스페이드", "A"));

        assertThat(cards.isLessThan(20)).isFalse();
    }

    @Test
    @DisplayName("에이스 보유 여부 확인")
    void hasAce() {
        Cards cards = new Cards();
        cards.addCard(Card.of("스페이드", "K"));
        assertThat(cards.hasAce()).isFalse();

        cards.addCard(Card.of("스페이드", "A"));
        assertThat(cards.hasAce()).isTrue();
    }

    @Test
    @DisplayName("버스트 확인")
    void isBust() {
        Cards cards = new Cards();
        cards.addCard(Card.of("스페이드", "K"));
        cards.addCard(Card.of("스페이드", "Q"));
        assertThat(cards.isBust()).isFalse();

        cards.addCard(Card.of("스페이드", "2"));
        assertThat(cards.isBust()).isTrue();

    }
}
