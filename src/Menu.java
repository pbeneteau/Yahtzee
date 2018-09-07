
import static java.lang.System.*;

public class Menu {


    public void showStartMenu() {

        // Display menu graphics
        out.println("\n\t\t\t\t\t============================");
        out.println("\t\t\t\t\t|           START          |");
        out.println("\t\t\t\t\t============================");
        out.println("\t\t\t\t\t| 1. Play                  |");
        out.println("\t\t\t\t\t| 2. Rules                 |");
        out.println("\t\t\t\t\t| 3. Exit                  |");
        out.println("\t\t\t\t\t============================");

        out.print("\nChoice: ");
    }

    public void showNumberOfPlayerMenu() {

        out.println("\nGame Mode");

        out.println("1. Solo");
        out.println("2. Multiplayer");

        out.print("\nChoice: ");
    }
}
