package dto;

public record AgreementRequestDto(String agreement) {

    public AgreementRequestDto {
        validateAgreementIsNotNullAndNotBlank(agreement);
        validateAgreementIsYOrN(agreement);
    }

    private void validateAgreementIsNotNullAndNotBlank(String agreement) {
        if (agreement == null || agreement.isBlank()) {
            throw new IllegalArgumentException("y/n 입력은 null 또는 공백이면 안됩니다");
        }
    }

    private void validateAgreementIsYOrN(String agreement) {
        if (!"y".equals(agreement) && !"n".equals(agreement)) {
            throw new IllegalArgumentException("y/n 입력은 y 또는 n 이어야만 합니다.");
        }
    }
}
