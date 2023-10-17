export interface Room {
  id?: any; // Eindeutige ID für den Raum
  title: string;  // Titel oder Name des Raums
  description: string; // Beschreibung des Raums
  numberOfSeats?: number; // Anzahl der Sitzplätze im Raum
  occupation?: boolean; // Belegungsstatus des Raums, z.B. "frei", "belegt" etc.
}
