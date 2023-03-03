package mission;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * mission 패키지는
 * 우아한 테크코스 미니 미션입니다.
 * 추가적으로 리뷰 하실 필요는 없습니다.
 */
public class ListStudy {
    @Test
    public void mission1() {
        SimpleList<Integer> values = new SimpleArrayList<Integer>();
        values.add(1);
        values.add(2);

        Integer first = values.get(0);
        Integer second = values.get(1);

        assertThat(first).isEqualTo(1);
        assertThat(second).isEqualTo(2);
    }
}
