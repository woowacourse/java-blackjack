package blackjack.domain.participant;

public class BettingMoney {
    
    private static final String ERROR_IS_NOT_DIGIT = "숫자만 입력해주세요";
    
    private final double money;
    
    private BettingMoney(double money) {
        this.money = money;
    }
    
    public static BettingMoney from(String input) {
        checkDigit(input);
        return new BettingMoney(Double.parseDouble(input));
    }
    
    private static void checkDigit(String input) {
        if (!isDigit(input)) {
            throw new IllegalArgumentException(ERROR_IS_NOT_DIGIT);
        }
    }
    
    private static boolean isDigit(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    public double getMoney() {
        return money;
    }
}
