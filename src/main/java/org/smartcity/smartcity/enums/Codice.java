package org.smartcity.smartcity.enums;

/**
 * Rappresenta i codici di stato utilizzati per identificare il livello di criticità di un'emergenza
 * o di una condizione monitorata nel contesto di una Smart City.
 *
 * I possibili valori sono:
 * <ul>
 *     <li><b>Rosso:</b> Indica un livello di criticità massimo, richiedendo interventi immediati.</li>
 *     <li><b>Giallo:</b> Indica un livello di criticità medio, richiedendo attenzione ma non urgente.</li>
 *     <li><b>Verde:</b> Indica una situazione normale, senza criticità.</li>
 *     <li><b>Unknown:</b> Indica uno stato sconosciuto o non definito.</li>
 * </ul>
 */
public enum Codice {
    Rosso, Giallo, Verde, Unknown
}
