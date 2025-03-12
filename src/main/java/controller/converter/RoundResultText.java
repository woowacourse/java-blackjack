package controller.converter;

public enum RoundResultText {
  Win(true, "승"),
  Lose(false, "패");


  private final boolean isWin;
  private final String text;

  RoundResultText(boolean isWin, String text) {
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
    for (RoundResultText result : RoundResultText.values()) {
      if (result.isWin() == isWin) {
        return result.getText();
      }
    }
    throw new IllegalArgumentException("잘못된 입력입니다.");
  }
}
