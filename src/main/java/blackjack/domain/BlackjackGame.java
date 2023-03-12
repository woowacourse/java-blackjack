package blackjack.domain;

import java.util.List;

public class BlackjackGame {

    public static final int CONVERT_TO_DEALER_AMOUNT = -1;

    private final CardDeck cardDeck;
    private final Participants participants;

    public BlackjackGame(Participants participants, CardDeck cardDeck) {
        this.participants = participants;
        this.cardDeck = cardDeck;
    }

    public BlackjackGame(Participants participants) {
        this(participants, new CardDeck());
    }

    public void dealOutCard() {
        for (Player player : participants.getPlayers()) {
            List<Card> cards = cardDeck.pickTwice();
            player.addCards(cards);
        }
        dealOutCardToDealer();
    }

    private void dealOutCardToDealer() {
        Dealer dealer = participants.getDealer();
        List<Card> cards = cardDeck.pickTwice();
        dealer.addCards(cards);
    }

    public void calculateBetAmount() {
        for (Player player : participants.getPlayers()) {
            ResultState resultState = ResultState.of(player, participants.getDealer());
            player.multipleBetAmount(resultState.getTimes());
        }
    }

    public int getDealerAmount() {
        return participants.getPlayers()
                           .stream()
                           .mapToInt(Player::getBetAmount)
                           .sum() * CONVERT_TO_DEALER_AMOUNT;
    }

    public void giveCard(Participant participant) {
        participant.addCard(cardDeck.pick());
    }
}
