package domain;

public class Card {
    private final String value;
    private final String pattern;

    public Card(String value, String pattern) {
        // value가 범위 내에 있는지

        // pattern이 범위 내에 있는지

        this.value = value;
        this.pattern = pattern;
    }

    public int getNumber() {
        if (value.equals("K") || value.equals("Q") || value.equals("J")) {
            return 10;
        }
        if (value.equals("A")) {
            return 1;
        }
        return Integer.parseInt(value);
    }

    // value + string 합친 값 내보내는 메서드
}
