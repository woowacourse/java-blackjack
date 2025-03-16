package testFixture;

import domain.participants.PlayerName;
import org.junit.jupiter.api.BeforeAll;

public class PlayerNameFixture {
    public static PlayerName playerNameA;
    public static PlayerName playerNameB;
    public static PlayerName playerNameC;
    public static PlayerName playerNameD;
    public static PlayerName playerNameE;
    public static PlayerName playerNameF;

    @BeforeAll
    public static void setUp(){
        playerNameA = new PlayerName("a");
        playerNameB = new PlayerName("b");
        playerNameC = new PlayerName("c");
        playerNameD = new PlayerName("d");
        playerNameE = new PlayerName("e");
        playerNameF = new PlayerName("f");
    }
}
