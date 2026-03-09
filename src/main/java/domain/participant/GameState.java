package domain.participant;

public enum GameState {

    HIT,
    STAND,
    ;

    public GameState toStand() {
        if (this == HIT){
            return STAND;
        }
        return this;
    }
}
