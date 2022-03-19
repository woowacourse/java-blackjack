package blackjack_statepattern;

import blackjack_statepattern.card.Card;
import blackjack_statepattern.dto.CardDto;
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

    public CardDto getCardsDto() {
        Map<Participant, List<Card>> participantsCards = new LinkedHashMap<>();
        participantsCards.put(dealer, List.of(dealer.getOneCard()));
        for (Player player : getPlayers()) {
            participantsCards.put(player, player.getCards());
        }
        return CardDto.of(participantsCards);
    }
}
