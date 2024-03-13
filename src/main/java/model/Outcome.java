package model;

public enum Outcome {
    WIN {
        public Outcome reverse() {
            return Outcome.LOSE;
        }
    },
    LOSE {
        public Outcome reverse() {
            return Outcome.WIN;
        }
    },
    DRAW {
        public Outcome reverse() {
            return Outcome.DRAW;
        }
    },
    ;

    public abstract Outcome reverse();
}
