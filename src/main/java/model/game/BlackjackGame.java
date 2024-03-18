package model.game;

import model.card.Card;
import model.card.CardDeck;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.Player;
import model.participant.Players;
import model.result.ParticipantCard;
import model.result.ParticipantCards;
import model.result.ParticipantProfits;
import model.result.ParticipantScores;

public class BlackjackGame {

    private static final int DEQUE_COUNT = 4;
    private static final int INITIAL_CARD_COUNT = 2;

    private final CardDeck cardDeck;

    public BlackjackGame() {
        cardDeck = CardDeck.createShuffledDeck(DEQUE_COUNT);
    }

    public ParticipantCards dealInitialCards(Dealer dealer, Players players) {
        dealCards(dealer);
        players.getPlayers()
            .forEach(this::dealCards);
        return ParticipantCards.createWithInitialCards(dealer, players);
    }

    private void dealCards(Participant participant) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            Card card = cardDeck.drawCard();
            participant.hit(card);
        }
    }

    public ParticipantCard dealCardTo(Player player) {
        Card card = cardDeck.drawCard();
        player.hit(card);
        return ParticipantCard.createWithAllCard(player);
    }

    public boolean dealCardTo(Dealer dealer) {
        if (dealer.isPossibleHit()) {
            Card card = cardDeck.drawCard();
            dealer.hit(card);
            return true;
        }
        return false;
    }

    public ParticipantScores finish(Dealer dealer, Players players) {
        return ParticipantScores.of(dealer, players);
    }

    public ParticipantProfits calculateProfit(Dealer dealer, Players players) {
        return ParticipantProfits.of(players, dealer);
    }
}
