package card;

public interface Rank {

    boolean matches(Rank rank);

    int getScore();
}
