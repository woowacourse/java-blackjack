package domain.participant;

public class PlayerName {
    private static final String NAME_IS_NOT_NULL_OR_BLANK_ERROR_MESSAGE = "[ERROR] 이름은 공백이나 빈 칸 일 수 없습니다.";

    private final String name;

    public PlayerName(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name){
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException(NAME_IS_NOT_NULL_OR_BLANK_ERROR_MESSAGE);
        }
    }

    public String getName() {
        return name;
    }
}
