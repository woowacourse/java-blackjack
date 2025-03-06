package model;

public class Score implements Comparable {
  private final int score;

  public Score(int score) {
    this.score = score;
  }

  public int getScore() {
    return score;
  }

  @Override
  public int compareTo(Object o) {
    return Integer.compare(this.score, ((Score) o).score);
  }

}
