package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Result;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private Cards cards;
    private List<Result> record;

    public Dealer() {
        this.cards = new Cards();
        this.record = new ArrayList<>();
    }

    public int draw(Deck deck, int index) {
        cards.add(deck.getCard(index++));
        return index;
    }

    public Cards cards() {
        return cards;
    }

    public Result compare(Player player) {
        Result result = Result.of(this.isBlackjack(), player.isBlackjack());

        if (result != Result.NONE) {
            return result;
        }

        result = Result.of(this.cards.getScore(), player.getScore());
        addRecord(result);

        return result;
    }

    private void addRecord(Result playerResult) {
        if (playerResult == Result.DRAW) {
            record.add(Result.DRAW);
        }

        if (playerResult == Result.LOSE) {
            record.add(Result.WIN);
        }

        record.add(Result.LOSE);
    }

    public boolean isBlackjack(){
        return cards.size() == 2 && cards.getScore() == 21;
    }

    public List<Result> getRecord(){
        return record;
    }
}
