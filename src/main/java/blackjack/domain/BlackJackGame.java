package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.*;
import blackjack.domain.result.ProfitResult;
import blackjack.dto.ParticipantDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {
    private Players players;
    private Dealer dealer;
    private Deck deck;

    public void initialSetting(List<String> playersName) {
        players = generatePlayers(playersName);
        players.bettingEachPlayer();
        dealer = new Dealer();
        List<Card> cards = new ArrayList<>(Card.values());
        Collections.shuffle(cards);
        deck = new Deck(cards);
    }

    private Players generatePlayers(List<String> allPlayersName) {
        return new Players(allPlayersName.stream()
                .map(Nickname::new)
                .map(Player::new)
                .collect(Collectors.toList()));
    }

    public void distributeCards() {
        dealer.firstDraw(deck.drawCard(), deck.drawCard());
        players.eachPlayerFirstDraw(deck);
    }

    public Card drawOneCard() {
        return deck.drawCard();
    }

    public ProfitResult profitResult() {
        ProfitResult profitResult = new ProfitResult();
        profitResult.calculateProfit(players.verifyResultByCompareScore(dealer), dealer);
        return profitResult;
    }

    public List<ParticipantDto> playersDto() {
        return players.getPlayers().stream()
                .map(this::toParticipantDto)
                .collect(Collectors.toList());
    }

    private ParticipantDto toParticipantDto(Participant participant) {
        Cards cards = participant.getCurrentCards();
        return new ParticipantDto(participant.getName(), cards, cards.calculateScore());
    }

    public ParticipantDto dealerDto() {
        return toParticipantDto(dealer);
    }

    public ParticipantDto playerDto(Player player) {
        return toParticipantDto(player);
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
