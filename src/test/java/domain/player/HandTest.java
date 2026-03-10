package domain.player;

import domain.card.Card;
import domain.player.attribute.Hand;
import java.util.Arrays;
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

        // when
        List<String> cardsInfo = hand.getInfo();

        // then
        Assertions.assertThat(cardsInfo.containsAll(Arrays.asList("K다이아몬드", "2하트", "3클로버")));
    }

}