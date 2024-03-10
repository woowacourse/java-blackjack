package blackjackgame.domain.blackjack;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SummationCardPointTest {

    @Test
    @DisplayName("카드 포인트 합이 잘 생성되는지 검증")
    void getSummationCardPoint() {
        List<CardPoint> cardPoints = List.of(
                new CardPoint(1), new CardPoint(2),
                new CardPoint(3), new CardPoint(4)
        );
        SummationCardPoint summationCardPoint = SummationCardPoint.of(cardPoints);

        Assertions.assertThat(summationCardPoint)
                .isEqualTo(new SummationCardPoint(10));
    }
}
