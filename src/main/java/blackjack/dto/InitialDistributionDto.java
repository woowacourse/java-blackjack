package blackjack.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.GameParticipants;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InitialDistributionDto {

    private final List<ParticipantCardsDto> participantsInfo = new ArrayList<>();

    public InitialDistributionDto(Dealer dealer, List<Player> players) {
        initDealerInfo(dealer);
        initPlayerInfos(players);
    }

    private void initDealerInfo(Dealer dealer) {
        ParticipantCardsDto dealerCardInfo = ParticipantCardsDto.ofInitialDealer(dealer);
        participantsInfo.add(dealerCardInfo);
    }

    private void initPlayerInfos(List<Player> players) {
        players.forEach(this::addPlayerInfo);
    }

    private void addPlayerInfo(Player player) {
        ParticipantCardsDto playerCardsInfo = ParticipantCardsDto.of(player);
        participantsInfo.add(playerCardsInfo);
    }

    public List<ParticipantCardsDto> getParticipantsInfo() {
        return Collections.unmodifiableList(participantsInfo);
    }

    public List<String> getPlayerNames() {
        return participantsInfo.stream()
                .filter(ParticipantCardsDto::isPlayer)
                .map(ParticipantCardsDto::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String toString() {
        return "InitialDistributionDto{" +
                "participantsInfo=" + participantsInfo +
                '}';
    }
}
