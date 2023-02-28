package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner;
    private final InputValidator validator;

    public InputView(final Scanner scanner, final InputValidator validator) {
        this.scanner = scanner;
        this.validator = validator;
    }

    public List<String> inputParticipantsName() {
        String line = scanner.nextLine();
        validator.validateNotBlank(line);

        String[] splitNames = line.split(",");

        return trimList(List.of(splitNames));
    }

    private List<String> trimList(List<String> targets) {
        List<String> trimmedList = new ArrayList<>();
        for (String target : targets) {
            trimmedList.add(target.trim());
        }

        return trimmedList;
    }
}
