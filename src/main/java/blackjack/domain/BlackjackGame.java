package blackjack.domain;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Participant;
import blackjack.domain.user.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private final CardDeck cardDeck;
    private final List<Participant> participants;

    public BlackjackGame(final CardDeck cardDeck, final List<Participant> participants) {
        validateNumberOfDealer(participants);

        this.cardDeck = cardDeck;
        this.participants = participants;
    }

    public static BlackjackGame createByPlayerNames(final List<String> names) {
        CardDeck cardDeck = CardDeck.shuffleCardDeck();
        List<Participant> participants = new ArrayList<>();

        participants.add(new Dealer());
        for (String name : names) {
            Player player = new Player(name);
            participants.add(player);
        }
        return new BlackjackGame(cardDeck, participants);
    }

    public void initCardsToParticipants() {
        for (Participant participant : participants) {
            Card card1 = cardDeck.pickRandomCard();
            Card card2 = cardDeck.pickRandomCard();
            participant.addCards(card1, card2);
        }
    }

    public void addExtraCard(final Participant participant) {
        Card card = cardDeck.pickRandomCard();
        participant.addCards(card);
    }

    public Dealer findDealer() {
        return participants.stream()
            .filter(participant -> participant instanceof Dealer)
            .map(participant -> (Dealer) participant)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("딜러가 존재하지 않습니다."));
    }

    public List<Player> findPlayers() {
        return participants.stream()
            .filter(participant -> participant instanceof Player)
            .map(participant -> (Player) participant)
            .toList();
    }

    public Map<Player, GameResult> calculateStatisticsForPlayer() {
        Map<Player, GameResult> playerResult = new HashMap<>();
        Dealer dealer = findDealer();

        for (Player player : findPlayers()) {
            GameResult gameResult = GameResult.playerResultFrom(dealer, player);
            playerResult.put(player, gameResult);
        }
        return playerResult;
    }

    public Map<GameResult, Integer> calculateStatisticsForDealer() {
        Map<GameResult, Integer> result = new HashMap<>();
        Dealer dealer = findDealer();

        for (Player player : findPlayers()) {
            GameResult playerResult = GameResult.playerResultFrom(dealer, player);
            GameResult dealerResult = playerResult.changeStatusOpposite();
            result.put(dealerResult, result.getOrDefault(dealerResult, 0) + 1);
        }
        return result;
    }

    public List<String> getPlayerNames() {
        return findPlayers().stream()
            .map(Player::getName)
            .toList();
    }

    private void validateNumberOfDealer(List<Participant> participants) {
        long dealerCount = participants.stream()
            .filter(Dealer.class::isInstance)
            .count();

        if (dealerCount != 1) {
            throw new IllegalArgumentException("딜러는 한 명이어야 합니다.");
        }
    }
}
