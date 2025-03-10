package domain;

import static view.InputView.getContinueOrNot;
import static view.OutputView.printBust;
import static view.OutputView.printHandCardsNames;

public class Player {
    private static final String YES = "y";
    private static final String NO = "n";

    private final String name;
    protected Hand hand;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public void drawByChoice(Deck deck) {
        boolean isAlive = resolveBust();
        while (isAlive) {
            String continueOrNot = getContinueOrNot(this);
            if (continueOrNot.equalsIgnoreCase(YES)) {
                isAlive = drawOneMoreCard(deck);
            }
            if (continueOrNot.equalsIgnoreCase(NO)) {
                return;
            }
        }
        printBust();
        setHandTotalToZero();
    }

    public boolean resolveBust() {
        if (isHandBust() && containsOriginalAce()) {
            setOriginalAceValueToOne();
            resolveBust();
        }
        return !isHandBust();
    }

    public boolean drawOneMoreCard(Deck deck) {
        addCard(deck.draw());
        if (isNotDealer()) {
            printHandCardsNames(this);
        }
        return resolveBust();
    }

    private boolean isNotDealer() {
        return !this.getClass().equals(Dealer.class);
    }

    public int getHandTotal() {
        return hand.getTotal();
    }

    public boolean isHandBust() {
        return hand.isBust();
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

    public WinLossResult getWinLoss(int dealerTotal) {
        return WinLossResult.of(Integer.compare(getHandTotal(), dealerTotal));
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

}
