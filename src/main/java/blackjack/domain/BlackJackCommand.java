package blackjack.domain;

import java.util.Arrays;

public enum BlackJackCommand {
    YES("y"),
    NO("n"),
    ;

    private final String answer;

    BlackJackCommand(final String answer) {
        this.answer = answer;
    }


    public static BlackJackCommand from(final String hitOrStay) {
        return Arrays.stream(values())
            .filter( it -> it.answer.equalsIgnoreCase(hitOrStay))
            .findFirst()
            .orElseThrow( () -> new IllegalArgumentException("잘못된 커맨드를 입력하셨습니다."));
    }
}
