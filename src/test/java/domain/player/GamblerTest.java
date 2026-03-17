package domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.player.attribute.Hand;
import domain.player.attribute.Money;
import domain.player.attribute.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamblerTest {

    @Test
    @DisplayName("카드 정상적으로 받기 테스트")
    void 카드_정상적으로_받기_테스트() {
        // given
        Name name = new Name("coco");
        Hand hand = new Hand();
        Money money = new Money("10000");
        Gambler gambler = new Gambler(name, hand, money);
        Card card = new Card("2", "하트");

        // when
        gambler.addCard(card);

        // then
        assertThat(gambler.getCardSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("카드 버스트 확인 테스트")
    void 카드_버스트_테스트() {
        // given
        Name name = new Name("coco");
        Hand hand = new Hand();
        Money money = new Money("10000");
        Gambler gambler = new Gambler(name, hand, money);

        Card card1 = new Card("2", "하트");
        Card card2 = new Card("K", "다이아몬드");
        Card card3 = new Card("K", "클로버");

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
        Name name = new Name("coco");
        Hand hand = new Hand();
        Money money = new Money("10000");
        Gambler gambler = new Gambler(name, hand, money);

        Card card1 = new Card("2", "하트");
        Card card2 = new Card("K", "다이아몬드");
        Card card3 = new Card("K", "클로버");

        // when
        gambler.addCard(card1);
        gambler.addCard(card2);
        gambler.addCard(card3);

        // then
        assertThat(gambler.getTotalScore()).isEqualTo(22);
    }
}