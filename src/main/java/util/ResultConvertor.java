package util;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.Result;

public class ResultConvertor {
    private static final Map<Result, String> mapper = new EnumMap<>(Result.class);

    static {
        initMap();
    }

    private static void initMap() {
        mapper.put(Result.WIN, "승");
        mapper.put(Result.LOSE, "패");
        mapper.put(Result.DRAW, "무");
    }

    public static List<String> convert(List<Result> results) {
        return results.stream().map(mapper::get).collect(Collectors.toList());
    }

    public static String convert(Result result) {
        return mapper.get(result);
    }
}
