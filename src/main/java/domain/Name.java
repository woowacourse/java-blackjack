package domain;

public class Name {
    private final String name;
    private static final Integer MIN_LENGTH = 2;
    private static final Integer MAX_LENGTH = 5;

    public Name(String name) {
        validateBlank(name);
        //validateEnglish(name);
        validateLength(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private static void validateBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 비어있거나 공백일 수 없습니다.");
        }
    }

    //TODO: Controller initDealer에서 안됨.
//    private static void validateEnglish(String name) {
//        if (!name.matches("^[a-zA-Z]*$")) {
//            throw new IllegalArgumentException("이름은 영어만 허용됩니다.");
//        }
//    }

    private static void validateLength(String name) {
        if (name.length() < MIN_LENGTH || name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("이름은 2~5글자만 허용됩니다.");
        }
    }
}
