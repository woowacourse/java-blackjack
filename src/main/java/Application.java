public class Application {

    public static void main(String[] args) {
        GameMachine gameMachine = new GameMachine();
        gameMachine.ready();
        gameMachine.play();
        gameMachine.printResult();
    }
}
