package dto;

public record NameRequestDto(String name) {

    public NameRequestDto(String name) {
        validateNameIsNotNullAndIsNotBlank(name);
        this.name = name;
    }

    private void validateNameIsNotNullAndIsNotBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름 리스트는 null 또는 공백이면 안됩니다");
        }
    }
}
