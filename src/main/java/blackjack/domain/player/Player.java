package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class Player {

    protected static final int MAX_SCORE = 21;

    private final Cards cards;
    private final String name;

    Player(final List<Card> cards, final String name) {
        this.cards = new Cards(cards);
        this.name = name;
    }

    public int calculateFinalScore() {
        final int score = getScoreByAceEleven();
        if (score <= MAX_SCORE) {
            return score;
        }
        return getScoreByAceOne();
    }

    public void addCard(final Card card) {
        cards.addCard(card);
    }

    public static Participant changeToParticipant(Player player) {
        if (player instanceof Participant) {
            return (Participant) player;
        }
        throw new ClassCastException("[ERROR] Player가 참여자가 아닙니다.");
    }

    public static Dealer changeToDealer(Player player) {
        if (player instanceof Dealer) {
            return (Dealer) player;
        }
        throw new ClassCastException("[ERROR] Player가 딜러가 아닙니다.");
    }

    public List<Card> getCards() {
        return this.cards.getCards();
    }

    public String getName() {
        return this.name;
    }

    protected int getScoreByAceOne() {
        return cards.calculateScoreByAceOne();
    }

    protected int getScoreByAceEleven() {
        return cards.calculateScoreByAceEleven();
    }
}
