package util;

public class Parser {
    // 쉼표 기준으로 분리
    // 예외 처리
    public void parseParticipantsName(String participantsName) {

    }

    public void validateParticipantsName(String participantsName) {
        validateEmptyInput(participantsName);
        validateNonLiteralInput(participantsName);
        validateInvalidSymbolInput(participantsName);
    }

    static void validateEmptyInput(String participantsName) {
        if (participantsName.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 참가자 이름은 비어 있을 수 없습니다.");
        }
    }

    static void validateNonLiteralInput(String participantsName) {

    }

    static void validateInvalidSymbolInput(String participantsName) {
    }
}
