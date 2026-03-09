package dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.WinningStatus;
import java.util.SortedMap;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;

class FinalResultDtoTest {

    @Test
    void from() {
        // given, when
        SortedMap<String, WinningStatus> map = new TreeMap<>();
        map.put("봉구스", WinningStatus.WIN);
        map.put("시오", WinningStatus.LOSE);

        FinalResultDto finalResultDto = FinalResultDto.from(map);
        // then
        assertEquals("딜러: 1승 1패", finalResultDto.finalResults().get(0));
        assertEquals("봉구스: 승", finalResultDto.finalResults().get(1));
        assertEquals("시오: 패", finalResultDto.finalResults().get(2));
    }
}
