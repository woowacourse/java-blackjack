package controller;

import java.util.List;
import java.util.function.Supplier;

import view.InputView;

public class BlackjackController {
    private final InputView inputView;
    private static final int MAX_RETRY = 10;

    public BlackjackController() {
        this.inputView = new InputView();
    }

    public void run() {
        List<String> names = inputNames();

        for (String name : names) {
            boolean isCard = isAdditionalCard(name);
        }

    }

    private List<String> inputNames() {
        return doRetry(
                inputView::readNames
        );
    }

    private boolean isAdditionalCard(String name) {
        return doRetry(() -> {
            boolean isCard = inputView.readAdditionalCard(name);
            return isCard;
        });

    }

    private <T> T doRetry(Supplier<T> action) {
        int retry = 0;
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                retry++;
                System.out.println(e.getMessage());

                if (retry >= MAX_RETRY) {
                    throw new IllegalStateException("입력 횟수를 초과했습니다.");
                }
            }
        }
    }
}


