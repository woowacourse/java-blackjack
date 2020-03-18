package domain.result;

public interface ScoreCalculable {
    int BLACK_JACK_SCORE = 21;
    int DEALER_DRAW_THRESHOLD = 16;
    int ACE_ADDITIONAL_SCORE = 10;

    Score calculateScore();

    boolean isBurst();
}