package domain;

// FIXME: 변수가 5개 - GameResult 만들었으니 그거랑 count만 두면 될 듯 - 그래도 4개긴 함 - DealerResult랑 PlayerResult 분리?
public record FinalResult(
        String name,
        int winCount,
        int drawCount,
        int loseCount,
        boolean isDealer
) {
}
