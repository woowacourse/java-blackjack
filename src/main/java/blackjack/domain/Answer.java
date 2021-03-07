package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.user.User;

import java.util.Arrays;

public enum Answer {
    YES("y"),
    NO("n");

    private String answer;

    Answer(String answer) {
        this.answer = answer;
    }

    public static Answer of(String value) {
        return Arrays.stream(Answer.values())
                .filter(answer -> answer.equals(value))
                .findAny()
                .get();
    }

    public boolean equals(String value) {
        return answer.equals(value);
    }

    public void executeByAnswer(User user, Deck deck) {
        if (this.equals(YES)) {
            user.addCard(deck.drawCard());
        }
    }
}
