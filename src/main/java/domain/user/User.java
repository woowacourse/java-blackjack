package domain.user;

import domain.card.Card;

public abstract class User {

    abstract String getName();

    abstract void draw(Card card);

    abstract String getStatus();

    abstract int getScore();
}
