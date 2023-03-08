package domain.player;

public class PlayerName {
    private static final int PLAYER_NAME_MIN_LENGTH = 1;
    private static final int PLAYER_NAME_MAX_LENGTH = 5;
    
    private final String name;
    
    public PlayerName(String name) {
        validateName(name);
        this.name = name;
    }
    
    private void validateName(String name) {
        if (name.length() < PLAYER_NAME_MIN_LENGTH || name.length() > PLAYER_NAME_MAX_LENGTH) {
            throw new IllegalArgumentException("참가자의 이름 길이 범위는 1~5를 벗어날 수 없습니다.");
        }
    }
    
    public String getName() {
        return name;
    }
}
