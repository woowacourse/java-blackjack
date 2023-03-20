package controller;

public enum DrawCardCommand {

    CARD_DRAW_AGAIN,
    CARD_DRAW_STOP;

    public boolean isDrawAgain() {
        return this == CARD_DRAW_AGAIN;
    }
}
