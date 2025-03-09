package blackjack.gamer;

public class GameParticipantFixture {

    public static Player createPlayer(String nickname) {
        return Player.from(Nickname.from(nickname));
    }

    public static Dealer createDealer() {
        return createDealer(1);
    }

    public static Dealer createDealer(int playerCount) {
        return Dealer.create(playerCount);
    }
}
