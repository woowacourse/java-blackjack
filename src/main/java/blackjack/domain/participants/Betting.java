package blackjack.domain.participants;

public class Betting {

    private static final int PRICE_MIN = 0;

    private final int price;

    private Betting(int price) {
        this.price = price;
    }

    public static Betting valueOf(String price) {
        int number = validateNumber(price);
        validateBound(number);
        return new Betting(number);
    }

    private static void validateBound(int price) {
        if (price <= PRICE_MIN) {
            throw new IllegalArgumentException("배팅 금액은 양수입니다.");
        }
    }

    private static int validateNumber(String price) {
        try {
            return Integer.parseInt(price);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("존재할 수 없는 배팅 금액입니다.");
        }
    }
}
