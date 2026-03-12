package view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import domain.result.RoundBetInfo;
import domain.player.User;

public class InputParser {

    public static List<String> parseToList(String input) {
        return Arrays.stream(input.split(","))
                .map(String::strip)
                .toList();
    }

    public static List<RoundBetInfo> parseToRoundBetInfos(List<String> names, List<Integer> betAmounts, int round) {
        return IntStream.range(0, names.size())
                .mapToObj(i -> new RoundBetInfo(round, User.from(names.get(i)), betAmounts.get(i)))
                .toList();
    }
}
