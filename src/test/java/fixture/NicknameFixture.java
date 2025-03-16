package fixture;

import blackjack.gamer.Nickname;

public class NicknameFixture {
    public static Nickname createNickname(String name) {
        return new Nickname(name);
    }
}
