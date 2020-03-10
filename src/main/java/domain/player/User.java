package domain.player;

import domain.ParticipantCards;

public class User extends Participant {

    public User(String name) {
        validateName(name);
        this.name = name;
        this.cards = new ParticipantCards();
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("이름은 blank 값이 될 수 없습니다.");
        }
    }
}
