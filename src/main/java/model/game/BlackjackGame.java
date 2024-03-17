package model.game;

import model.result.ParticipantCard;
import model.result.ParticipantCards;
import model.result.ParticipantProfits;
import model.result.ParticipantScores;
import model.betting.Bets;
import model.card.Card;
import model.card.CardDeck;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.Player;
import model.participant.Players;

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

    public ParticipantCard dealCard(Player player) {
        Card card = cardDeck.drawCard();
        player.hit(card);
        return ParticipantCard.createWithAllCard(player);
    }

    public boolean dealerHitTurn() {
        if (dealer.isPossibleHit()) {
            Card card = cardDeck.drawCard();
            dealer.hit(card);
            return true;
        }
        return false;
    }

    public ParticipantScores finish(Players players) {
        return ParticipantScores.of(dealer, players);
    }

    public ParticipantProfits calculateProfit(Players players, Bets bets) {
        return ParticipantProfits.of(players, dealer, bets);
    }

    public int dealerCardSize() {
        return dealer.cardSize();
    }
}
