package fuelinjection.view;

import java.util.Scanner;

public class InputView {
    private static final String QUESTION_DISTANCE = "목적지까지 이동할 거리를 입력해주세요.";
    private static final String ERROR_NUMBER_FORMAT = "[ERROR] 정수로만 입력해주세요.";

    private final Scanner scanner = new Scanner(System.in);

    public int askDistance() throws IllegalArgumentException {
        System.out.println(QUESTION_DISTANCE);
        return readInt();

    }

    private int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NUMBER_FORMAT);
        }
    }
}
