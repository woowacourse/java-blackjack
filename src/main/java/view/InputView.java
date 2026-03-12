package view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String readParticipantsName() {
        return scanner.nextLine();
    }

    public String readBetAmount() {
        return scanner.nextLine();
    }

    public boolean readDealDecision() {
        String answer = scanner.nextLine();
        validateDealDecisionAnswer(answer);
        return answer.equalsIgnoreCase("y");
    }

    public void validateDealDecisionAnswer(String answer) {
        validateGetMoreEmptyInput(answer);
        validateYesOrNo(answer);
    }

    static void validateGetMoreEmptyInput(String answer) {
        if (answer.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 카드 수령 여부(y/n)는 비어 있을 수 없습니다.");
        }
    }

    static void validateYesOrNo(String answer) {
        if (!(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n"))) {
            throw new IllegalArgumentException("[ERROR] 'y' 또는 'n'만 입력해 주세요.");
        }
    }
}
