package rentcompany.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TripDistanceTest {
    @DisplayName("여행거리 VO 생성 확인 테스트")
    @Test
    void testCreateTripDistance() {
        double expectedTripDistance = 300;
        TripDistance tripDistance = new TripDistance(expectedTripDistance);

        assertThat(tripDistance).isEqualTo(new TripDistance(expectedTripDistance));
    }

    @DisplayName("예외 : 여행거리가 음수면 예외처리")
    @Test
    void testWhenNegativeTripDistance() {
        double expectedTripDistance = -300;

        assertThatThrownBy(() -> new TripDistance(expectedTripDistance))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("여행거리는 음수일 수 없습니다.");
    }
}
