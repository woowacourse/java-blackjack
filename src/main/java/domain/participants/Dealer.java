package domain.participants;

import static java.util.stream.Collectors.toList;

import domain.card.Card;
import domain.card.DrawnCards;
import domain.message.ExceptionMessage;
import java.util.Collections;
import java.util.List;

public class Dealer extends Participant {

    private static final int DEALER_DRAW_LIMIT_SCORE = 16;
    private static final double GAME_LOSE_RATIO = 0.5;

    public Dealer(final DrawnCards drawnCards) {
        super(new Status(new Name(ExceptionMessage.DEALER_NAME.getMessage()), new Account(0)), drawnCards);
    }

    @Override
    public List<Card> openDrawnCards() {
        List<Card> openCards = drawnCards.getCards()
                .stream()
                .skip(1)
                .collect(toList());

        return Collections.unmodifiableList(openCards);
    }

    @Override
    public boolean canDrawMore() {
        return calculateCardScore() <= DEALER_DRAW_LIMIT_SCORE;
    }

    public void winGame(final int playerAccount) {
        this.status.winGame(playerAccount);
    }

    public void loseGame(final int playerAccount) {
        int loseMoney = (int) (playerAccount * GAME_LOSE_RATIO);
        status.loseGame(loseMoney);
    }
}
