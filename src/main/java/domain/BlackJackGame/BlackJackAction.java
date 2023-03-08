package domain.BlackJackGame;

public enum BlackJackAction {
    HIT, STAND;
    
    public static BlackJackAction of(boolean isHit) {
        if (isHit) {
            return HIT;
        }
        return STAND;
    }
}
