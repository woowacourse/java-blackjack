package controller.converter;

public enum PlayerResultText {
  Win(true, "승"),
  Lose(false, "패");


  private final boolean isWin;
  private final String text;

  PlayerResultText(boolean isWin, String text) {
    this.isWin = isWin;
    this.text = text;
  }

  public String getText() {
    return text;
  }


  public boolean isWin() {
    return isWin;
  }

  public static String convertBooleanToText(boolean isWin) {
    for (PlayerResultText result : PlayerResultText.values()) {
      if (result.isWin() == isWin) {
        return result.getText();
      }
    }
    throw new IllegalArgumentException("잘못된 입력입니다.");
  }
}
