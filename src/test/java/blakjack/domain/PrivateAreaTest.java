package blakjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blakjack.domain.MockCard.CLUB_TEN;
import static blakjack.domain.MockCard.HEART_ACE;
import static org.assertj.core.api.Assertions.assertThat;

public final class PrivateAreaTest {

    @Test
    @DisplayName("카드 합계 구하기 에이스가 10으로 계산")
    void getSum() {
        final PrivateArea privateArea = new PrivateArea("칙촉");
        privateArea.addCard(CLUB_TEN);
        privateArea.addCard(HEART_ACE);

        assertThat(privateArea.getTotalScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("카드 합계 구하기 에이스가 1로 계산")
    void getSum2() {
        final PrivateArea privateArea = new PrivateArea("칙촉");
        privateArea.addCard(CLUB_TEN);
        privateArea.addCard(CLUB_TEN);
        privateArea.addCard(HEART_ACE);

        assertThat(privateArea.getTotalScore()).isEqualTo(21);
    }
}
