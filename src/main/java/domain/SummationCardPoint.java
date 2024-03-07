package domain;

import java.util.List;

public record SummationCardPoint(int summationCardPoint) {

    // TODO: 디폴트 생성자의 제한자가 public으로 되어버리는데, class로 만드는 것이 더 낫지 않을까
    // TODO: record로 해버리니 get 함수 이름이 .summationCardPoint가 되어 버리는데, 이를 사용하는 곳에서 가시성이 많이 떨어짐
    public static SummationCardPoint of(List<CardPoint> cardPoints) {
        int summationCardPoint = cardPoints.stream()
                .mapToInt(CardPoint::point)
                .sum();
        return new SummationCardPoint(summationCardPoint);
    }

    public boolean isBiggerThan(SummationCardPoint other) {
        int otherPoint = other.summationCardPoint();
        return summationCardPoint > otherPoint;
    }
}
