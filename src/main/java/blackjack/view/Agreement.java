package blackjack.view;

import java.util.Arrays;

enum Agreement {

    AGREE("y"),
    DISAGREE("n");

    private String name;

    Agreement(String name) {
        this.name = name;
    }

    public static boolean isAgree(String input) {
        return Arrays.stream(values())
                .anyMatch(item -> input.equalsIgnoreCase("y") && item.name.equalsIgnoreCase(input));
    }
}