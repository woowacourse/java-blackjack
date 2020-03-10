package domain.player;

import domain.ParticipantCards;
import domain.Result;

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

    public Result beatDealer(Dealer dealer) {
        int dealerScore = dealer.calculateScore();
        int userScore = this.calculateScore();
        if (dealerScore > 21) {
            if (userScore > 21) {
                return Result.패;
            }
            return Result.승;
        }
        if (userScore > 21) {
            return Result.패;
        }
        if (dealerScore == userScore) {
            return Result.무;
        }
        if (dealerScore > userScore) {
            return Result.패;
        }
        return Result.승;
    }
}
