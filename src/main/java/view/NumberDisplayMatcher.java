package view;

import domain.card.Number;

import java.util.*;

public class NumberDisplayMatcher {
    private static final Map<Number, String> messagesMapper = new EnumMap<>(Number.class);

    static {
        List<String> messages = new ArrayList<>(List.of("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "K", "Q", "J"));
        for (Number number : Number.values()) {
            messagesMapper.put(number, messages.remove(0));
        }
    }

    private NumberDisplayMatcher() {}

    public static String displayMessage(Number number) {
        return messagesMapper.get(number);
    }
}
