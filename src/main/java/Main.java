public class Main {
    public static void main(String[] args) {
//        String[] deck = {"1", "2", "K"};
//        String[] deck2 = {"K", "Q", "J"};
//        String[] deck3 = {"1", "6", "K"};
//
//        System.out.println(calculate(deck));
//        System.out.println(calculate(deck2));
//        System.out.println(calculate(deck3));
    }

    public static int calculate(String[] deck) {
        int sum = 0;
        for (String card : deck) sum += modifyScore(card);

        return sum;
    }

    static int modifyScore(String card) {
        final String CARD_DELIMITER = "[KQJ]";
        final String CARD_NUMBER_BOUNDARY_DELIMITER = "[1-9]";

        if (card.matches(CARD_DELIMITER)) {
            return 10;
        }
        if (card.matches(CARD_NUMBER_BOUNDARY_DELIMITER)) {
            return Integer.parseInt(card);
        }

        throw new IllegalArgumentException("잘못된 범위의 카드입니다.");
    }
}
