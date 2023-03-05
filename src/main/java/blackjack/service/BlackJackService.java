package blackjack.service;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Participants;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import blackjack.dto.ParticipantStatusResponse;
import blackjack.dto.ParticipantTotalStatusResponse;
import blackjack.dto.PlayerGameResult;
import blackjack.dto.TotalGameResult;
import java.util.List;

public class BlackJackService {
    private static final int START_DRAW_COUNT = 2;

    private final Deck deck;
    private Participants participants;

    public BlackJackService(DeckGenerator deckGenerator) {
        this.deck = deckGenerator.generate();
    }

    public void createParticipants(List<String> names) {
        List<Player> players = names.stream()
                .map(name -> new Player(name, deck.drawCards(START_DRAW_COUNT)))
                .collect(toList());
        Dealer dealer = new Dealer(deck.drawCards(START_DRAW_COUNT));
        this.participants = new Participants(dealer, players);
    }

    public void drawMoreCardByName(String playerName) {
        Participant participant = participants.findByName(playerName);
        validateOverScore(participant);
        participant.addCard(deck.drawCard());
    }

    private void validateOverScore(Participant participant) {
        if (!participant.canDrawCard()) {
            throw new IllegalStateException("[ERROR] 더이상 카드를 뽑을 수 없습니다.");
        }
    }

    public boolean drawMoreCardForDealer() {
        Participant dealer = participants.getDealer();
        if (dealer.canDrawCard()) {
            dealer.addCard(deck.drawCard());
            return true;
        }
        return false;
    }

    public List<String> getPlayersName() {
        return participants.getPlayers().stream()
                .map(Participant::getName)
                .collect(toList());
    }

    public List<ParticipantStatusResponse> getStartStatusResponse() {
        return participants.getParticipants().stream()
                .map(ParticipantStatusResponse::ofStart)
                .collect(toList());
    }

    public List<ParticipantTotalStatusResponse> getAllParticipantTotalResponse() {
        return participants.getParticipants().stream()
                .map(ParticipantTotalStatusResponse::of)
                .collect(toList());
    }

    public ParticipantStatusResponse getParticipantStatusResponseByName(String name) {
        Participant participant = participants.findByName(name);
        return ParticipantStatusResponse.of(participant);
    }

    public TotalGameResult getTotalGameResult() {
        Dealer dealer = participants.getDealer();
        return participants.getPlayers()
                .stream()
                .map(player -> new PlayerGameResult(player.getName(), player.matchGame(dealer)))
                .collect(collectingAndThen(toList(), TotalGameResult::of));
    }
}
