package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

import java.util.List;

public class BlackjackGame {

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
        calculateDealerAmount();
    }

    private void calculateDealerAmount() {
        int playerTotalAmount = participants.getPlayers()
                                            .stream()
                                            .mapToInt(Player::getBetAmount)
                                            .sum();
        participants.getDealer()
                    .initBetAmount(playerTotalAmount);

    }

    public void giveCard(Participant participant) {
        participant.addCard(cardDeck.pick());
    }
}
