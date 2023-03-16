package blackjack.domain.participant.player;

public enum AddCardOrNot {
    YES,
    NO;

    public static AddCardOrNot of(String command) {
        String lowerCommand = command.toLowerCase();
        if (lowerCommand.equals("y")) {
            return YES;
        }
        if (lowerCommand.equals("n")) {
            return NO;
        }
        throw new IllegalArgumentException("올바른 카드 추가 명령어가 아닙니다.");
    }
}
