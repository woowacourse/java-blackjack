package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.Dealer;
import blackjack.domain.entry.Participant;
import blackjack.domain.entry.Player;
import blackjack.domain.entry.Players;

import blackjack.dto.CardCountingResult;
import blackjack.dto.FirstTurnCards;
import blackjack.dto.PlayerGameResult;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BlackjackTable {

    private final Deck deck;
    private final Players players;

    public BlackjackTable(List<String> names) {
        this.deck = Deck.of(Card.createDeck());
        this.players = new Players(createDealer(), toPlayers(names));
    }

    public void hit(Player player) {
        player.addCard(deck.draw());
    }

    public boolean needMoreCardByDealer() {
        return players.isHitDealer();
    }

    public void hitDealer() {
        players.hitDealer(deck.draw());
    }

    public boolean canHit(Player player, Command command) {
        return player.canHit() && command == Command.HIT;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public List<FirstTurnCards> getFirstTurnCards() {
        List<Participant> participants = players.getParticipant();
        return participants.stream()
            .map(participant -> new FirstTurnCards(participant.getName(), participant.openCard()))
            .collect(Collectors.toList());
    }

    public List<CardCountingResult> getCardCountingResult() {
        List<Participant> participants = players.getParticipant();
        return participants.stream()
            .map(this::toCardCountingResult)
            .collect(Collectors.toList());
    }

    public List<PlayerGameResult> getGameResults() {
        Map<PlayerOutcome, List<Player>> gameResults = players.getGameResults();
        return gameResults.keySet().stream()
            .flatMap(outcome -> toPlayerGameResult(gameResults, outcome))
            .collect(Collectors.toList());
    }

    private Dealer createDealer() {
        return new Dealer(HoldCards.initTwoCards(deck.draw(), deck.draw()));
    }

    private List<Player> toPlayers(List<String> names) {
        return names.stream()
            .map(name -> new Player(name, HoldCards.initTwoCards(deck.draw(), deck.draw())))
            .collect(Collectors.toList());
    }

    private CardCountingResult toCardCountingResult(Participant participant) {
        return new CardCountingResult(
            participant.getName(),
            participant.getHoldCards().getCards(),
            participant.countCards()
        );
    }

    private Stream<PlayerGameResult> toPlayerGameResult(
        Map<PlayerOutcome, List<Player>> gameResult,
        PlayerOutcome outcome
    ) {
        return gameResult.get(outcome).stream()
            .map(player -> new PlayerGameResult(player.getName(), outcome));
    }
}
