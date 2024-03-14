package view;

import domain.card.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class RankViewTest {

    @DisplayName("모든 Rank에 대응하는 RankView가 존재한다.")
    @Test
    void mappingRankToRankViewTest() {
        assertThatCode(() -> Arrays.stream(Rank.values()).forEach(RankView::findName)).doesNotThrowAnyException();
    }
}
