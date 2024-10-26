import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MioRistorante {
    static String[] id = new String[3]; //gli array in java sono come degli oggetti, che vanno quindi inizializzati
    static int[] nascita = new int[3];
    static int[] registrazioneG = new int[3];
    static int[] registrazioneM = new int[3];
    static int[] registrazioneA = new int[3];
    static int numRecord = 0;

    public static void main(String[] args) {
        Scanner tastiera = new Scanner(System.in);
        System.out.println("Benvenuto nel software Ristorazione dell’UPO!");
        int scelta = -1;
        while (scelta < 100) {
            menu();
            scelta = tastiera.nextInt();
            esegui(scelta);
        }
    }

    public static void menu() {
        System.out.println("Cosa vuoi fare? ");
        System.out.println("Scelta 1: Inserisci un utente");
        System.out.println("Scelta 1: Inserisci un cliente");
        System.out.println("Scelta 2: Cerca un cliente");
        System.out.println("Scelta 100: Esci da un'applicazione");
    }

    public static void esegui(int scelta) {
        //System.out.println("Hai inserito il numero: " + scelta);
        switch (scelta) {
            case 1:
                System.out.println("Inserisci un cliente");
                break;
            case 2:
                System.out.println("Cerca un cliente");
                break;
            case 100:
                System.out.println("Esci dall'applicazione");
                break;
            default:
                System.out.println("Scelto altro");
                break;
        }
    }

    public static void inserisci() {
        Scanner tastiera = new Scanner(System.in);
        System.out.println("Inserisci l'id: ");
        String idCliente = tastiera.nextLine();
        id[numRecord] = idCliente;

        System.out.println("Inserisci l'anno di nascita: ");
        int nascitaCliente = tastiera.nextInt();
        nascita[numRecord] = nascitaCliente;

        System.out.println("Inserisci il giorno della registrazione: ");
        int registrazioneGCliente = tastiera.nextInt();
        if (registrazioneGCliente < 32 && registrazioneGCliente > 0) {
            registrazioneG[numRecord] = registrazioneGCliente;
        }


        System.out.println("Inserisci il mese della registrazione: ");
        int registrazioneMCliente = tastiera.nextInt();
        if (registrazioneMCliente > 0 && registrazioneMCliente < 13) {
            registrazioneM[numRecord] = registrazioneMCliente;
        }

        System.out.println("Inserisci l'anno della registrazione: ");
        int registrazioneACliente = tastiera.nextInt();
        registrazioneA[numRecord] = registrazioneACliente;
    }
}