package blackjack.domain.player;

import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.PlayingCards;

public interface Player extends State {

    void addCard(PlayingCard playingCard);

    boolean isDealer();

    boolean isWin(Player player);

    boolean isLose(Player player);

    boolean isDraw(Player player);

    String getName();

    PlayingCards getPlayingCards();
}
