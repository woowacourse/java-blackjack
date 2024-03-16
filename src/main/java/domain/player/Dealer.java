package domain.player;

import domain.card.Card;
import domain.card.Deck;
import dto.DealerResponse;

public class Dealer extends Participant {
    private static final int HIT_UPPER_BOUND = 17;

    private final Deck decks = Deck.makeDecks();

    public Result compareHandsWith(final Player player) {
        if (isStand() && player.isStand()) {
            return compareScore(player);
        }
        return getPlayerResult(player);
    }

    private Result getPlayerResult(final Player player) {
        if ((isBlackjack() && (player.isStand() || player.isBust())) ||
                (isStand() && player.isBust()) ||
                (isBust()  && player.isBust())) {
            return Result.WIN;
        }
        if (isBlackjack() && player.isBlackjack()) {
            return Result.TIE;
        }
        return Result.LOSE;
    }

    public Card draw() {
        return decks.draw();
    }


    private Result compareScore(final Player player) {
        if (getScore() == player.getScore()) {
            return Result.TIE;
        }
        if (getScore() < player.getScore()) {
            return Result.LOSE;
        }
        return Result.WIN;
    }

    @Override
    public boolean canHit() {
        return state.canHit(HIT_UPPER_BOUND);
    }

    public DealerResponse toDealerResponse() {
        return new DealerResponse(getHands().stream().map(Card::toCardResponse).toList(),
                state.getHands().calculateScore());
    }
}
