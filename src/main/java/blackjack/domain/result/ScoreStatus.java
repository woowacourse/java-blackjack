package blackjack.domain.result;

//TODO: 네이밍 고려하기 WinningStatus랑 차이가 뭔지, 또 Score에서 들고 있는게 맞는지 생각해보기
public enum ScoreStatus {
    BLACKJACK,
    DRAW_AVAILABLE,
    BUST
}
