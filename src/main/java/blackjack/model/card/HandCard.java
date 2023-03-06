package blackjack.model.card;

import blackjack.model.ResultState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HandCard {
    private final List<Card> cards;

    public HandCard() {
        this.cards = new ArrayList<>();
    }

    public HandCard(List<Card> cards) {
        this.cards = cards;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public CardScore score(ResultState state){
        List<CardNumber> ownedNumbers = cards.stream().map(Card::getNumber).collect(Collectors.toList());
        return new CardScore(ownedNumbers, state);
    }

    public boolean isBigScoreEqual(int throttle) {
        List<CardNumber> numbers = cards.stream()
                .map(Card::getNumber)
                .collect(Collectors.toList());

        return (bigScore(numbers) == throttle);
    }

   public boolean isBigScoreOver(int throttle) {
       List<CardNumber> numbers = cards.stream()
               .map(Card::getNumber)
               .collect(Collectors.toList());

       return (bigScore(numbers) > throttle);
   }

    public boolean isSmallScoreOver(int throttle) {
        List<CardNumber> numbers = cards.stream()
                .map(Card::getNumber)
                .collect(Collectors.toList());

        return (smallScore(numbers) > throttle);
    }

    public int smallScore(List<CardNumber> numbers) {
        int score = 0;
        for (CardNumber number : numbers) {
            score += number.getSmallScore();
        }
        return score;
    }

    public int bigScore(List<CardNumber> numbers) {
        int score = 0;
        for (CardNumber number : numbers) {
            score += number.getBigScore();
        }
        return score;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int size() {
        return cards.size();
    }
}
