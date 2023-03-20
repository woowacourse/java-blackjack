package domain.participant;

public class Name {

    public static final String BLANK = " ";
    private final String name;

    public Name(String name) {
        validateBlank(name);
        validateNameLength(name);
        validateNameDuplicationWithDealer(name);
        this.name = name;
    }

    public Name() {
        this.name = "딜러";
    }

    public static Name generateDealerName() {
        return new Name();
    }

    private void validateBlank(String name) {
        if (name.contains(BLANK)) {
            throw new IllegalArgumentException("[ERROR] 이름에 공백은 포함할 수 없습니다.");
        }
    }

    private void validateNameLength(String name) {
        if (name.length() < 1 || name.length() > 6) {
            throw new IllegalArgumentException("[ERROR] 이름은 1자 이상, 6자 이하만 가능합니다.");
        }
    }

    private void validateNameDuplicationWithDealer(String name) {
        if (name.equals("딜러") || name.equalsIgnoreCase("dealer")) {
            throw new IllegalArgumentException("[ERROR] 이름으로 '딜러(Dealer)'는 사용할 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }
}
