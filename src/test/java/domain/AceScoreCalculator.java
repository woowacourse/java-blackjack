package domain;

public class AceScoreCalculator {
    private int min = Integer.MAX_VALUE;
    private int aceScore = 0;
    private int temp;

    private void dfs(int sum, int count, int limit) {
        if (count == 0) {
            if (min > limit - sum && limit - sum >= 0) {
                aceScore = sum - temp;
                min = limit - sum;
            }
            return;
        }

        dfs(sum + 1, count -1 , limit);
        dfs(sum + 11, count -1 , limit);
    }

    public int calculateAceScore(int sum, int count, int limit) {
        this.temp = sum;
        this.min = Integer.MAX_VALUE;
        dfs(sum, count, limit);
        return aceScore;
    }
}
