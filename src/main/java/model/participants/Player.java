package model.participants;

import static view.OutputView.printBust;
import static view.OutputView.printHandCardsNames;

import model.card.Card;
import model.card.Hand;
import model.casino.Deck;
import model.casino.HitOrHoldPolicy;

public class Player {

    private final String name;
    protected Hand hand;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public boolean resolveBust() {
        if (isHandBust() && containsOriginalAce()) {
            setOriginalAceValueToOne();
            resolveBust();
        }
        return !isHandBust();
    }

    public void bustCheckOfHitOrHold(Deck deck, HitOrHoldPolicy hitOrHoldPolicy) {
        boolean isAlive = resolveBust();
        while (isAlive) {
            if (hitOrHoldPolicy.hold()) {
                return;
            }
            isAlive = bustWithDrawOneMoreCard(deck);
        }
        setHandTotalToZero();
        printBust();
    }

    private boolean bustWithDrawOneMoreCard(Deck deck) {
        addCard(deck.draw());
        if (isNotDealer()) {
            printHandCardsNames(this);
        }
        return resolveBust();
    }

    public boolean isNotDealer() {
        return true;
    }

    public int getHandTotal() {
        return hand.getTotal();
    }

    public boolean isHandBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public boolean containsOriginalAce() {
        return hand.containsOriginalAce();
    }

    public void setOriginalAceValueToOne() {
        hand.setOriginalAceValueToOne();
    }

    public void setHandTotalToZero() {
        hand.setAllCardValueToZero();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

}
