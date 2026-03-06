package domain.player;

import domain.card.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("카드 정상적으로 받기 테스트")
    void 카드_정상적으로_받기_테스트() {
        // given
        Card card = new Card("2", "하트");
        Player player = new Player("coco");

        // when
        player.addCard(card);

        // then
        Assertions.assertThat(player.getCardSize()).isEqualTo(1);
    }


    // 버스트 확인
    @Test
    @DisplayName("카드 버스트 확인 테스트")
    void 카드_버스트_테스트() {
        // given
        Card card1 = new Card("2", "하트");
        Card card2 = new Card("K", "다이아몬드");
        Card card3 = new Card("K", "클로버");

        Player player = new Player("coco");

        // when
        player.addCard(card1);
        player.addCard(card2);
        player.addCard(card3);

        // then
        Assertions.assertThat(player.isBust()).isEqualTo(true);
    }

    // 합산
    @Test
    @DisplayName("카드 값 합산")
    void 카드_값_합산_테스트() {
        // given
        Card card1 = new Card("2", "하트");
        Card card2 = new Card("K", "다이아몬드");
        Card card3 = new Card("K", "클로버");

        Player player = new Player("coco");

        // when
        player.addCard(card1);
        player.addCard(card2);
        player.addCard(card3);

        // then
        Assertions.assertThat(player.getTotalValue()).isEqualTo(22);
    }

    @Test
    @DisplayName("플레이어 이름과 카드 정보를 문자열 리스트로 반환하는지 검증")
    void 이름_카드_정보_문자열_반환_테스트() {
        // given
        Player player = new Player("pobi");
        player.addCard(new Card("K", "다이아몬드"));
        player.addCard(new Card("2", "하트"));
        player.addCard(new Card("3", "클로버"));

        Map<String, List<String>> temp = new HashMap<>();
        temp.put("pobi", List.of("K다이아몬드", "2하트", "3클로버"));

        // when
        Map<String, List<String>> nameAndCardInfo = player.getNameAndCardsInfo();

        // then
        Assertions.assertThat(nameAndCardInfo.equals(temp)).isEqualTo(true);
    }

}