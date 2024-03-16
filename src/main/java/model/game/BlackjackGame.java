package model.game;

import dto.ParticipantCards;
import model.card.Card;
import model.card.CardDeck;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.Player;
import model.participant.Players;
import model.result.GameResult;

public class BlackjackGame {

    private static final int DEQUE_COUNT = 4;
    private static final int INITIAL_CARD_COUNT = 2;

    private final CardDeck cardDeck;
    private final Dealer dealer;

    public BlackjackGame() {
        cardDeck = CardDeck.createShuffledDeck(DEQUE_COUNT);
        dealer = new Dealer();
    }

    public ParticipantCards dealInitialCards(Players players) {
        dealCards(dealer);
        players.getPlayers()
            .forEach(this::dealCards);
        return ParticipantCards.createWithInitialCards(dealer, players);
    }

    private void dealCards(Participant participant) {
        for (int count = 0; count < INITIAL_CARD_COUNT; count++) {
            Card card = cardDeck.drawCard();
            participant.hit(card);
        }
    }

    public void dealCard(Player player) {
        Card card = cardDeck.drawCard();
        player.hit(card);
    }

    public boolean dealerHitTurn() {
        if (dealer.isPossibleHit()) {
            Card card = cardDeck.drawCard();
            dealer.hit(card);
            return true;
        }
        return false;
    }

    public GameResult finish(Players players) {
        return GameResult.of(dealer, players);
    }

    public Dealer getDealer() {
        return dealer;
    }
}
