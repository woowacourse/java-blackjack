package blackjack.dto;

import blackjack.domain.Person;
import java.util.List;

public class PersonTotalStatusResponse extends PersonStatusResponse {
    private final int score;

    public PersonTotalStatusResponse(String name, List<String> cards, int score) {
        super(name, cards);
        this.score = score;
    }

    public static PersonTotalStatusResponse of(Person person) {
        return new PersonTotalStatusResponse(person.getName(), getCardsStatus(person.getCards()), person.getScore());
    }

    public int getScore() {
        return score;
    }
}
