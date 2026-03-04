package domain;

public class Player implements Person {

    private final String name;
    // TODO: 덱(카드 여러개) 필드 추가

    private Player(String name) {
        this.name = name;
    }

    public static Player of(String name) {
        return new Player(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public int getDeckSum() {
        return 0;
    }
}
