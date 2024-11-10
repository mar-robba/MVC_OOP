package upo20052959.ristorante;

import java.util.Arrays;
import java.util.Scanner;
import java.security.SecureRandom;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MioRistorante {
    static String[] id = new String[3]; //gli array in java sono come degli oggetti, che vanno quindi inizializzati
    static int[] nascita = new int[3];
    static int[] registrazioneG = new int[3];
    static int[] registrazioneM = new int[3];
    static int[] registrazioneA = new int[3];

    //Matrice richiesta per l'inserimento di ordini
    static int[][] numeroPiatti = new int[3][4];
    static String[][] tipoMenu = new String[3][4];

    static int numRecord = 0;
    static int[] numOrdini = new int[3];

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
        System.out.println("Scelta 3: Cerca clienti per età");
        System.out.println("Scelta 4: Aggiungi un ordine");
        System.out.println("Scelta 5: Stampa statistiche numero piatti");
        System.out.println("Scelta 6: Stampa statistiche tipo menu");
        System.out.println("Scelta 7: Stampa i dati di tutti i clienti");
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
                ricerca();
                break;
            case 3:
                System.out.println("Cerca un cliente");
                ricercaEta();
                break;
            case 4:
                System.out.println("Aggiungi un ordine");
                aggiungiOrdine();
                break;
            case 5:
                System.out.println("Statistiche numero piatti");
                statisticheNumeroPiatti();
                break;
            case 6:
                System.out.println("Statistiche tipo menu");
                statisticheMenu();
                break;
            case 7:
                System.out.println("Stampa i dati di tutti i clienti");
                stampa_clienti();
                break;
            case 100:
                System.out.println("Esci dall'applicazione");
                break;
            default:
                System.out.println("Selezionato codice non valido");
                break;
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

        if (inserisciCliente(idCliente, nascitaCliente,
                registrazioneGCliente, registrazioneMCliente,
                registrazioneACliente)) {
            System.out.println("Cliente inserito correttamente");
        } else {
            System.out.println("Cliente non inserito correttamente");
        }
    }

    public static boolean inserisciCliente(String idCliente, int nascitaCliente,
                                           int registrazioneGCliente, int registrazioneMCliente,
                                           int registrazioneACliente) {
        if (id_esistente(idCliente) || idCliente.isEmpty() ||
                nascitaCliente < 1900 ||
                registrazioneGCliente <= 0 || registrazioneGCliente > 31 ||
                registrazioneMCliente <= 0 || registrazioneMCliente > 12 ||
                registrazioneACliente <= 1900) {
            return false;
        }
        //controllo se il vettore è pieno e nel caso lo si estende
        if (numRecord >= id.length)
            estendi();

        id[numRecord] = idCliente;
        nascita[numRecord] = nascitaCliente;
        registrazioneG[numRecord] = registrazioneGCliente;
        registrazioneM[numRecord] = registrazioneMCliente;
        registrazioneA[numRecord] = registrazioneACliente;

        numRecord++;
        return true;
    }

    //estensione array
    public static void estendi() {

        int newLenght = id.length * 2;

        id = Arrays.copyOf(id, newLenght);
        nascita = Arrays.copyOf(nascita, newLenght);
        registrazioneG = Arrays.copyOf(registrazioneG, newLenght);
        registrazioneM = Arrays.copyOf(registrazioneM, newLenght);
        registrazioneA = Arrays.copyOf(registrazioneA, newLenght);

        // Dobbiamo estendere anche le matrici degli ordini
        numOrdini = Arrays.copyOf(numOrdini, newLenght);
        numeroPiatti = Arrays.copyOf(numeroPiatti, newLenght);
        tipoMenu = Arrays.copyOf(tipoMenu, newLenght);
    }

    public static String genera_id() {
        SecureRandom rand = new SecureRandom();
        int n;
        String random_id;

        do {
            n = rand.nextInt(9000) + 1000;
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

    public static void ricerca() {
        tastiera.skip("\n"); // svuotiamo il buffer di input

        System.out.println("Id cliente da cercare: ");
        String idRicerca = tastiera.nextLine();

        int cliente = ricercaCliente(idRicerca);

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

    // cerca un cliente tramite l'id
    public static int ricercaCliente(String idRicerca) {
        int cliente = -1;

        for (int i = 0; i < numRecord; i++) {
            if (id[i].equals(idRicerca)) {
                cliente = i;
                break; // possiamo uscire perché gli id sono unici
            }
        }

        return cliente;
    }

    public static void ricercaEta() {
        String[] idClienti;

        tastiera.skip("\n");

        int etaMin;
        System.out.println("Cercare clienti con età superiore a: ");
        // Se l'utente non inserisce un numero usiamo come età minima 0 (quindi
        // selezioniamo tutti i clienti con età inferiore a etaMax)
        if (tastiera.hasNextInt()) {
            etaMin = tastiera.nextInt();
        } else {
            etaMin = 0;
        }

        System.out.println("Cercare clienti con età inferiore a: ");
        int etaMax = tastiera.nextInt();

        idClienti = ricercaEtaCliente(etaMin, etaMax);

        for (String s : idClienti) {
            System.out.println("Id: " + s);
        }
    }

    // La funzione ritorna un'array con gli id di tutti i clienti con età
    // compresa tra minimo e massimo
    public static String[] ricercaEtaCliente(int etaMin, int etaMax) {
        String[] idClienti = new String[numRecord];
        int eta;

        for (int i = 0, j = 0; i < nascita.length; i++) {
            eta = 2024 - nascita[i];
            if (etaMin <= eta && etaMax > eta) {
                idClienti[j] = id[i];
                j++;
            }
        }

        return idClienti;
    }

    public static void stampa_clienti() {
        for (int i = 0; i < numRecord; i++) {
            System.out.println("Utente " + (i + 1) + ": " + id[i] + " " + nascita[i] + " " +
                    registrazioneG[i] + "/" + registrazioneM[i] + "/" + registrazioneA[i]);
        }
    }

    public static void aggiungiOrdine() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci l'ID del cliente: ");
        String idCliente = scanner.nextLine();

        System.out.print("Inserisci il numero di piatti: ");
        int numPiattiCliente = scanner.nextInt();
        System.out.print("Inserisci il tipo di menù: ");
        String tipoMenuCliente = scanner.next();

        // Qui salviamo il codice di ritorno della funzione per controllare eventuali errori
        int res = aggiungiOrdineCliente(idCliente, numPiattiCliente, tipoMenuCliente);

        switch (res) {
            case 0:
                System.out.println("Ordine aggiunto correttamente");
                break;
            case -1:
                System.out.println("Cliente non trovato");
                break;
            case -2:
                System.out.println("Il cliente ha già il numero massimo di ordini");
                break;
            case -3:
                System.out.println("Il cliente ha ordinato un numero di piatti non valido");
                break;
            case -4:
                System.out.println("Il cliente ha selezionato un menu non valido");
                break;
            default:
                System.out.println("Qualcosa non va");
                break;
        }
    }

    // La funzione ritorna un codice di errore o 0 se tutto era corretto
    public static int aggiungiOrdineCliente(String idCliente, int numPiattiCliente, String tipoMenuCliente) {
        int indiceCliente = ricercaCliente(idCliente);

        if (indiceCliente < 0) {
            return -1;
        }

        // Controlla se c'è spazio per ordini
        int indiceOrdine = numOrdini[indiceCliente];

        // Per ogni tipo di problema usiamo un return diverso, e useremo 0 come 'tutto ok'
        if (indiceOrdine >= numeroPiatti[indiceCliente].length) {
            return -2; // Il cliente ha già il numero massimo di ordini
        }

        if (numPiattiCliente <= 0) {
            return -3; // Il cliente ha ordinato un numero di piatti non valido
        }

        if (tipoMenuCliente == null || tipoMenuCliente.isEmpty()) {
            return -4; // Il cliente ha selezionato un menu non valido
        }

        numeroPiatti[indiceCliente][indiceOrdine] = numPiattiCliente;
        tipoMenu[indiceCliente][indiceOrdine] = tipoMenuCliente.toLowerCase(); // rendiamo sempre tutto in lower case per avere dati omogenei
        numOrdini[indiceCliente]++;

        return 0;
    }

    // Stampiamo numero minimo, massimo e medio di piatti in ogni ordine
    public static void statisticheNumeroPiatti() {
        // NOTE: Ci assicuriamo che i valori di partenza minimo e massimo siano
        // estremi così che il primo valore trovato aggiorni quello di
        // partenza. Notare che non basta fare min = numeroPiatti[0][0] perché
        // non è sicuro ci sia un valore in posizione [0][0]
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int tot = 0;
        int count = 0;

        for (int[] piattiCliente : numeroPiatti) {
            for (int piatti : piattiCliente) {
                // alcuni valori non sono ancora stati inseriti e compare 0
                if (piatti <= 0) {
                    break;
                }

                if (piatti < min) {
                    min = piatti;
                }
                if (piatti > max) {
                    max = piatti;
                }
                tot += piatti;
                count++;
            }
        }

        double media = (double) tot / count;

        System.out.println("Il numero minimi di piatti in un ordine è: " + min);
        System.out.println("Il numero massimo di piatti in un ordine è: " + max);
        System.out.println("Il numero medio di piatti in un ordine è: " + media);
    }

    public static void statisticheMenu() {
        int maxNumMenu = 3;
        int numTipiMenu = 0;
        String[] tipiMenu = new String[maxNumMenu];
        int[] countMenu = new int[maxNumMenu];

        // inseriamo i dati nei nostri array
        for (String[] ordini : tipoMenu) {
            for (String ordine : ordini) {
                // alcuni valori non sono ancora stati inseriti e il tipo è null
                if (ordine == null)
                    break;
                // cerchiamo il menu
                int indiceOrdine = -1;
                for (int i = 0; i < maxNumMenu; i++) {
                    if (ordine.equals(tipiMenu[i])) {
                        indiceOrdine = i;
                        break;
                    }
                }
                // se il menu non viene trovato lo aggiungiamo
                if (indiceOrdine < 0) {
                    indiceOrdine = numTipiMenu;
                    numTipiMenu++;
                    // se non basta lo spazio estendiamo gli array
                    if (numTipiMenu >= maxNumMenu) {
                        maxNumMenu *= 2;
                        tipiMenu = Arrays.copyOf(tipiMenu, maxNumMenu);
                        countMenu = Arrays.copyOf(countMenu, maxNumMenu);
                    }
                    tipiMenu[indiceOrdine] = ordine;
                    countMenu[indiceOrdine] = 0;
                }
                // aumentiamo il conteggio per il menu
                countMenu[indiceOrdine]++;
            }
        }

        // stampiamo il numero di ordini per ogni menu
        for (int i = 0; i < numTipiMenu; i++) {
            System.out.println(tipiMenu[i] + ": " + countMenu[i]);
        }
    }
}
