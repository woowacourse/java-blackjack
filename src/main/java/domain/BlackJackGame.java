package domain;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Players;
import view.OutputView;

public class BlackJackGame {
    private static final int INIT_CARD_NUM = 2;
    public static final int BLACK_JACK_NUMBER = 21;
    public static final int SINGLE_CARD_NUM = 1;

    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackJackGame(Players players, Dealer dealer, Deck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public void initSettingCards() {
        dealer.takeCard(deck, INIT_CARD_NUM);
        players.initDistribute(deck);
    }

    public void distributeCard(Participant participant, int num) {
        participant.takeCard(deck, num);
    }

    public void dealerExecute() {
        while (dealer.isDrawable()) {
            OutputView.printDealerReceivedMessage();
            dealer.getHandCards().takeCard(deck.generateCard());
        }
    }
}
