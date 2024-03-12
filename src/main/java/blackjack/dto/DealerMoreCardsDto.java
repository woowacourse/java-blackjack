package blackjack.dto;

public record DealerMoreCardsDto(String name, int count, int limitScore) {

    public static DealerMoreCardsDto of(final String name, final int count, final int limitScore) {
        return new DealerMoreCardsDto(name, count, limitScore);
    }
}
