package blackjack.domain.blackjack;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.rule.ScoreRule;
import blackjack.dto.DealerResultDto;
import blackjack.dto.ScoreResultDto;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {

    private final Deck deck;
    private final Participants participants;

    public BlackJackGame(final Deck deck, final List<String> players, ScoreRule scoreRule) {
        this.deck = deck;
        this.participants = createParticipants(players, scoreRule);
    }

    private Participants createParticipants(final List<String> names, final ScoreRule scoreRule) {
        List<Player> players = names.stream()
                .map(name -> new Player(name, scoreRule))
                .collect(Collectors.toList());
        return new Participants(players, new Dealer(scoreRule));
    }

    public void handInitCards() {
        participants.dealCardsAllParticipants(deck);
    }

    public void playDealerTurn() {
        Dealer dealer = getDealer();
        if (dealer.isReceiveCard()) {
            dealer.receiveCard(deck.draw());
        }
    }

    public boolean isExistWaitingPlayer() {
        List<Player> players = participants.extractPlayers();
        return players.stream()
                .anyMatch(player -> player.isReceiveCard() && player.isContinue());
    }

    public Player findCurrentTurnPlayer() {
        List<Player> players = participants.extractPlayers();
        return players.stream()
                .filter(player -> player.isReceiveCard() && player.isContinue())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("턴이 남은 플레이어가 존재하지 않습니다."));
    }

    public Participants getParticipants() {
        return participants;
    }

    public Dealer getDealer() {
        return participants.extractDealer();
    }

    public DealerResultDto getDealerResult() {
        return getDealer().getDealerResult(participants.extractPlayers());
    }

    public List<ScoreResultDto> getPlayersResults() {
        return getDealer().decideWinOrLoseResults(participants.extractPlayers());
    }

    public void askMoreCard(final boolean isNeedCard) {
        Player currentPlayer = findCurrentTurnPlayer();
        if (isNeedCard) {
            currentPlayer.receiveCard(deck.draw());
            return;
        }

        currentPlayer.endOwnTurn();
    }

    public List<Player> getPlayers() {
        return participants.extractPlayers();
    }
}
