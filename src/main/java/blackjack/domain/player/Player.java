package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;
import java.util.Objects;

public abstract class Player {

    protected static final int MAX_SCORE = 21;

    private final Cards cards;
    private final String name;
    private final State state;
    private final Bet bet;

    Player(final List<Card> cards, final String name, final Bet bet) {
        validateCards(cards);
        validateName(name);
        this.cards = new Cards(cards);
        this.state = new State();
        this.name = name;
        this.bet = bet;
    }

    private void validateCards(final List<Card> cards) {
        if (cards == null || cards.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 비어있는 카드입니다.");
        }
    }

    private void validateName(final String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이름은 비어있을 수 없습니다.");
        }
    }

    public abstract boolean acceptableCard();

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

    public void win() {
        state.win();
        bet.win();
    }

    public void lose() {
        state.lose();
        bet.lose();
    }

    public List<Card> getCards() {
        return this.cards.getCards();
    }

    public String getName() {
        return this.name;
    }

    public int getWinCount() {
        return state.getWinCount();
    }

    public int getLoseCount() {
        return state.getLoseCount();
    }

    public Bet getBet() {
        return bet;
    }

    public int getBetProfit(){
        return bet.getProfit();
    }

    protected int getScoreByAceOne() {
        return cards.calculateScoreByAceOne();
    }

    protected int getScoreByAceEleven() {
        return cards.calculateMaxScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
