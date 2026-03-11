package domain.pariticipant;


import domain.card.CardShuffler;
import domain.card.Deck;
import domain.card.Hand;

import java.util.ArrayList;

import static constant.BlackjackConstant.DEALER_NAME;

public class Participants {

    private final Dealer dealer; // dealer는 반드시 Dealer 타입이어야 하기 때문에 상위 클래스가 아닌 하위 클래스로 의존함
    private final Players players;

    public Participants(Players players) {
        this.dealer = new Dealer(new Name(DEALER_NAME), new Hand(new ArrayList<>()));
        this.players = players;
    }

    public void drawInitialCards(Deck deck, CardShuffler cardShuffler) {
        dealer.drawInitialCards(deck, cardShuffler);
        players.drawInitialCards(deck, cardShuffler);
    }
}
