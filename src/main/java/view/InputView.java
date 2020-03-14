package view;

import dto.RequestAnswerDTO;
import dto.RequestPlayerNamesDTO;
import dto.ResponsePlayerDTO;

import java.util.*;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DELIMITER = ",";

    private InputView() {
    }

    public static RequestPlayerNamesDTO inputPlayerName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        try {
            String playerName = SCANNER.nextLine();
            validatePlayerNameEmpty(playerName);
            List<String> playerNames = validateDuplicateName(playerName);
            return new RequestPlayerNamesDTO(playerNames);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputPlayerName();
        }
    }

    private static void validatePlayerNameEmpty(String playerName) {
        if (playerName == null || playerName.isEmpty()) {
            throw new IllegalArgumentException("비어있는 이름을 입력할 수 없습니다.");
        }
    }

    private static List<String> validateDuplicateName(String playerName) {
        String[] names = playerName.split(DELIMITER);
        List<String> playerNames = Arrays.asList(names);
        Set<String> duplicateNames = new HashSet<>(playerNames);
        if (duplicateNames.size() != playerNames.size()) {
            throw new IllegalArgumentException("중복된 이름을 입력하였습니다.");
        }
        return playerNames;
    }

    public static RequestAnswerDTO inputAnswer(ResponsePlayerDTO responsePlayerDTO) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)", responsePlayerDTO.getName());
        try {
            String answer = SCANNER.nextLine();
            validateAnswer(answer);
            return new RequestAnswerDTO(answer);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputAnswer(responsePlayerDTO);
        }
    }

    private static void validateAnswer(String answer) {
        if (!answer.equals("y") && !answer.equals("n")) {
            throw new IllegalArgumentException("예는 y, 아니오는 n 로 입력해주세요");
        }
    }
}

