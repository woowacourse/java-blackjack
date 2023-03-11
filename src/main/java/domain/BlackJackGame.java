package domain;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Players;

public class BlackJackGame {
    private static final int INIT_CARD_NUM = 2;
    private final Players players;
    private final Dealer dealer;

    public BlackJackGame(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void initSettingCards(Deck deck) {
        dealer.takeCard(deck, INIT_CARD_NUM);
        players.initDistribute(deck);
    }

    public void distributeCard(Deck deck, Participant participant, int num) {
        participant.takeCard(deck, num);
    }
}
