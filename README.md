# Staubsauger
Ein Projekt mit dem Ziel einen Staubsaugroboter zu simulieren


## erforderliche Kriterien:
* Staubsaugrobotersimulation
  * [x] Es wird ein Raum erstellt (nicht änderbar) (mit Hindernissen und Schmutz)
  * [x] Der Roboter startet an einer bestimmten Stelle (Ladestation)
  * [x] Der Roboter kann nach vorne fahren 
  * [x] Der Roboter erkennt, wenn er eine Wand trifft 
  * [x] Der Roboter dreht in eine andere Richtung
  * [x] Der Roboter fährt in die neue Richtung weiter
  * [x] Der Roboter fährt nach einer bestimmten Zeit zum Ausgangspunkt zurück (bei 25% Akku)
  * [x] Der Roboter hat ein Gesicht
  * [x] Der Roboter saugt beim Fahren kleine Pixel weg
  * [x] Der Roboter hat einen Batteriestand, Beutelinhalt, Reparaturstatus
  * [x] Der Roboter kann laden


* UI zum Bedienen des Roboters 
  * [x] starten/stoppen der Saugfunktion
  * [x] einstellen von Saugzeiten
  * [x] zeigt den Batteriestand, Beutelinhalt, Reparaturstatus, verbleibende Saugzeit an
  

* UI zum beeinflussen der Simulation
  * [x] Geschwindigkeit
  * [x] Schmutz löschen/erstellen
  


## optionale Kriterien
* Raummanager
  * [ ] Die Umgebung des Roboters kann über eine UI geändert werden
  * [ ] Es können mehrere Räume verwaltet werden
 
 
* Intelligenz
  * [ ] Der Roboter fährt gezielte Routen im Raum ab
  * [ ] Der Roboter saugt bis er den ganzen Raum abgedeckt hat und fährt dann zur Ladestation zurück
  

* Kotstop
  * [x] Der Roboter stoppt, wenn er auf weiche Gegenstände trifft
  
## Anleitung
Zum Starten des Programms den Gradle-Task **run** ausführen

## Infos
Das Programm wurde mit Java 15 entwickelt

