package domain;

import dto.ParticipantDto;
import java.util.ArrayList;
import java.util.List;

public record Participants(Dealer dealer, Players players) {

    public Player getPlayer(String name) {
        return players.getPlayer(name);
    }

    public ParticipantDto generateInitialDealerDto() {
        return new ParticipantDto(dealer.getName(), dealer.getOnlyFirstHand(), dealer.calculateScore());
    }

    public List<ParticipantDto> generatePlayersDto() {
        List<ParticipantDto> playersDto = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            playersDto.add(new ParticipantDto(player.getName(), player.getHand(), player.calculateScore()));
        }
        return playersDto;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public ParticipantDto generateDealerDto() {
        return new ParticipantDto(dealer.getName(), dealer.getHand(), dealer.calculateScore());
    }
}
