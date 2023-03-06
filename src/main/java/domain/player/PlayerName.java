package domain.player;

public class PlayerName {
    private final String name;
    
    public PlayerName(String name) {
        validateName(name);
        this.name = name;
    }
    
    private void validateName(String name) {
        if (name.length() < 1 || name.length() > 5) {
            throw new IllegalArgumentException("참가자의 이름 길이 범위는 1~5를 벗어날 수 없습니다.");
        }
    }
    
    public String getName() {
        return name;
    }
}
