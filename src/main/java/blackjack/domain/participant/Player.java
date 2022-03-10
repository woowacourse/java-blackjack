package blackjack.domain.participant;

import blackjack.domain.Card;
import blackjack.domain.Hand;
import blackjack.domain.Result;
import java.util.List;
import java.util.Objects;

public class Player {

    private final Name name;
    private final Hand cardHand;

    public Player(String name) {
        this(new Name(name));
    }

    public Player(Name name) {
        this(name, new Hand());
    }

    public Player(Name name, Hand cardHand) {
        this.name = name;
        this.cardHand = cardHand;
    }

    public void receiveCard(Card card) {
        cardHand.add(card);
    }

    public Result compareMatchResult(int dealerCardScore) {
        if (cardHand.getScore() < dealerCardScore || cardHand.isBust()) {
            return Result.LOSE;
        }
        if (dealerCardScore == cardHand.getScore()) {
            return Result.DRAW;
        }
        if (dealerCardScore < cardHand.getScore()) {
            return Result.WIN;
        }
        throw new IllegalArgumentException("입력값이 잘못되었을거");
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return cardHand.getCards();
    }

    public Hand getCardHand() {
        return cardHand;
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
