package domain;

import static domain.CardFixture.*;

import java.util.List;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/04
 */
public class PlayerFixture {

    public static final Player 우가 = new Player(new Name("우가"), new Cards(List.of(하트에이스, 하트2)));
    public static final Player 하마드 = new Player(new Name("하마드"), new Cards(List.of(하트에이스, 하트3)));
    public static final Player 빙봉 = new Player(new Name("빙봉"), new Cards(List.of(하트에이스, 하트10)));
}
