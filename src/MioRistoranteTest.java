
public class MioRistoranteTest {

    //testa l’inserimento di un cliente tramite il metodo inserisciCliente
    public static boolean testInserisciSingolo (){
        //controlla che gli array che rappresentano i record siano tutti vuoti se no restituisce false

        String Id = "1222";
        int nascita = 1999;
        int RegistrazioneG = 12;
        int RegistrazioneM = 3;
        int RegistrazioneA = 2024;

        if(MioRistorante.id[0] != null
                ||  MioRistorante.nascita[0] != 0
                ||  MioRistorante.registrazioneG[0] != 0
                ||  MioRistorante.registrazioneM[0] != 0
                ||  MioRistorante.registrazioneA[0] != 0
        ) return false;

        if( ! MioRistorante.inserisciCliente(Id,nascita,RegistrazioneG,RegistrazioneM,RegistrazioneA)) return false;

        //controlla che piazzi il record nei primi posti
        if(MioRistorante.id[0] != null
                &&  MioRistorante.nascita[0] != 0
                &&  MioRistorante.registrazioneG[0] != 0
                &&  MioRistorante.registrazioneM[0] != 0
                &&  MioRistorante.registrazioneA[0] != 0
        ) return true;
        else return false;

    }
    /*4. Analogamente a quanto fatto al punto precedente, scrivete un test testInserisciEstendi (che verifica che
            tutto funzioni nel caso in cui gli array dei record siano pieni, e vadano estesi),*/
    public static boolean testEstendi(){
        //salvataggio delle lunghezze degli array prima dell'estensione
         int len1 = MioRistorante.id.length;
         int len2 = MioRistorante.nascita.length;
         int len3 = MioRistorante.registrazioneG.length;
         int len4 = MioRistorante.registrazioneM.length;
         int len5 = MioRistorante.registrazioneA.length;

        System.out.println("E"+MioRistorante.id.length);
        //estensione degli array
        MioRistorante.estendi();
        System.out.println("E"+MioRistorante.id.length);
        if(MioRistorante.id.length > len1
            && MioRistorante.nascita.length >  len2
            && MioRistorante.registrazioneG.length  > len3
            && MioRistorante.registrazioneM.length  > len4
            && MioRistorante.registrazioneA.length  > len5
        )return true;
        else { System.out.println("Efalse"); return false;}
    }
/*
            ed un metodo
    testInserisciErrore, che verifica che record con dati sbagliati non possano essere inseriti (es. provate i casi
            con id già presente nell’array id, e anno di nascita negativo)
    */
    public  static  boolean testInserisciErrore(){
        String Id = "-12";
        int nascita = 1700;
        int RegistrazioneG = -2;
        int RegistrazioneM = 13;
        int RegistrazioneA = 23099;

        if(MioRistorante.inserisciCliente(Id,nascita,RegistrazioneG,RegistrazioneM,RegistrazioneA)) return false;
        else return true;
    }

    //volendo racchiudere il corpo delle funzioni di test in un costrutto try catch per le eccezioni
    public static void main(String[] args) {

        boolean test1 = testInserisciSingolo();
        boolean test2 = testEstendi();
        boolean test3 = testInserisciErrore();

        if (test1) System.out.println("testInserisciSingolo: ---> OK ");
        else System.out.println("testInserisciSingolo: --> X ");

        if (test2) System.out.println("testEstendi: ------------> OK");
        else System.out.println("testEstendi: -----------> X");

        if (test3) System.out.println("testInserisciErrore: ----> OK");
        else System.out.println("testInserisciErrore: ----> X");//per passarlo implementare le condizioni di compatibilità

    }


}
