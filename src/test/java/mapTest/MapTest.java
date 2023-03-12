package mapTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.temporal.Temporal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MapTest {
    Map<String, String> originalMap = new LinkedHashMap<>();

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 10; i++) {
            String key = "key" + i;
            String value = "value" + i;
            originalMap.put(key, value);
        }
    }

    @Test
    @DisplayName("copyOf 순서 확인 테스트")
    void copyOf() {
        Map<String, String> copyMap = Map.copyOf(originalMap);

        assertThat(copyMap.keySet()).doesNotContainSequence(originalMap.keySet());
    }

    @Test
    @DisplayName("unmodifiableMap 순서 확인 테스트")
    void unmodifiableMap() {
        Map<String, String> copyMap = Collections.unmodifiableMap(originalMap);

        assertThat(copyMap.keySet()).containsExactlyElementsOf(originalMap.keySet());
    }
}
