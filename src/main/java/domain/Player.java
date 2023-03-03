package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {

    private final String name;
    private final List<Card> cards;


    public Player(String name) {
        this(name, new ArrayList<>());
    }

    public Player(String name, List<Card> cards) {
        this.name = name;
        this.cards = new ArrayList<>(cards);
    }

    private int calculateScore() {
        List<Card> cardsExceptA = getCardsExceptA();
        int scoreExceptA = getScoreExceptA(cardsExceptA);
        int countA = cards.size() - cardsExceptA.size();
        return getFinalScore(countA, scoreExceptA);
    }

    private int getScoreExceptA(List<Card> cardsExceptA) {
        return cardsExceptA.stream()
                .map(Card::getLetter)
                .mapToInt(this::getScoreFrom)
                .sum();
    }

    private List<Card> getCardsExceptA() {
        return cards.stream()
                .filter(Card::isNotA)
                .collect(Collectors.toList());
    }

    private int getFinalScore(int countA, final int scoreExceptA) {
        int score = scoreExceptA;
        for (int i = 0; i < countA; i++) {
            score += getScoreFromA(score);
        }

        return score;
    }

    // TODO: 2023/03/03 메서드명 변경
    private int getScoreFromA(int score) {
        if (score + 11 > 21) {
            return 1;
        }

        return 11;
    }

    private int getScoreFrom(String letter) {
        if (letter.equals("K") || letter.equals("Q") || letter.equals("J")) {
            return 10;
        }

        return Integer.parseInt(letter);
    }

    public int getScore() {
        return calculateScore();
    }

    public boolean isBusted() {
        return getScore() > 21;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }
}


