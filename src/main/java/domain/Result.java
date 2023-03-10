package domain;

public enum Result {

    DRAW {
        @Override
        public Result reverse() {
            return DRAW;
        }
    },
    WIN {
        @Override
        public Result reverse() {
            return LOSE;
        }
    },
    LOSE {
        @Override
        public Result reverse() {
            return WIN;
        }
    };

    public abstract Result reverse();
}
