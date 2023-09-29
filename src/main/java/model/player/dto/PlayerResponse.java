package model.player.dto;

import model.card.Card;
import model.cards.Cards;
import model.name.Name;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static util.Keyword.DEALER;

public class PlayerResponse {

    private final String name;
    private final Cards cards;
    private int grade;

    private PlayerResponse(final String name, final Cards cards, final int grade) {
        this.name = name;
        this.cards = cards;
        this.grade = grade;
    }

    public static PlayerResponse createDefault(final Name name, final Cards cards) {
        return new PlayerResponse(name.getName(), cards, Integer.MAX_VALUE);
    }

    public static PlayerResponse withGrade(final Name name, final Cards cards, final int grade) {
        return new PlayerResponse(name.getName(), cards, grade);
    }

    public String getNameValue() {
        return name;
    }

    public String getCardsName() {
        List<Name> cardNames = getPlayerCardNames();
        return Name.chainingNames(cardNames);
    }

    public String getCardsNameWithSecret() {
        List<Name> cardNames = getPlayerCardNames().stream()
                .limit(cards.getCardSize() - 1)
                .collect(Collectors.toList());
        return Name.chainingNames(cardNames);
    }

    private List<Name> getPlayerCardNames() {
        return cards.getCardList()
                .stream()
                .map(Card::getName)
                .collect(Collectors.toList());
    }

    public boolean isUpThanDealer(PlayerResponse dealer) {
        return grade < dealer.getGrade();
    }

    public boolean isSameWithDealer(PlayerResponse dealer) {
        return grade == dealer.getGrade();
    }

    public boolean isDealerResponse() {
        return name.equals(DEALER.getValue());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerResponse response = (PlayerResponse) o;
        return Objects.equals(name, response.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public int getScore() {
        return cards.calculateScore();
    }

    public int getGrade() {
        return grade;
    }
}
