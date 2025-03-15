package domain.participant;

import domain.Cards;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import java.util.Arrays;
import java.util.List;

public class ParticipantFactory {

    public static Participant createParticipantCardsOfRanks(List<Rank> ranks) {
        Participant participant = createParticipant("행성");
        ranks.stream()
                .map(rank -> new Card(rank, Suit.DIAMOND))
                .forEach(participant::addCard);
        return participant;
    }

    public static Dealer createDealerCardsOfRanks(List<Rank> ranks) {
        Dealer dealer = new Dealer();
        ranks.stream()
                .map(rank -> new Card(rank, Suit.DIAMOND))
                .forEach(dealer::addCard);
        return dealer;
    }

    public static Player createPlayerCardsOfRanks(List<Rank> ranks) {
        Player player = new Player("행성");
        ranks.stream()
                .map(rank -> new Card(rank, Suit.DIAMOND))
                .forEach(player::addCard);
        return player;
    }

    public static Cards createCardsOfRanks(List<Rank> ranks) {
        Cards cardsOfRanks = new Cards();
        ranks.stream()
                .map(rank -> new Card(rank, Suit.DIAMOND))
                .forEach(cardsOfRanks::addCard);
        return cardsOfRanks;
    }

    public static List<Card> createCardListOfRanks(List<Rank> ranks) {
        return ranks.stream()
                .map(rank -> new Card(rank, Suit.DIAMOND))
                .toList();
    }

    public static List<Rank> createRanks(String rankNames) {
        return Arrays.stream(rankNames.split(","))
                .map(Rank::valueOf)
                .toList();
    }

    private static Participant createParticipant(String name) {
        return new Participant(name) {
            @Override
            public List<Card> getInitialCards() {
                return List.of();
            }

            @Override
            public boolean ableToAddCard() {
                return false;
            }
        };
    }
}
