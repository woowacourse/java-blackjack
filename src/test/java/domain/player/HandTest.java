package domain.player;

import domain.card.Card;
import domain.player.attribute.Hand;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @Test
    @DisplayName("카드 정보를 문자열 리스트로 반환하는지 검증")
    void 카드_정보_문자열_반환_테스트() {
        // given
        Hand hand = new Hand();
        hand.addCard(new Card("K", "다이아몬드"));
        hand.addCard(new Card("2", "하트"));
        hand.addCard(new Card("3", "클로버"));
        List<String> comparisonValue = List.of("K다이아몬드", "2하트", "3클로버");

        // when
        List<String> cardsInfo = hand.getInfo();

        // then
        Assertions.assertThat(cardsInfo).isEqualTo(comparisonValue);
    }

    @Test
    @DisplayName("ACE를 11점으로 계산 시 21점을 초과하면, ACE를 1점으로 계산하여 합계를 구함")
    void ACE를_11점으로_계산할_때_21점을_초과하면_1점으로_계산() {
        // given
        Hand hand = new Hand();
        hand.addCard(new Card("K", "다이아몬드"));
        hand.addCard(new Card("9", "하트"));
        hand.addCard(new Card("A", "다이아몬드"));

        // when
        int handCount = hand.calculateScore();

        // then
        Assertions.assertThat(handCount).isEqualTo(20);
    }

    @Test
    @DisplayName("ACE를 11점으로 계산해도 21점을 넘지 않으면, ACE를 11점으로 유지하여 합계를 구함")
    void ACE를_11점으로_계산해도_21점을_넘지_않으면_11점으로_유지() {
        // given
        Hand hand = new Hand();
        hand.addCard(new Card("2", "다이아몬드"));
        hand.addCard(new Card("2", "하트"));
        hand.addCard(new Card("A", "다이아몬드"));

        // when
        int handCount = hand.calculateScore();

        // then
        Assertions.assertThat(handCount).isEqualTo(15);
    }

    @Test
    @DisplayName("ACE를 11점으로 계산 시 21점을 초과하지만, 1점으로 조정하여 딱 21점이 되는 경우를 검증")
    void ACE를_1점으로_조정하여_합계가_딱_21점이_되는_경우를_검증() {
        // given
        Hand hand = new Hand();
        hand.addCard(new Card("K", "다이아몬드"));
        hand.addCard(new Card("J", "하트"));
        hand.addCard(new Card("A", "다이아몬드"));

        // when
        int handCount = hand.calculateScore();

        // then
        Assertions.assertThat(handCount).isEqualTo(21);
    }
}
