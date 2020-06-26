import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static String course[][] = null;
    static Scanner sc = new Scanner(System.in);
    static int nbreCoureurs = 0;
    static String infosHeaders[] = {"Nom", "Numero", "Etat", "Temps"};
    final static SimpleDateFormat FORMATTER = new SimpleDateFormat("HH:mm:ss");
    final static Calendar CALENDAR = Calendar.getInstance();


    public static void main(String[] args) {

        System.out.println("|\t Nom \t|\t N° \t|\t Etat \t\t|\t Temps \t|");
        System.out.println(
                "-----------------------------------------------------------------");
        System.out.println("|\t david \t|\t 33 \t|\t arrivé \t|\t 12h23 \t|");

        System.out.println("####################################################################");
        System.out.println("###############  Bienvenue au Tour de France 2020  #################");
        System.out.println("####################################################################");
        System.out.println("Saisissez le nombre de coureurs :");
        nbreCoureurs = sc.nextInt();
        sc.nextLine();

        System.out.println("Vous avez saisi " + nbreCoureurs + " coureurs.");
        course = new String[nbreCoureurs][4];

        System.out.println("Nous allons remplir le tableau des coureurs");
        String infos = "";
        String infosTab[] = new String[4];
        for (int i = 0; i < nbreCoureurs; i++) {
            System.out.println("Veuillez saisir les informations du coureur N°" + (i + 1)
                    + " sous format: Nom,Numéro,Etat (arrivé ou abandon ou disqualification)");
            infos = sc.nextLine();
            infos += "," + FORMATTER.format(CALENDAR.getTime() ) + ",";
            System.out.println(infos);
            infosTab = infos.trim().split(",");
            course[i] = infosTab;

        }

        afficherTableau();
        boolean mainLoop = true;

        int choice;
        do {
            System.out.println("Tour de france 2020\n");
            System.out.print("0.) Quitter \n");
            System.out.print("1.) Afficher les coureurs.\n");
            System.out.print("2.) Afficher le classement provisoire.\n");
            System.out.print("3.) Enregistrer une arrivée.\n");
            System.out.print("4.) Enregistrer un abandon.\n");
            System.out.print("5.) Enregistrer une disqualification.\n");
            System.out.print("6.) Enregistrer le temps d'arrivée d'un coureur.\n");
            System.out.print("7.) Obtenir le temps d'un coureur.\n");
            System.out.print("8.) Obtenir l'écart de temps entre deux coureurs donnés\n");
            System.out.print("\nEnter Your Menu Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("Fermeture du programme...");
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("choice 1");
                    afficherTableau();
                    break;
                case 2:
                    // do something
                    System.out.println("Classement provisoire");
                    break;

                case 3:
                    // do something
                    System.out.println("Enregistrer une arrivée");
                    break;

                case 4:
                    // do something
                    System.out.println("Enregistrer un abandon");
                    break;

                case 5:
                    // do something
                    System.out.println("choice 5");
                    break;

                case 6:
                    enregistrerTempsCoureur();
                    break;
                default:
                    System.out.println(choice + " is not a valid Menu Option! Please Select Another.");

            }
        } while (choice != 0 /* Quitter la loupe quand le choix = 0 */);

    }

    public static void afficherTableau() {
        // C1,22,arrive,17h30
        // C2,33,arrive,17h33
        System.out.println("|\t Nom \t|\t N° \t|\t Etat \t\t|\t Temps \t|");
        System.out.println("----------------------------------------------------------------------");
        for (int i = 0; i < nbreCoureurs; i++) {
            for (int j = 0; j < infosHeaders.length; j++) {
                System.out.printf("|\t %s \t ", course[i][j]);

            }
//			System.out.println();
            System.out.println("\n---------------------------------------------------------------------");
        }
    }

    public static void enregistrerTempsCoureur() {
        afficherTableau();
        System.out.println("Saisir le numéro du coureur à enregistrer");
        String numero = sc.nextLine();
        int indice = find(course, numero);
        // if indice < course.length : => l'indice existe
        if (indice != -1) {
            System.out.println("Saisir le temps d'arrivée au format hh:mm:ss");
            String temps = sc.nextLine();
            course[indice][3] = temps;

            afficherTableau();
        } else {
            System.out.println("Le numéro que vous avez saisi n'existe pas !");
        }
    }

    // Fonction qui trouve l'indice d'un element dans un tableau java
    public static int find(String[][] a, String target)
    {
        for (int i = 0; i < a.length; i++)
            if (a[i][1].equals(target))
                return i;

        return -1;
    }

}
