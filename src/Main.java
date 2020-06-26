import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class Main {
    static String course[][] = null;
    static Scanner sc = new Scanner(System.in);
    static int nbreCoureurs = 0;
    static String infosHeaders[] = {"Nom", "Numero", "Etat", "Temps"};
    final static SimpleDateFormat FORMATTER = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) throws ParseException {

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
            Date date = new Date(System.currentTimeMillis());
            String time = FORMATTER.format(date);
            infos += "," + time;

            infosTab = infos.trim().split(",");
            course[i] = infosTab;

        }

        afficherTableau();
        boolean mainLoop = true;

        int choix;
        do {

            System.out.print("0.) Quitter \n");
            System.out.print("1.) Afficher les coureurs.\n");
            System.out.print("2.) Afficher le classement provisoire.\n");
            System.out.print("3.) Enregistrer une arrivée.\n");
            System.out.print("4.) Enregistrer un abandon.\n");
            System.out.print("5.) Enregistrer une disqualification.\n");
            System.out.print("6.) Enregistrer le temps d'arrivée d'un coureur.\n");
            System.out.print("7.) Obtenir le temps d'un coureur.\n");
            System.out.print("8.) Obtenir l'écart de temps entre deux coureurs donnés\n");
            System.out.print("\nEntrez votre choix: ");

            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 0:
                    System.out.println("Fermeture du programme...");
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Afficher les coureurs");
                    afficherTableau();
                    System.out.println();
                    break;
                case 2:
                    System.out.println("Classement provisoire");
                    classementProvisoire();
                    System.out.println();
                    break;

                case 3:
                    System.out.println("Enregistrer une arrivée");
                    enregistrerArrivee();
                    break;

                case 4:
                    System.out.println("Enregistrer un abandon");
                    enregistrerAbandon();
                    System.out.println();
                    break;

                case 5:
                    System.out.println("Enregistrer une disqualification");
                    enregistrerDisqualification();
                    System.out.println();
                    break;
                case 6:
                    System.out.println("Enregistrer le temps d'arrivée d'un coureur");
                    enregistrerTempsCoureur();
                    System.out.println();
                    break;
                case 7:
                    System.out.println("Obtenir le temps d'un coureur");
                    obtenirTemps();
                    System.out.println();
                    break;
                case 8:
                    System.out.println("Trouver l'écart de temps entre deux coureurs");
                    ecartTemps();
                    System.out.println();
                    break;
                default:
                    System.out.println(choix + " n'est pas valide, choisissez-en un autre.");

            }
        } while (choix != 0 /* Quitter la loupe quand le choix = 0 */);

    }

    public static void classementProvisoire() {
        Arrays.sort(course, new Comparator<String[]>() { // sort the array

            @Override
            public int compare(String[] o1, String[] o2) {
                try {
                    return FORMATTER.parse(o1[3]).compareTo(FORMATTER.parse(o2[3]));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return -1;
            }
        });
        for(int i = 0; i < course.length; i++){ // display the result
            for(int j = 0; j < course[i].length; j++)
            {
                System.out.print(course[i][j]);
                if(j < course[i].length - 1) System.out.print(" ");
            }
            System.out.println();
        }
    }

    private static void obtenirTemps() {
        afficherTableau();
        System.out.println("Saisir le numéro du coureur pour obtenir son temps :");
        String numero = sc.nextLine();
        int indice = find(course, numero);
        // if indice < course.length : => l'indice existe
        if (indice != -1 && indice < course.length) {
            System.out.println("Temps du coureur N°" + numero + " : " + course[indice][3]);
        } else {
            System.out.println("Le numéro que vous avez saisi n'existe pas !");
        }
    }

    private static void ecartTemps() throws ParseException {
        System.out.println("Ecart entre deux coureurs");
        afficherTableau();
        System.out.println("Saisir le numéro du 1er coureur: ");
        String coureur1 = sc.nextLine();
        System.out.println("Saisir le numéro du 1er coureur: ");
        String coureur2 = sc.nextLine();

        int indice1 = find(course, coureur1);
        int indice2 = find(course, coureur2);

        if (indice1 != -1 && indice1 < course.length && indice2 != -1 && indice2 < course.length ) {
            String temps1 = course[indice1][3];
            String temps2 = course[indice2][3];
            Date date1 = FORMATTER.parse(temps1);
            Date date2 = FORMATTER.parse(temps2);
            long difference = date2.getTime() - date1.getTime();
            System.out.println("\n l'écart entre les deux coureurs est de " + difference/60000 + " minutes.");
        } else {
            System.out.println("L'un des coureurs n'existe pas !");
        }

    }


    public static void enregistrerDisqualification() {
        afficherTableau();
        System.out.println("Saisir le numéro du coureur pour enregistrer disqualifié :");
        String numero = sc.nextLine();
        int indice = find(course, numero);
        // if indice < course.length : => l'indice existe
        if (indice != -1 && indice < course.length) {
            course[indice][2] = "disqualifié";

            course[indice][3] = "";

            afficherTableau();
        } else {
            System.out.println("Le numéro que vous avez saisi n'existe pas !");
        }
    }

    public static void enregistrerAbandon() {
        afficherTableau();
        System.out.println("Saisir le numéro du coureur pour enregistrer son abandon :");
        String numero = sc.nextLine();
        int indice = find(course, numero);
        // if indice < course.length : => l'indice existe
        if (indice != -1 && indice < course.length) {
            course[indice][2] = "abandon";

            course[indice][3] = "";

            afficherTableau();
        } else {
            System.out.println("Le numéro que vous avez saisi n'existe pas !");
        }
    }

    public static void enregistrerArrivee() {
        afficherTableau();
        System.out.println("Saisir le numéro du coureur pour enregistrer son arrivéé :");
        String numero = sc.nextLine();
        int indice = find(course, numero);
        // if indice < course.length : => l'indice existe
        if (indice != -1 && indice < course.length) {
            course[indice][2] = "arrive";

            course[indice][3] = FORMATTER.format(new Date(System.currentTimeMillis()));

            afficherTableau();
        } else {
            System.out.println("Le numéro que vous avez saisi n'existe pas !");
        }
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
