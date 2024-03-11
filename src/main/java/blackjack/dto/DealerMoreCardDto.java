package blackjack.dto;

public record DealerMoreCardDto(String name, int count, int limitScore) {

    public static DealerMoreCardDto of(final String name, final int count, final int limitScore) {
        return new DealerMoreCardDto(name, count, limitScore);
    }
}
