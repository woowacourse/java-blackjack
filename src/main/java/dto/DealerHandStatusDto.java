package dto;

import domain.participant.Dealer;

import java.util.List;

public record DealerHandStatusDto(List<PlayingCardDto> playingCards, int playingCardSum) {

    public static DealerHandStatusDto of(final Dealer dealer) {
        List<PlayingCardDto> playingCars = dealer.getHandCards()
                .stream()
                .map(PlayingCardDto::of)
                .toList();
        return new DealerHandStatusDto(playingCars, dealer.getHandSum());
    }
}
