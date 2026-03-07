package service;

public record FinalResult(
        String name,
        int win,
        int draw,
        int lose,
        boolean isDealer
) {
}
