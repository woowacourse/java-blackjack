package blackjack_statepattern;

import blackjack_statepattern.card.Card;
import blackjack_statepattern.dto.CardsDto;
import blackjack_statepattern.participant.Dealer;
import blackjack_statepattern.participant.Participant;
import blackjack_statepattern.participant.Player;
import blackjack_statepattern.participant.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class BlackjackBoard {

    private final Dealer dealer;
    private final Players players;

    public BlackjackBoard(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public CardsDto getInitialCardsDto() {
        Map<Participant, List<Card>> participantsCards = new LinkedHashMap<>();
        participantsCards.put(dealer, List.of(dealer.getOneCard()));
        for (Player player : getPlayers()) {
            participantsCards.put(player, player.getCardsValue());
        }
        return CardsDto.of(participantsCards);
    }

    public CardsDto getFinalCardsDto() {
        Map<Participant, List<Card>> participantsCards = new LinkedHashMap<>();
        participantsCards.put(dealer, dealer.getCardsValue());
        for (Player player : getPlayers()) {
            participantsCards.put(player, player.getCardsValue());
        }
        return CardsDto.of(participantsCards);
    }
}
