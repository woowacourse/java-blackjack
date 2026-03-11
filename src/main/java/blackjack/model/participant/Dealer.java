package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.cardDeck.CardDeck;
import blackjack.model.Hands;
import blackjack.model.result.Result;
import java.util.List;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int PICK_THRESHOLD = 16;

    public Dealer() {
        super(DEALER_NAME, Hands.empty());
    }

    @Override
    public void pickInitialCards(CardDeck cardDeck) {
        hands.addCard(cardDeck.pick());
        
        Card secondPickedCard = pickSecondCard(cardDeck);
        hands.addCard(secondPickedCard);
    }
    
    private Card pickSecondCard(CardDeck cardDeck) {
        Card secondPickedCard = cardDeck.pick();
        secondPickedCard.flip();
        return secondPickedCard;
    }

    public boolean canPick() {
        return !hands.isTotalScoreOver(PICK_THRESHOLD);
    }

    public Result determineResultOf(Player player) {
        if (player.isBust()) {
            return Result.LOSE;
        }

        if (this.isBust()) {
            return Result.WIN;
        }

        if (hands.isTotalScoreOver(
                player.getCurrentTotalScore())
        ) {
            return Result.LOSE;
        }

        if (player.getCurrentTotalScore() > hands.calculateTotalScore()) {
            return Result.WIN;
        }

        return Result.DRAW;
    }

    public List<Player> awardPrize(List<Player> players) {
        if (this.isBust()) {
            return List.copyOf(players);
        }

        if (this.isBlackjack()) {
            return players.stream()
                    .map(player -> {
                        if (player.isBlackjack()) {
                            return player;
                        }

                        return player.bust();
                    }).toList();
        }

        int dealerScore = this.getCurrentTotalScore();
        System.out.println("dealerScore: " + dealerScore);

        return players.stream()
                .map(player -> {
                    if (player.isBlackjack()) {
                        return player.blackjack();
                    }

                    if (player.getCurrentTotalScore() >= dealerScore) {
                        return player;
                    }

                    return player.bust();
                }).toList();
    }
}
