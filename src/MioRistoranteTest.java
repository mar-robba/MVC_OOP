public class MioRistoranteTest {
    //testa l’inserimento di un cliente tramite il metodo inserisciCliente
    public static boolean testInserisciSingolo() {
        //controlla che gli array che rappresentano i record siano tutti vuoti se no restituisce false
        String Id = "1222";
        int Nascita = 1999;
        int RegistrazioneG = 12;
        int RegistrazioneM = 3;
        int RegistrazioneA = 2024;

        // Controlliamo il numero di clienti inseriti nel sistema
        if (MioRistorante.numRecord != 0) {
            return false;
        }

        if (!MioRistorante.inserisciCliente(Id, Nascita, RegistrazioneG, RegistrazioneM, RegistrazioneA)) {
            return false;
        }

        //controlla che piazzi il record nei primi posti
        return (MioRistorante.id[0].equals(Id)
                && MioRistorante.nascita[0] == Nascita
                && MioRistorante.registrazioneG[0] == RegistrazioneG
                && MioRistorante.registrazioneM[0] == RegistrazioneM
                && MioRistorante.registrazioneA[0] == RegistrazioneA);
    }

    /*4. Analogamente a quanto fatto al punto precedente, scrivete un test testInserisciEstendi (che verifica che
            tutto funzioni nel caso in cui gli array dei record siano pieni, e vadano estesi),*/
    public static boolean testEstendi() {
        //salvataggio delle lunghezze degli array prima dell'estensione
        int lenId = MioRistorante.id.length;
        int lenNascita = MioRistorante.nascita.length;
        int lenRegG = MioRistorante.registrazioneG.length;
        int lenRegM = MioRistorante.registrazioneM.length;
        int lenRegA = MioRistorante.registrazioneA.length;

        // Carichiamo un nuovo cliente per vedere se viene mantenuto uguale dopo l'estensione
        String Id = "testEstensione";
        int Nascita = 1999;
        int RegistrazioneG = 12;
        int RegistrazioneM = 5;
        int RegistrazioneA = 2023;

        MioRistorante.inserisciCliente(Id, Nascita, RegistrazioneG, RegistrazioneM, RegistrazioneA);

        //estensione degli array
        MioRistorante.estendi();

        // Le dimensioni degli array devono essere aumentate
        if (MioRistorante.id.length <= lenId || MioRistorante.nascita.length <= lenNascita ||
                MioRistorante.registrazioneG.length <= lenRegG || MioRistorante.registrazioneM.length <= lenRegM ||
                MioRistorante.registrazioneA.length <= lenRegA) {
            return false;
        }

        int index = MioRistorante.numRecord - 1;

        // I valori nell'array devono essere gli stessi prima dell'estensione
        return (MioRistorante.id[index].equals(Id) &&
                MioRistorante.nascita[index] == Nascita &&
                MioRistorante.registrazioneG[index] == RegistrazioneG &&
                MioRistorante.registrazioneM[index] == RegistrazioneM &&
                MioRistorante.registrazioneA[index] == RegistrazioneA);
    }

    /*
                ed un metodo
        testInserisciErrore, che verifica che record con dati sbagliati non possano essere inseriti (es. provate i casi
                con id già presente nell’array id, e anno di nascita negativo)
        */
    public static boolean testInserisciErrore() {
        String Id = "-12";
        int nascita = 1700;
        int RegistrazioneG = -2;
        int RegistrazioneM = 13;
        int RegistrazioneA = 23099;

        return !MioRistorante.inserisciCliente(Id, nascita, RegistrazioneG, RegistrazioneM, RegistrazioneA);
    }

    //volendo racchiudere il corpo delle funzioni di test in un costrutto try catch per le eccezioni
    public static void main(String[] args) {

        boolean test1 = testInserisciSingolo();
        boolean test2 = testEstendi();
        boolean test3 = testInserisciErrore();

        if (test1) System.out.println("testInserisciSingolo: ---> OK ");
        else System.out.println("testInserisciSingolo: ---> X ");

        if (test2) System.out.println("testEstendi: ------------> OK");
        else System.out.println("testEstendi: -----------> X");

        if (test3) System.out.println("testInserisciErrore: ----> OK");
        else
            System.out.println("testInserisciErrore: ----> X");//per passarlo implementare le condizioni di compatibilità
    }
}
