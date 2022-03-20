package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardStack;
import blackjack.domain.card.Hand;
import blackjack.domain.money.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.ParticipantGroup;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.PlayerCreateDto;
import java.util.List;
import java.util.stream.Collectors;

// TODO: 플레이어가 등록되지 않을 경우 동작하지 않아야함
public class BlackjackGame {

    private final CardStack cardDeck;
    private final ParticipantGroup participantGroup;

    public BlackjackGame(CardStack cardDeck, List<PlayerCreateDto> playerCreateDtos) {
        this.cardDeck = cardDeck;
        this.participantGroup = ParticipantGroup.of(Dealer.of(generateInitialHand()),
                generatePlayers(playerCreateDtos));
    }

    private Players generatePlayers(List<PlayerCreateDto> playerCreateDtos) {
        List<Player> rawPlayers = playerCreateDtos.stream()
                .map(dto -> generatePlayer(dto.getPlayerName(), dto.getBetMoney()))
                .collect(Collectors.toList());

        return Players.of(rawPlayers);
    }

    public Player generatePlayer(String playerName, int betMoney) {
        return Player.of(playerName, generateInitialHand(), Money.from(betMoney));
    }

    private Hand generateInitialHand() {
        return Hand.of(cardDeck.pop(), cardDeck.pop());
    }

    public boolean giveExtraCardToPlayer(Player player) {
        player.receiveCard(cardDeck.pop());
        return player.canReceive();
    }

    public int giveExtraCardsToDealer() {
        Dealer dealer = participantGroup.getDealer();

        int extraCardCount = 0;
        while (dealer.canReceive()) {
            dealer.receiveCard(cardDeck.pop());
            extraCardCount++;
        }

        return extraCardCount;
    }

    public Card popCard() {
        return cardDeck.pop();
    }

    public Dealer getDealer() {
        return participantGroup.getDealer();
    }

    public Players getPlayers() {
        return participantGroup.getPlayers();
    }

    public ParticipantGroup getParticipantGroup() {
        return participantGroup;
    }

    @Override
    public String toString() {
        return "BlackjackGame{" +
                "cardDeck=" + cardDeck +
                ", participantGroup=" + participantGroup +
                '}';
    }
}
