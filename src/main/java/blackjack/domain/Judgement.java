package blackjack.domain;

public enum Judgement {

    WIN("승") {
        @Override
        public Judgement getOpposite() {
            return LOSE;
        }
    },
    DRAW("무") {
        @Override
        public Judgement getOpposite() {
            return DRAW;
        }
    },
    LOSE("패") {
        @Override
        public Judgement getOpposite() {
            return WIN;
        }
    };

    private final String name;

    Judgement(String name) {
        this.name = name;
    }

    abstract public Judgement getOpposite();

    public String getName() {
        return name;
    }
}
