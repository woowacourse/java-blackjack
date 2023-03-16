package domain.game;

public enum GameAction {
    HIT, STAND;
    
    public static GameAction from(boolean isHit) {
        if (isHit) {
            return HIT;
        }
        return STAND;
    }
    
    public boolean isHit() {
        return this == HIT;
    }
}
