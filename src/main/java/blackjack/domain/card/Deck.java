package blackjack.domain.card;

public interface Deck {

    Cards initialDraw();

    Card draw();

    int size();
}
