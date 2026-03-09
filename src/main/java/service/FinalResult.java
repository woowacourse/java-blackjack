package service;

public record FinalResult(
        String name,
        int win, // FIXME: 동사가 아니라 명사형으로 네이밍 수정
        int draw,
        int lose,
        boolean isDealer
) {
}
