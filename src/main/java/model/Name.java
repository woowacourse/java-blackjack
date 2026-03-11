package model;

public record Name(String name) {

    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 10;

    public Name {
        validate(name);
    }

    private static void validate(String name) {
        validateNullAndEmpty(name);
        validateLength(name);
    }

    private static void validateLength(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("닉네임 길이 제한을 초과했습니다. 최소: %d, 최대: %d", MIN_NAME_LENGTH, MAX_NAME_LENGTH)
            );
        }
    }

    private static void validateNullAndEmpty(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("닉네임 정보는 비어있을 수 없습니다.");
        }
    }
}
