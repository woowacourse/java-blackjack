package domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamblerTest {

    @Test
    @DisplayName("카드 정상적으로 받기 테스트")
    void 카드_정상적으로_받기_테스트() {
        // given
        Card card = new Card("2", "하트");
        Gambler gambler = new Gambler("coco");

        // when
        gambler.addCard(card);

        // then
        assertThat(gambler.getCardSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("카드 버스트 확인 테스트")
    void 카드_버스트_테스트() {
        // given
        Card card1 = new Card("2", "하트");
        Card card2 = new Card("K", "다이아몬드");
        Card card3 = new Card("K", "클로버");

        Gambler gambler = new Gambler("coco");

        // when
        gambler.addCard(card1);
        gambler.addCard(card2);
        gambler.addCard(card3);

        // then
        assertThat(gambler.isBust()).isEqualTo(true);
    }

    @Test
    @DisplayName("카드 값 합산")
    void 카드_값_합산_테스트() {
        // given
        Card card1 = new Card("2", "하트");
        Card card2 = new Card("K", "다이아몬드");
        Card card3 = new Card("K", "클로버");

        Gambler gambler = new Gambler("coco");

        // when
        gambler.addCard(card1);
        gambler.addCard(card2);
        gambler.addCard(card3);

        // then
        assertThat(gambler.getTotalScore()).isEqualTo(22);
    }

    @Test
    @DisplayName("베팅 금액이 0일 때 에러 발생 검증")
    void 베팅_금액_0_검증() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Gambler("james", 0)
        );
    }

    @Test
    @DisplayName("베팅 금액이 음수 때 에러 발생 검증")
    void 베팅_금액_음수_검증() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Gambler("james", -1)
        );
    }

    @Test
    @DisplayName("베팅 금액이 100억 넘을 때 에러 검증")
    void 베팅_금액_음수_검증() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Gambler("james", 10_000_000_000L)
        );
    }
}