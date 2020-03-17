package study;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class CollectorsTest {
    @Test
    void groupingBy() {
        List<String> inputs = Arrays.asList("a","b","c");
        Map<String, List<String>> actual = inputs.stream().collect(Collectors.groupingBy(input -> input, Collectors.toList()));
        assertThat(actual).isEqualTo(new HashMap<String, List<String>>() {{
            put("a", Collections.singletonList("a"));
            put("b", Collections.singletonList("b"));
            put("c", Collections.singletonList("c"));
        }});
    }

    @Test
    void groupingByEqualsA() {
        List<String> inputs = Arrays.asList("a","b","c");
        Map<Boolean, List<String>> actual = inputs.stream().collect(Collectors.groupingBy(input -> input.equals("a"), Collectors.toList()));
        assertThat(actual).isEqualTo(new HashMap<Boolean, List<String>>() {{
            put(true, Collections.singletonList("a"));
            put(false, Arrays.asList("b", "c"));
        }});
    }
}
