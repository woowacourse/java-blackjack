package blackjack.domain;

import blackjack.domain.card.Score;

public enum Outcome {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String word;

    Outcome(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
/*
- 승
  - 공통조건: 이기려면 카드 합계는 21 이하여야 한다.
  - 블랙잭: 카드 합계가 21
  - 블랙잭 아닌 경우: 상대보다 점수가 높은 경우
- 무
  - 점수가 같은 경우
  - 둘다 21이 초과되는 경우
- 패
  - `승`,`무` 이외의 경우

 */
    public static Outcome getInstance(final Score base, final Score counterpart) {
        if (draw(base, counterpart)) {
            return Outcome.DRAW;
        }

        if (win(base, counterpart)) {
            return Outcome.WIN;
        }

        return Outcome.LOSE;
    }

    private static boolean draw(final Score base, final Score counterpart) {
        if (base.isBurst() && counterpart.isBurst()) {
            return true;
        }
        if(base.isBlackJack() && counterpart.isBlackJack()){
            return true;
        }
        if (!base.isBurst() && !counterpart.isBurst() && base.isSameAs(counterpart)) {
            return true;
        }
        return false;
    }

    private static boolean win(final Score base, final Score counterpart) {
        if (base.isBlackJack() && !counterpart.isBlackJack()) {
            return true;
        }
        if(!base.isBurst() && counterpart.isBurst()){
            return true;
        }
        if (!base.isBurst() && base.isHigherThan(counterpart)) {
            return true;
        }
        return false;
    }
}
