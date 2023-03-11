package blackjack.model.participant;

import blackjack.model.ResultState;
import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardScore;
import blackjack.model.card.HandCard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Participant {
    private static int CURRENT_MAX_ID = 0;
    public static final int BLACKJACK_NUMBER = 21;

    private final int id;
    private final Name name;
    protected final Integer betting;
    protected final HandCard handcard;

    public Participant(Name name, HandCard handCard, Integer betting) {
        this.id = CURRENT_MAX_ID;
        this.name = name;
        this.handcard = handCard;
        this.betting = betting;
        CURRENT_MAX_ID++;
    }

    public Participant(Name name) {
        this(name, new HandCard(), 0);
    }

    public Participant(Name name, Integer betting) {
        this(name, new HandCard(), betting);
    }

    public void draw(CardDeck cardDeck) {
        if (isFinished()) {
            throw new IllegalStateException("카드를 더 뽑을 수 없는 상태입니다.");
        }
        handcard.add(cardDeck.pick());
    }

    public void drawFirstTurnCards(CardDeck cardDeck) {
        if (!handcard.isEmpty()) {
            throw new IllegalStateException("이미 첫 카드를 뽑은 상태입니다.");
        }
        draw(cardDeck);
        draw(cardDeck);
    }

    abstract public Map<String, List<Card>> firstDistributedCard();

    abstract public boolean isFinished();

    public CardScore cardScore() {
        return handcard.score(resultState());
    }

    abstract protected ResultState resultState();

    public boolean isEqualId(int findId) {
        return this.id == findId;
    }

    public boolean isBlackjack() {
        return (handcard.size() == 2) && handcard.isBigScoreEqual(BLACKJACK_NUMBER);
    }

    protected boolean isBust() {
        return handcard.isSmallScoreOver(21) && handcard.isBigScoreOver(21);
    }

    public List<Card> getCards() {
        return handcard.getCards();
    }

    public Map<String, List<Card>> getCardUnit() {
        Map<String, List<Card>> cardUnit = new HashMap<>();
        cardUnit.put(getName(), getCards());
        return cardUnit;
    }

    public String getName() {
        return name.getName();
    }

    public int getId() {
        return id;
    }
}
