package view;

import dto.*;

import java.util.*;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DELIMITER = ",";
    private static final int MIN_BETTING_MONEY = 1;

    private InputView() {
    }

    public static Map<RequestPlayerNameDTO, RequestPlayerBettingMoneyDTO> inputPlayerInfo() {
        List<String> playerNames = inputPlayerName();
        List<Integer> playerBettingMoney = new ArrayList<>(playerNames.size());
        for (String name : playerNames) {
            int bettingMoney = validateInputMoney(name);
            playerBettingMoney.add(bettingMoney);
        }

        Map<RequestPlayerNameDTO, RequestPlayerBettingMoneyDTO> playerInformation = new LinkedHashMap<>();
        for (int i = 0; i < playerNames.size(); i++) {
            String targetPlayerName = playerNames.get(i);
            int targetBettingMoney = playerBettingMoney.get(i);
            playerInformation.put(new RequestPlayerNameDTO(targetPlayerName),
                    new RequestPlayerBettingMoneyDTO(targetBettingMoney));
        }
        return playerInformation;
    }

    private static List<String> inputPlayerName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        try {
            String playerName = SCANNER.nextLine();
            validatePlayerNameEmpty(playerName);
            return validateDuplicateName(playerName);
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

    private static int validateInputMoney(String name) {
        try {
            System.out.printf("\n%s의 배팅 금액은?\n", name);
            int bettingMoney = Integer.parseInt(SCANNER.nextLine());
            validateBettingMoneyRange(bettingMoney);
            return bettingMoney;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return validateInputMoney(name);
        }
    }

    private static void validateBettingMoneyRange(int bettingMoney) {
        if (bettingMoney < MIN_BETTING_MONEY) {
            throw new IllegalArgumentException("배팅 금액은 1원 이상이어야 합니다.");
        }
    }

    public static RequestAnswerDTO inputAnswer(ResponsePlayerDTO responsePlayerDTO) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)\n", responsePlayerDTO.getName());
        try {
            String answer = SCANNER.nextLine();
            validateAnswer(answer);
            return new RequestAnswerDTO(answer.toUpperCase());
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

