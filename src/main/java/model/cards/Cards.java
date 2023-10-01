package model.cards;

import model.card.Card;
import model.card.dto.CardRequest;
import model.name.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static util.Rule.GOAL_SCORE;

public class Cards {

    private final List<Card> cards;

    private Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public static Cards from(final List<Card> cards) {
        return new Cards(cards);
    }

    public static Cards createPlayerCards() {
        return new Cards(new ArrayList<>());
    }

    public static List<Card> createNormalCardsWithScore(int score, final List<String> names) {
        List<Name> cardNames = Name.convertStringListToNamesWithScore(score, names);

        return cardNames.stream()
                .map(cardName -> CardRequest.createDefault(cardName.getName(), score))
                .map(Card::from)
                .collect(Collectors.toList());
    }

    public static List<Card> createSpecialCards(final String special, final List<String> names, int score) {
        List<Name> cardNames = Name.convertStringListToNamesWithSpecial(special, names);

        return cardNames.stream()
                .map(cardName -> CardRequest.createDefault(cardName.getName(), score))
                .map(Card::from)
                .collect(Collectors.toList());
    }

    public void addCards(List<Card> newCards) {
        cards.addAll(newCards);
    }

    public void downInitialScoreIfExceedLimit() {
        if (this.calculateScore() > GOAL_SCORE.getValue()) {
            firstCardDownScore();
        }
    }

    private void firstCardDownScore() {
        this.getCardList()
                .stream()
                .limit(1)
                .forEach(Card::downScore);
    }

    public void changeScoreIfHaveAce() {
        cards.stream()
                .filter(card -> Name.isAce(card.getName()))
                .findFirst()
                .ifPresent(Card::downScore);
    }


    public int getCardSize() {
        return cards.size();
    }

    public Card useCard(int index) {
        if (index < cards.size()) {
            Card card = cards.get(index);
            cards.remove(index);
            return card;
        }
        return null;
    }

    public List<Card> getCardList() {
        return cards;
    }

    public int calculateScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }
}
