package util;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListUtil {
	public static <T> Map<T, Integer> countFrequency(List<T> target) {
		return target.stream()
				.distinct()
				.collect(Collectors.toMap(Function.identity(),
						ele -> Collections.frequency(target, ele),
						(duplicate1, duplicate2) -> {
							throw new AssertionError("중복된 키는 있을 수 없습니다.");
						},
						TreeMap::new));
	}
}
