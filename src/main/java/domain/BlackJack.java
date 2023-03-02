package domain;

import domain.card.Card;
import domain.card.CardRepository;
import domain.player.Dealer;
import domain.player.Participant;
import domain.player.Player;
import domain.strategy.IndexGenerator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJack {
    private final CardRepository cardRepository;
    private final List<Participant> participants;
    private final Dealer dealer;

    public BlackJack(String playerNames) {
        cardRepository = CardRepository.create();
        participants = initParticipants(playerNames);
        dealer = new Dealer();
    }

    private List<Participant> initParticipants(String playerNames) {
        return Arrays.stream(playerNames.split(","))
                .map(Participant::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public void startGame(IndexGenerator indexGenerator) {
        giveCardToDealer(indexGenerator);
        giveCardToParticipants(indexGenerator);
    }

    private void giveCardToDealer(IndexGenerator indexGenerator) {
        for (int divideCardCount = 0; divideCardCount < 2; divideCardCount++) {
            Card card = cardRepository.findCardByIndex(indexGenerator.generate(cardRepository.size()));
            dealer.addCard(card);
        }
    }

    private void giveCardToParticipants(IndexGenerator indexGenerator) {
        for (Participant participant : participants) {
            giveCardToPerParticipant(indexGenerator, participant);
        }
    }

    private void giveCardToPerParticipant(IndexGenerator indexGenerator, Participant participant) {
        for (int divideCardCount = 0; divideCardCount < 2; divideCardCount++) {
            Card card = cardRepository.findCardByIndex(indexGenerator.generate(cardRepository.size()));
            participant.addCard(card);
        }
    }

    public Map<Player, List<Card>> getPlayersCards() {
        HashMap<Player, List<Card>> cardsPerPlayer = new HashMap<>();
        putDealerCards(cardsPerPlayer);
        putParticipantsCards(cardsPerPlayer);
        return cardsPerPlayer;
    }

    private void putDealerCards(HashMap<Player, List<Card>> cardsPerPlayer) {
        cardsPerPlayer.put(dealer, dealer.getCards());
    }

    private void putParticipantsCards(HashMap<Player, List<Card>> cardsPerPlayer) {
        participants.forEach(participant -> cardsPerPlayer.put(participant, participant.getCards()));
    }
}
