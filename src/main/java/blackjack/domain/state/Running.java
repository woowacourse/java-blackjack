package blackjack.domain.state;

public abstract class Running extends Started {

    Running(Cards cards) {
        super(cards);
    }

}
