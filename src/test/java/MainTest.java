import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MainTest {
    @Nested
    @DisplayName("Main 테스트")
    class MainOperationTest {
        @Test
        void 숫자_정상_계산_테스트() {
            // Given
            String[] request = {"1", "2", "3"};

            // When
            int response = Main.calculate(request);

            // Then
            assertThat(response).isEqualTo(6);
        }

        @Test
        void 문자카드_정상_계산_테스트() {
            // Given
            String[] request = {"K", "Q", "J"};

            // When
            int response = Main.calculate(request);

            // Then
            assertThat(response).isEqualTo(30);
        }

        @Test
        void 숫자_문자카드_혼합_정상_계산_테스트() {
            // Given
            String[] request = {"K", "Q", "J", "1", "2", "3"};

            // When
            int response = Main.calculate(request);

            // Then
            assertThat(response).isEqualTo(36);
        }

        @Test
        void 벗어난_숫자_범위_카드_테스트() {
            // Given
            String[] request = {"K", "Q", "J", "0", "2", "3"};

            // When
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                Main.calculate(request);
            });

            // Then
            assertThat(exception.getMessage()).isEqualTo("잘못된 범위의 카드입니다.");
        }
    }
}
