package blackjack.domain.gameplayer;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;
import java.util.Objects;

public class Player extends BlackJackParticipant {
    public static final Score hitUpperBound = Score.of(21);

    private final Name name;
    private Betting betting;

    public Player(Name name, Betting betting) {
        super(new Cards());
        this.name = name;
        this.betting = betting;
    }

    public void bet(int betting) {
        this.betting = this.betting.changeBetting(betting);
    }

    public String showName() {
        return name.getName();
    }

    public Betting getBetting() {
        return betting;
    }

    @Override
    public List<Card> showCards() {
        return cards.getCards();
    }

    @Override
    public boolean canContinue() {
        Score totalScore = calculateScore();
        return totalScore.isLessThan(hitUpperBound);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(cards, player.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards);
    }
}
