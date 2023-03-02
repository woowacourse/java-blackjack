package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String delimiter = ",";
    private static final Scanner scanner = new Scanner(System.in);

    private void askInputNames() {
        System.out.println(ViewMessage.ASK_INPUT_NAMES.getMessage());
    }

    public List<String> requestNames() {
        askInputNames();
        List<String> names = Arrays.asList(scanner.nextLine().split(delimiter, -1));
        validate(names);
        return names;
    }

    private void validate(List<String> names) {
        validateNumberOfNames(names);
        validateNoDuplication(names);
    }

    private void validateNumberOfNames(List<String> names) {
        if (names.size() < 1 || names.size() > 7) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_OF_PLAYER.getMessage());
        }
    }

    private void validateNoDuplication(List<String> names) {
        if (names.stream().distinct().count() != names.size()) {
            throw new IllegalArgumentException(ErrorMessage.NAME_IS_DUPLICATED.getMessage());
        }
    }

    private void askDrawingCard(String name) {
        System.out.printf("%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", name);
    }

    public String requestDrawingCard(String name) {
        askDrawingCard(name);
        String drawingCardRequest = scanner.nextLine();
        validateDrawingCardRequest(drawingCardRequest);
        return drawingCardRequest;
    }

    private void validateDrawingCardRequest(String drawingCardRequest) {
        if (!drawingCardRequest.equals("y") && !drawingCardRequest.equals("n")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DRAWING_CARD_REQUEST.getMessage());
        }
    }
}
