package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public List<String> readNames() {
        String line = scanner.nextLine();
        validateIsBlank(line);
        List<String> names = Arrays.stream(line.split("\\s*,\\s*")).toList();
        names.forEach(this::validateIsBlank);
        return names;
    }

    public boolean readHitOrStand() {
        String input = scanner.nextLine().trim();
        validateIsBlank(input);
        if (input.equals("y")) {
            return true;
        }
        if (input.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException("[ERROR] y 혹은 n만 입력 가능합니다.");
    }

    private void validateIsBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 공백은 허용되지 않습니다");
        }
    }
}
