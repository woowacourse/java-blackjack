package model;

import java.util.*;

import static model.Player.DELIMITER;

public class Dealer extends User {
    private final Map<Result, Integer> result = new HashMap<>();

    public Dealer(CardHand cardHand) {
        super(cardHand);
        result.put(Result.WIN, 0);
        result.put(Result.DRAW, 0);
        result.put(Result.LOSE, 0);
    }

    public String toStringCardHandFirst() {
        return cardHand.getCards().get(0).toString();
    }

    public Result compareScore(Player player) {
        if(this.isBust() && player.isBust()){
            return Result.DRAW;
        }
        if(this.isBust()){
            return Result.WIN;
        }
        if(player.isBust()){
            return Result.LOSE;
        }
        return Result.calculateResult(Integer.compare(getScore(), player.getScore()));
    }

    public Map<Result, Integer> getResult() {
        return Collections.unmodifiableMap(result);
    }

    @Override
    public String toStringCardHand() {
        List<String> cardNames = new ArrayList<>();

        for (Card card : cardHand.getCards()) {
            cardNames.add(card.toString());
        }
        return String.join(DELIMITER, cardNames);
    }

    public void setResult(final Result result) {
        Result oppositeResult = Result.oppositeResult(result);
        int count = this.result.get(oppositeResult);
        this.result.put(oppositeResult, count+1);
    }
}
