import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MioRistorante {
    static String[] id = new String[3]; //gli array in java sono come degli oggetti, che vanno quindi inizializzati
    static int[] nascita = new int[3];
    static int[] registrazioneG = new int[3];
    static int[] registrazioneM = new int[3];
    static int[] registrazioneA = new int[3];
    static int numRecord = 0;

    static Scanner tastiera = new Scanner(System.in);

    public static void main(String[] args) {
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
        System.out.println("Scelta 1: Inserisci un cliente");
        System.out.println("Scelta 2: Cerca un cliente");
        System.out.println("Scelta 3: Stampa i dati di tutti i clienti");
        System.out.println("Scelta 100: Esci da un'applicazione");
    }

    public static void esegui(int scelta) {
        //System.out.println("Hai inserito il numero: " + scelta);
        switch (scelta) {
            case 1:
                System.out.println("Inserisci un cliente");
                inserisci();
                break;
            case 2:
                System.out.println("Cerca un cliente");
                break;
            case 3:
                System.out.println("Stampa i dati di tutti i clienti");
                stampa_clienti();
                break;
            case 100:
                System.out.println("Esci dall'applicazione");
                break;
            default:
                System.out.println("Scelto altro");
                break;
        }
    }

    //estensione array
    public static void estendi(){
        int newLenght = numRecord * 2;

        id = Arrays.copyOf(id, newLenght);
        nascita = Arrays.copyOf(nascita, newLenght);
        registrazioneG = Arrays.copyOf(registrazioneG, newLenght);
        registrazioneM = Arrays.copyOf(registrazioneM, newLenght);
        registrazioneA = Arrays.copyOf(registrazioneA, newLenght);
    }

    public static void inserisci() {
        String idCliente;
        int registrazioneGCliente;
        int registrazioneMCliente;
        int registrazioneACliente;

        tastiera.skip("\n"); // svuotiamo il buffer di lettura prima di leggere

        //controllo se il vettore è pieno e nel caso lo si estende
        if (numRecord >= id.length) estendi();

        do {
            System.out.println("Inserisci l'id: ");
            idCliente = tastiera.nextLine();
            if (idCliente.isEmpty()) {
                idCliente = genera_id();
                break;
            }
        } while (id_esistente(idCliente));
        id[numRecord] = idCliente;

        System.out.println("Inserisci l'anno di nascita: ");
        int nascitaCliente = tastiera.nextInt();
        nascita[numRecord] = nascitaCliente;

        do {
            System.out.println("Inserisci il giorno della registrazione: ");
            registrazioneGCliente = tastiera.nextInt();
        } while (registrazioneGCliente <= 0 || registrazioneGCliente > 31);
        registrazioneG[numRecord] = registrazioneGCliente;

        do {
            System.out.println("Inserisci il mese della registrazione: ");
            registrazioneMCliente = tastiera.nextInt();
        } while (registrazioneMCliente <= 0 || registrazioneMCliente > 12);
        registrazioneM[numRecord] = registrazioneMCliente;

        do {
            System.out.println("Inserisci l'anno della registrazione: ");
            registrazioneACliente = tastiera.nextInt();
        } while (registrazioneACliente < 1900 || registrazioneACliente > 2024);
        registrazioneA[numRecord] = registrazioneACliente;

        numRecord++;
    }

    public static String genera_id() {
        Random rand = new Random();
        int n;
        String random_id;

        do {
            n = rand.nextInt(10000);
            random_id = "user_" + String.format("%04d", n);
        } while (id_esistente(random_id));

        return random_id;
    }

    public static boolean id_esistente(String new_id) {
        for (int i = 0; i < numRecord; i++) {
            if (id[i].equals(new_id)) {
                return true;
            }
        }
        return false;
    }

    public static void stampa_clienti() {
        for (int i = 0; i < numRecord; i++) {
            System.out.println("Utente " + i+1 + ": " + id[i] + " " + nascita[i] + " " + registrazioneG[i] + "/" + registrazioneM[i] + "/" + registrazioneA[i]);
        }
    }
}
