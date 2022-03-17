package blackjack.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Mapper {

    public static Map<String, Integer> mappingUserNameAndBettingPrice(List<String> userNames, List<Integer> bettingPrices) {
        return IntStream.range(0, userNames.size())
                .boxed()
                .collect(Collectors.toMap(userNames::get, bettingPrices::get));
    }
}
