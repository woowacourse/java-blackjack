package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @DisplayName("한국어로 된 결과 텍스트 반환 - 승")
    @Test
    void 한국어_승_반환() {
        Result result = Result.WIN;
        assertThat(result.getKoreanText()).isEqualTo("승");
    }

    @DisplayName("한국어로 된 결과 텍스트 반환 - 무")
    @Test
    void 한국어_무_반환() {
        Result result = Result.DRAW;
        assertThat(result.getKoreanText()).isEqualTo("무");
    }

    @DisplayName("한국어로 된 결과 텍스트 반환 - 패")
    @Test
    void 한국어_패_반환() {
        Result result = Result.LOSE;
        assertThat(result.getKoreanText()).isEqualTo("패");
    }
}