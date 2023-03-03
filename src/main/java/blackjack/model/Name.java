package blackjack.model;

public class Name {

    private final String name;

    public Name(String name) {
        validateIsEmpty(name);
        this.name = name;
    }

    private void validateIsEmpty(String name){
        if(name.isBlank()){
            throw new IllegalArgumentException("이름은 빈 문자열일 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }
}
