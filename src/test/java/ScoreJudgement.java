public class ScoreJudgement {

    public boolean isOverCriterion(int total) {
        return total <= 21;
    }

    public int judge(int total1, int total2) {
        if (21 - total1 > 21 - total2) {
            return total2;
        }
        return total1;
    }
}
