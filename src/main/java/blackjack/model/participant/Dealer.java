package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.cardDeck.CardDeck;
import blackjack.model.Hands;
import blackjack.model.result.Result;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int PICK_THRESHOLD = 16;

    public Dealer() {
        super(DEALER_NAME, Hands.empty());
    }

    @Override
    public void pickInitialCards(CardDeck cardDeck) {
        hands.addCard(cardDeck.pick());

        Card secondPickedCard = cardDeck.pick();
        secondPickedCard.flip();
        hands.addCard(secondPickedCard);
    }

    public boolean canPick() {
        return !hands.isTotalScoreOver(PICK_THRESHOLD);
    }

    public Result compare(Player player) {
        // 플레이어가 버스트되면 무조건 딜러 승
        if (player.isBust()) {
            return Result.LOSE;
        }

        // 플레이어가 버스트 되지 않고, 딜러가 버스트 되면 플레이어 승
        if (this.isBust()) {
            return Result.WIN;
        }

        // 딜러의 점수가 더 높으면 승
        if (this.hands.isTotalScoreOver(player.getCurrentTotalScore())) {
            return Result.LOSE;
        }

        // 플레이어 점수가 더 높으면 패
        if (player.getCurrentTotalScore() > this.hands.calculateTotalScore()) {
            return Result.WIN;
        }

        // 무승부
        return Result.DRAW;
    }
}
