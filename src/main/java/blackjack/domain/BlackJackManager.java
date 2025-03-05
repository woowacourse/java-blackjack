package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackManager {

    private final CardDeck cardDeck;
    private final List<Participant> participants;

    public BlackJackManager(CardDeck cardDeck, List<Participant> participants) {
        this.cardDeck = cardDeck;
        this.participants = participants;
    }

    public static BlackJackManager createByPlayerNames(List<String> names) {
        CardDeck cardDeck = CardDeck.createCardDeck();

        List<Participant> participants = new ArrayList<>();
        participants.add(new Dealer());
        for (String name : names) {
            Player player = new Player(name);
            participants.add(player);
        }
        return new BlackJackManager(cardDeck, participants);
    }

    public void initCardsToParticipants() {
        for (Participant participant : participants) {
            Card card1 = cardDeck.pickRandomCard();
            Card card2 = cardDeck.pickRandomCard();
            participant.addCards(card1, card2);
        }
    }

    public void addExtraCard(Participant participant) {
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

    private Dealer findDealer() {
        return participants.stream()
                .filter(participant -> participant instanceof Dealer)
                .map(participant -> (Dealer) participant)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("딜러가 존재하지 않습니다."));
    }

    public Map<GameResult, Integer> calculateStatisticsForDealer() {
        Map<GameResult, Integer> result = new HashMap<>();
        Dealer dealer = findDealer();

        for(Participant participant : participants) {
            if(participant instanceof Dealer) {
                continue;
            }
            Player player = (Player) participant;
            GameResult gameResult = player.matchGame(dealer);
            if(gameResult == GameResult.WIN) {
                result.put(GameResult.LOSE, result.getOrDefault(GameResult.LOSE, 0) + 1);
            }
            if(gameResult == GameResult.LOSE) {
                result.put(GameResult.WIN, result.getOrDefault(GameResult.WIN, 0) + 1);
            }
            if(gameResult == GameResult.DRAW) {
                result.put(GameResult.DRAW, result.getOrDefault(GameResult.DRAW, 0) + 1);
            }
        }

        return result;
    }

    public List<String> getPlayerNames() {
        return participants.stream()
                .filter(participant -> participant instanceof Player)
                .map(participant -> (Player) participant)
                .map(Player::getName)
                .toList();
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }
}
