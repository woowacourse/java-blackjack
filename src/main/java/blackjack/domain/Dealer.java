package blackjack.domain;

public class Dealer extends Participant {

    private static final int MAXIMUM_SCORE_FOR_ADDITIONAL_CARD = 16;
    private static final long DEFAULT_VALUE = 0L;
    private static final String DEALER_NAME = "딜러";
    private static final int INCREASE_COUNT = 1;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isAbleToReceiveCard() {
        int score = calculateFinalScore();
        return score <= MAXIMUM_SCORE_FOR_ADDITIONAL_CARD;
    }

//    public Map<Result, Long> getStatisticResult(List<Player> players) {
//        Map<Result, Long> statisticResult = new EnumMap<>(Result.class);
//        for (Player player : players) {
//            Result result = player.judgeResult(this);
//            Result replacedResult = result.replaceWinWithLose();
//            statisticResult.put(replacedResult,
//                statisticResult.getOrDefault(replacedResult, DEFAULT_VALUE) + INCREASE_COUNT);
//        }
//        return statisticResult;
//    }
}
