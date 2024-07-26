# Traccia - Monitoraggio ambientale 
Si vuole sviluppare un sistema per la gestione di una Smart City. Una Smart City comprende un insieme di strategie di pianificazione urbanistica al fine di migliorare la qualità della vita e soddisfare le esigenze di cittadini, imprese e istituzioni.

Si suppone di gestire un sistema di monitoraggio ambientale centralizzato. In una città, per ogni strada, sono situate diverse centraline contenenti dei sensori di monitoraggio che permettono di registrare i livelli di tre parametri: inquinamento dell’aria, la temperatura e il numero di autoveicoli che transitano. Per ogni parametro l’amministratore del sistema fissa una soglia di guardia.

Il sistema di monitoraggio si può trovare in questi tre stati:

* **Codice Verde** - Se tutti e tre i parametri sono sotto soglia
* **Codice Giallo** - Se i primi due parametri sono sopra soglia
* **Codice Rosso** - Se tutti i parametri sono sopra soglia

Nel caso si verifichi il codice rosso si può applicare una delle seguenti strategie:

* Consentire il traffico solo a targhe alterne. Un dispositivo controlla automaticamente le vetture e procede con l’invio di una segnalazione alla polizia locale in caso di infrazione.
* Il flusso del traffico viene inviato su un altro percorso. \

Il sistema permette, inoltre, di inserire nuovi sensori alla rete e di fare il grafico dei parametri per un periodo fissato.

## Note di sviluppo
La prova d’esame richiede la progettazione e lo sviluppo della traccia proposta. Lo studente può scegliere di sviluppare il progetto nelle due modalità: **Applicazione Web** o **programma standalone con supporto grafico**.

Il progetto deve essere sviluppato secondo le seguenti linee:

* Usare almeno **due** pattern per persona (almeno **uno** per chi sceglie la modalità Web Application) tra i design pattern noti;

* Attenersi ai principi della programmazione SOLID;

* Usare il linguaggio Java;

* Inserire sufficienti commenti (anche per Javadoc) e annotazioni;

* Gestione delle eccezioni;

* Usare i file o database;

E' possibile costruire l'applicazione standalone con supporto grafico tramite l'utilizzo di strumenti per la realizzazione di interfacce grafiche presenti in molti IDE (GUI Designer in IntelliJ e WindowsBuilder in Eclipse) oppure utilizzare tools compatibili con JavaFx come Scene Builder (compatibile con gli IDE).

## Consegna progetto
Lo studente deve presentare una relazione sintetica (per chi usa latex è possibile scaricare un template dalla piattaforma e-learning). La relazione deve contenere:

* Una breve descrizione dei requisiti del progetto;
* Il diagramma UML delle classi;
* Altri diagrammi se opportuni;
* Parti rilevanti del codice sviluppato;
* Per chi usa latex si consiglia di utilizzare la piattaforma Overleaf:

https://www.overleaf.com/
La consegna potrà avvenire tramite email a tutti i docenti, con in allegato un archiovio con tutto relazione, codice e presentazione. In alternativa è possibile utilizzare Microsoft Teams con le stesse modalità.
