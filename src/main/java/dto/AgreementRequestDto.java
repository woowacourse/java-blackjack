package dto;

public record AgreementRequestDto(String agreement) {

    public AgreementRequestDto {
        validateNameIsNotNullAndIsNotBlank(agreement);
    }

    private void validateNameIsNotNullAndIsNotBlank(String agreement) {
        if (agreement == null || agreement.isBlank()) {
            throw new IllegalArgumentException("y/n 입력은 null 또는 공백이면 안됩니다");
        }
    }
}
