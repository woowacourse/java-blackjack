package domain;

public record Betting(int amount) {
    public Betting {
        if (amount < 1000 || amount > 300_000_000) {
            throw new IllegalArgumentException("1회 베팅 한도는 3억원 이하 1,000원 이상입니다.");
        }
    }
}
