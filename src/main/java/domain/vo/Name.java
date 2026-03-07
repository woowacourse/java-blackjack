package domain.vo;

import java.util.regex.Pattern;

public class Name {
    private String name;

    public Name(String name) {
        validateName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private static void validateName(String name) {
        validateKorEng(name);
        validateNotDealer(name);
    }

    private static void validateNotDealer(String name){
        if (name.equals("딜러")) {
            throw new IllegalArgumentException("[ERROR] 딜러는 플레이어 이름으로 사용할 수 없습니다.");
        }
    }

    private static void validateKorEng(String name) {
        Pattern namePattern = Pattern.compile("^[a-zA-Z가-힣]+$");

        if(!namePattern.matcher(name).matches()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름은 영어와 한글만 가능합니다.");
        }
    }
}
