package techcourse.jcf.mission;

public class Main {

    public static void main(String[] args) {

        final var laserPrinter = new LaserPrinter();

        final SimpleList<Printer> printers = new SimpleArrayList<Printer>();
        final SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<LaserPrinter>(laserPrinter);

        SimpleList.copy(laserPrinters, printers);

        System.out.println(printers.get(0) == laserPrinter);
        System.out.println("from : " + laserPrinter);
        System.out.println("to : " + printers.get(0));
    }

}
