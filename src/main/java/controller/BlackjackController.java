package controller;

import java.util.List;
import java.util.function.Supplier;

import view.InputView;

public class BlackjackController {
    private final InputView inputView;
    static final int MAX_RETRY = 10;

    public BlackjackController() {
        this.inputView = new InputView();
    }

    public void run() {
        List<String> names = inputNamse();

    }

    public List<String> inputNamse() {
        return doRetry(
                inputView::readNames,
                "[ERROR] 유효하지 입력입니다. 다시 입력해 주세요."
        );
    }

    private <T> T doRetry(Supplier<T> action, String errorMessage) {
        int retry = 0;
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                retry++;
                System.out.println(errorMessage);

                if (retry >= MAX_RETRY) {
                    throw new IllegalStateException("입력 횟수를 초과했습니다.");
                }
            }
        }
    }


}


