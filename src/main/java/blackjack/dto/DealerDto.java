package blackjack.dto;

import blackjack.domain.player.Dealer;

public class DealerDto {

    private final CardDto firstCard;
    private final String name;

    public DealerDto(CardDto firstCard, String name) {
        this.firstCard = firstCard;
        this.name = name;
    }

    public static DealerDto from(Dealer dealer) {
        CardDto cardDto = CardDto.from(dealer.getCards().getFirstCard());
        String name = dealer.getName();
        return new DealerDto(cardDto, name);
    }

    public CardDto getCard() {
        return firstCard;
    }

    public String getName() {
        return name;
    }
}
