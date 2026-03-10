package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.GameCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GameCardsTest {

    @Test
    @DisplayName("덱 세트 양에 따라서 카드가 정상적으로 생성되는지 검증")
    void 덱_세트_검증() {
        // given
        GameCards cards = new GameCards(1);

        // when
        int size = cards.getSize();

        // then
        assertThat(size).isEqualTo(52);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2클로버","3클로버","4클로버","5클로버","6클로버","7클로버","8클로버","9클로버","10클로버","J클로버","Q클로버","K클로버","A클로버",
            "2스페이드","3스페이드","4스페이드","5스페이드","6스페이드","7스페이드","8스페이드","9스페이드","10스페이드","J스페이드","Q스페이드","K스페이드","A스페이드",
            "2하트","3하트","4하트","5하트","6하트","7하트","8하트","9하트","10하트","J하트","Q하트","K하트","A하트",
            "2다이아몬드","3다이아몬드","4다이아몬드","5다이아몬드","6다이아몬드","7다이아몬드","8다이아몬드","9다이아몬드","10다이아몬드","J다이아몬드","Q다이아몬드","K다이아몬드","A다이아몬드"})
    @DisplayName("52개 종류의 카드 포함 여부 검증")
    void 덱_종류_검증(String string) {
        // given
        GameCards cards = new GameCards(1);

        // when
        boolean flag = cards.containsCard(string);

        // then
        assertThat(flag).isEqualTo(true);
    }
}
