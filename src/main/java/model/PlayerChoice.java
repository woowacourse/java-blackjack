package model;

import java.util.Arrays;

public enum PlayerChoice {
    HIT("hit"),
    STAND("stand"),
    SURRENDER("surrender"),
    INSURANCE("insurance"),
    DOUBLE_DOWN("double down");

    private final String choiceName;

    PlayerChoice(String choiceName) {
        this.choiceName = choiceName;
    }

    public static PlayerChoice findPlayerChoice(String input){
        return Arrays.stream(PlayerChoice.values())
                .filter(playerChoice -> playerChoice.choiceName.equals(input))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("[ERROR] 잘못된 입력입니다. 입력값 : " + input));
    }

    public String getChoiceName() {
        return choiceName;
    }
}
