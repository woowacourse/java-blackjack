package blackjack.dto;

import blackjack.domain.Person;

public class PersonTotalStatusResponse {
    private final PersonStatusResponse personStatusResponse;
    private final int score;

    public PersonTotalStatusResponse(PersonStatusResponse personStatusResponse, int score) {
        this.personStatusResponse = personStatusResponse;
        this.score = score;
    }

    public static PersonTotalStatusResponse of(Person person) {
        return new PersonTotalStatusResponse(PersonStatusResponse.of(person), person.getScore());
    }

    public PersonStatusResponse getPersonStatusResponse() {
        return personStatusResponse;
    }

    public int getScore() {
        return score;
    }
}
