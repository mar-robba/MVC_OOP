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
                cerca();
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
    public static void estendi() {
        /***Marco per Rie, cancellalo quando hai letto il test su questa funzione richiede che tale abbia validità generale;
         * per esempio: se numRecord fosse uguale a 0 la  funzione non riuscirebbe ad estendere gli array
         */
        int newLenght = id.length * 2;

        id = Arrays.copyOf(id, newLenght);
        nascita = Arrays.copyOf(nascita, newLenght);
        registrazioneG = Arrays.copyOf(registrazioneG, newLenght);
        registrazioneM = Arrays.copyOf(registrazioneM, newLenght);
        registrazioneA = Arrays.copyOf(registrazioneA, newLenght);
    }

    public static boolean inserisciCliente(String idCliente, int nascitaCliente, int registrazioneGCliente ,int registrazioneMCliente, int registrazioneACliente){

        boolean RiuscitaInserimento = true;

                        //**condizioni sulla validità dell'input**//
        /***Marco: aggiungere la condizione sul range 1000 9999*/
        //sull'id
        if (id_esistente(idCliente) || idCliente.isEmpty())              RiuscitaInserimento = false;

        //sulla nascita
        if (nascitaCliente < 1900)                                       RiuscitaInserimento = false;

        //sul giorno della registrazione
        if (registrazioneGCliente <= 0 || registrazioneGCliente > 31)    RiuscitaInserimento = false;

        //sul mese della registrazione
        if(registrazioneMCliente <= 0 || registrazioneMCliente > 12)     RiuscitaInserimento = false;

        //sull'anno della registrazione
        if(registrazioneACliente < 1900)                                 RiuscitaInserimento = false;

        /***Marco: dove ho spostato la parte di gestione dei clienti come da consegna*/
        if (RiuscitaInserimento){
            //controllo se il vettore è pieno e nel caso lo si estende
            if (numRecord >= id.length) estendi();

            id[numRecord] = idCliente;
            nascita[numRecord] = nascitaCliente;
            registrazioneG[numRecord] = registrazioneGCliente;
            registrazioneM[numRecord] = registrazioneMCliente;
            registrazioneA[numRecord] = registrazioneACliente;

            numRecord++;
            System.out.println("\n Il cliente è stato registrato\n");
            return true;
        }else {
            System.out.println("\nIl cliente non è stato registrato\n");
            return false;
        }
    }

    public static void inserisci() {
        String idCliente;
        int nascitaCliente;
        int registrazioneGCliente;
        int registrazioneMCliente;
        int registrazioneACliente;

        tastiera.skip("\n"); // svuotiamo il buffer di lettura prima di leggere


        System.out.println("Inserisci l'id: ");
        idCliente = tastiera.nextLine();
        if (idCliente.isEmpty()) idCliente = genera_id();

        System.out.println("Inserisci l'anno di nascita: ");
        nascitaCliente = tastiera.nextInt();

        System.out.println("Inserisci il giorno della registrazione: ");
        registrazioneGCliente = tastiera.nextInt();

        System.out.println("Inserisci il mese della registrazione: ");
        registrazioneMCliente = tastiera.nextInt();

        System.out.println("Inserisci l'anno della registrazione: ");
        registrazioneACliente = tastiera.nextInt();

        inserisciCliente(idCliente,nascitaCliente,registrazioneGCliente,registrazioneMCliente,registrazioneACliente);
    }


    public static String genera_id() {
        Random rand = new Random();
        int n;
        String random_id;

        /**Marco: se trovate un metodo meno primordiale per generare il valore nel range sarebbe meglio, si dovrebbe cercare
         * nella documentazione di Random
         */
        do {
            n = rand.nextInt() % (9999 - 999) + 1000;
            random_id = "user_" + String.format("%04d", n);
        } while (id_esistente(random_id));

        return random_id;
    }

    public static boolean id_esistente(String new_id) {
        for (int i = 0; i < numRecord; i++) {
            if (id[i] != null && id[i].equals(new_id)) {
                return true;
            }
        }
        return false;
    }

    public static void cerca() {
        int cliente = -1;

        tastiera.skip("\n"); // svuotiamo il buffer di input

        System.out.println("Id cliente da cercare: ");
        String id_ricerca = tastiera.nextLine();

        for (int i = 0; i < numRecord; i++) {
            if (id[i].equals(id_ricerca)) {
                cliente = i;
                break;
            }
        }

        if (cliente == -1) {
            System.out.println("Nessun cliente con l'id selezionato");
        } else {
            System.out.println(
                "Id: " + id[cliente] +
                ", anno di nascita: " + nascita[cliente] +
                ", data di registrazione: " + registrazioneG[cliente] + "/" + registrazioneM[cliente] + "/" + registrazioneA[cliente]
            );
        }
    }

    public static void stampa_clienti() {
        for (int i = 0; i < numRecord; i++) {
            System.out.println("Utente " + (i + 1) + ": " + id[i] + " " + nascita[i] + " " + registrazioneG[i] + "/" + registrazioneM[i] + "/" + registrazioneA[i]);
        }
    }
}
