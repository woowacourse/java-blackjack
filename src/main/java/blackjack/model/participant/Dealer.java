package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.cardDeck.CardDeck;
import blackjack.model.Hands;
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

    public int getProfit(List<Player> players) {
        return (int) players.stream()
                .mapToInt(Player::getPrize)
                .sum();
    }
}
