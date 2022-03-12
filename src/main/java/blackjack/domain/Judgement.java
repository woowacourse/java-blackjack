package blackjack.domain;

public enum Judgement {

    WIN("승") {
        @Override
        Judgement getOpposite() {
            return LOSE;
        }
    },
    DRAW("무") {
        @Override
        Judgement getOpposite() {
            return DRAW;
        }
    },
    LOSE("패") {
        @Override
        Judgement getOpposite() {
            return WIN;
        }
    };

    private final String name;

    Judgement(String name) {
        this.name = name;
    }

    abstract Judgement getOpposite();

    public String getName() {
        return name;
    }
}
