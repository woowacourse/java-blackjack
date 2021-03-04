package blackjack.domain.user;

public class Result {

    private static final String WIN = "승";
    private static final String TIE = "무";
    private static final String LOSE = "패";

    private Result() {}

    public static String getResult(User targetUser, User compareUser) {
        if (targetUser.isBustCondition() && compareUser.isBustCondition()) {
            return WIN;
        }
        return compareScore(targetUser, compareUser);
    }

    private static String compareScore(User dealer, User player) {
        int compareResult = dealer.compare(player);
        if (compareResult == 0) {
            return TIE;
        }
        if (compareResult > 0) {
            return WIN;
        }
        return LOSE;
    }
}
