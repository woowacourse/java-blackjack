package view;

import domain.ExceptionCode;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String HIT_OR_STAY_REQUEST_MESSAGE = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static final String ASK_INPUT_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String HIT_REQUEST = "y";
    private static final String STAY_REQUEST = "n";
    private static final String DELIMITER = ",";
    private static final Scanner scanner = new Scanner(System.in);

    private void askInputNames() {
        System.out.println(ASK_INPUT_NAMES_MESSAGE);
    }

    public List<String> requestNames() {
        askInputNames();
        return Arrays.asList(scanner.nextLine().split(DELIMITER, -1));
    }



    private void askDrawingCard(String name) {
        System.out.printf(HIT_OR_STAY_REQUEST_MESSAGE, name);
    }

    public String requestDrawingCard(String name) {
        askDrawingCard(name);
        String drawingCardRequest = scanner.nextLine();
        validateDrawingCardRequest(drawingCardRequest);
        return drawingCardRequest;
    }

    private void validateDrawingCardRequest(String drawingCardRequest) {
        if (!drawingCardRequest.equals(HIT_REQUEST) && !drawingCardRequest.equals(STAY_REQUEST)) {
            throw new IllegalArgumentException(ExceptionCode.INVALID_HIT_OR_STAY.getExceptionCode());
        }
    }
}
