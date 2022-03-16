package domain.participant;

import static java.lang.Integer.compare;

public class Player extends Participant implements Comparable<Participant> {

    private static final int MAX_SCORE = 21;
    private static final int COMPARE_CRITERIA = 0;

    public Player(String name) {
        super(name);
    }

    public Result judgeResult(Dealer dealer) {
        if (compareTo(dealer) > COMPARE_CRITERIA) {
            return Result.WIN;
        }
        if (compareTo(dealer) < COMPARE_CRITERIA) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

    @Override
    public boolean canHit() {
        return cards.calculateSum() < MAX_SCORE;
    }


    @Override
    public int compareTo(Participant participant) {
        if (isBust()) {
            return -1;
        }

        if(participant.isBust()){
            return 1;
        }

        return compare(calculateScore(), participant.calculateScore());
    }
}
