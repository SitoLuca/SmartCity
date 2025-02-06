package org.smartcity.smartcity.State;

import java.util.Map;

/**
 * Interfaccia che definisce il comportamento di uno stato per un dispositivo o sensore.
 * <p>
 * Ogni implementazione di questa interfaccia deve fornire la logica per eseguire un'azione
 * specifica associata allo stato corrente, tramite il metodo {@link #doaction(int)}.
 * </p>
 */
public interface State {

    /**
     * Esegue l'azione associata allo stato corrente.
     * <p>
     * In base allo stato implementato, questo metodo esegue un'azione specifica che può
     * produrre dei dati o modificare il comportamento del sistema. I dati risultanti dall'azione
     * vengono restituiti in una {@link Map} dove le chiavi rappresentano il nome del parametro
     * e i valori i corrispondenti valori numerici.
     * </p>
     *
     * @param id l'identificativo del dispositivo o sensore su cui eseguire l'azione
     * @return una {@link Map} contenente i dati generati dall'azione, oppure {@code null} se non sono previsti risultati
     */
    Map<String, Float> doaction(int id);
}
