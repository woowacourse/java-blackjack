package blackjack.domain;

import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.List;

public class BlackJackGame {
    private final Participants participants;
    private final Deck deck;

    public BlackJackGame(Participants participants, Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }

    public List<Name> getPlayerNames() {
        return participants.getPlayers().stream()
                .map(Participant::getName)
                .collect(toList());
    }

    public void drawMoreCardForPlayer(Player player) {
        if (!player.canDrawCard()) {
            throw new IllegalStateException("[ERROR] 더이상 카드를 뽑을 수 없습니다.");
        }
        player.addCard(deck.drawCard());
    }

    public boolean drawMoreCardForDealer() {
        Dealer dealer = participants.getDealer();
        if (dealer.canDrawCard()) {
            dealer.addCard(deck.drawCard());
            return true;
        }
        return false;
    }

    public ParticipantProfits getParticipantProfits() {
        return ParticipantProfits.of(participants.getPlayers(), participants.getDealer());
    }

    public List<Participant> getParticipants() {
        return participants.getParticipants();
    }
}
