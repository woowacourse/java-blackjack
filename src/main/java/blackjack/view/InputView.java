package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    
    private static final String DELIMITER = ",";
    
    private final Scanner scanner = new Scanner(System.in);
    
    public String readLine() {
        return scanner.nextLine();
    }
    
    public List<String> readPlayerNames() {
        return Arrays.stream(readLine().split(DELIMITER))
                .map(String::trim)
                .toList();
    }
    
    public int readPlayerBettingAmount() {
        try {
            int bettingAmount = Integer.parseInt(readLine());
            validateBettingAmount(bettingAmount);
            return bettingAmount;
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("배팅 금액은 숫자로 입력해주세요.");
        }
    }
    
    private void validateBettingAmount(int bettingAmount) {
        if (bettingAmount < 0) {
            throw new IllegalArgumentException("배팅 금액은 1 이상의 양수여야 합니다.");
        }
        if (bettingAmount == 0) {
            throw new IllegalArgumentException("배팅 금액은 0일 수 없습니다.");
        }
    }
    
    public boolean readUserCommand() {
        return UserCommand.from(readLine()) == UserCommand.YES;
    }
}
