package blackjack.domain.card;

public interface Hand {

    Hand draw(final Card card);

    Hand stay();

    boolean isDrawable();

    Cards cards();

    int score();

    Result play(final Hand other);
}
