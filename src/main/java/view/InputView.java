package view;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import vo.Bet;

public class InputView {
    private static final Integer MAX_PLAYER_COUNT = 16;

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> readParticipantsName() {
        return parseName(scanner.nextLine());
    }

    public Bet readBetAmount() {
        return new Bet(scanner.nextLine());
    }

    public boolean readDealDecision() {
        String answer = scanner.nextLine();
        validateDealDecisionAnswer(answer);
        return answer.equalsIgnoreCase("y");
    }

    private List<String> parseName(String participantsName) {
        List<String> parsedName = Stream.of(participantsName.split(","))
                .map(String::trim)
                .toList();
        validateParticipantsNumbers(parsedName);
        validateDuplicateNames(parsedName);
        return parsedName;
    }

    static void validateDuplicateNames(List<String> names) {
        if (names.size() != new HashSet<>(names).size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 이름은 사용할 수 없습니다.");
        }
    }

    static void validateParticipantsNumbers(List<String> parsedName) {
        if (parsedName.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 최대 참가 인원은 16명 이하여야 합니다.");
        }
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
