package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private static final int BLACKJACK_VALUE = 21;
    
    private final CardDeck cardDeck;
    private final List<Participant> participants;

    public BlackjackGame(CardDeck cardDeck, List<Participant> participants) {
        this.cardDeck = cardDeck;
        this.participants = participants;
    }

    public static BlackjackGame createByPlayerNames(List<String> names) {
        CardDeck cardDeck = CardDeck.createCardDeck();
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
            // TODO : 생각해보기 - Deck에게 몇 장 받을지를 보내서 그 수 만큼 받아온다.
            Card card1 = cardDeck.pickRandomCard();
            Card card2 = cardDeck.pickRandomCard();
            participant.addCards(card1, card2);
        }
    }

    public void addExtraCard(Participant participant) {
        // TODO : 생각해보기 - Deck에게 몇 장 받을지를 보내서 그 수 만큼 받아온다.
        Card card = cardDeck.pickRandomCard();
        participant.addCards(card);
    }

    public boolean addExtraCardToDealer() {
        Dealer dealer = findDealer();
        if (dealer.isPossibleToAdd()) {
            addExtraCard(dealer);
            return true;
        }
        return false;
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

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public List<String> getPlayerNames() {
        return findPlayers().stream()
                .map(Player::getName)
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
            GameResult gameResult = GameResult.playerResultFrom(dealer, player);
            if (gameResult == GameResult.WIN) {
                result.put(GameResult.LOSE, result.getOrDefault(GameResult.LOSE, 0) + 1);
            }
            if (gameResult == GameResult.LOSE) {
                result.put(GameResult.WIN, result.getOrDefault(GameResult.WIN, 0) + 1);
            }
            if (gameResult == GameResult.DRAW) {
                result.put(GameResult.DRAW, result.getOrDefault(GameResult.DRAW, 0) + 1);
            }
        }
        return result;
    }
}
