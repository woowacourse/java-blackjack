package blackjack.controller.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;

import java.util.List;
import java.util.stream.Collectors;

public class ParticipantsResponseDto {

    private final ParticipantResponseDto dealer;
    private final List<ParticipantResponseDto> players;

    public ParticipantsResponseDto(ParticipantResponseDto dealer, List<ParticipantResponseDto> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static ParticipantsResponseDto of(Dealer dealer, Players players) {
        ParticipantResponseDto dealerDto = ParticipantResponseDto.from(dealer);
        List<ParticipantResponseDto> playersDto = createParticipantResponseDtos(players);
        return new ParticipantsResponseDto(dealerDto, playersDto);
    }

    private static List<ParticipantResponseDto> createParticipantResponseDtos(Players players) {
        return players.stream()
                .map(ParticipantResponseDto::from)
                .collect(Collectors.toList());
    }

    public ParticipantResponseDto getDealer() {
        return dealer;
    }

    public List<ParticipantResponseDto> getPlayers() {
        return players;
    }
}
