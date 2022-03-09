package blackjack.view;

import java.util.Scanner;

public class InputView {

    public static final String regex = "^[a-zA-Z]+$";

    public static String[] inputName() {
        Scanner sc = new Scanner(System.in);
        String[] names = sc.nextLine().split(",");
        validateInputName(names);
        return names;
    }

    private static void validateInputName(String[] names) {
        for (String name : names) {
            if (!name.matches(regex)) {
                throw new IllegalArgumentException("");
            }
        }
    }


}
