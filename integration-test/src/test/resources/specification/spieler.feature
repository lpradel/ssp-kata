# language: de
# encoding: utf-8
Funktionalität: Spieler anlegen und abrufen

  Grundlage:
    Angenommen es gibt keine Spieler

  Szenario: Leere Spielerliste abrufen
    Wenn ich alle Spieler abrufe
    Dann ist der Statuscode 200
    Dann erhalte ich eine leere Liste

  Szenario: Spieler anlegen
    Wenn ich einen Spieler mit dem Namen "Lukas" und der ClientURL "https://localhost:8080/lukas" registriere
    Dann ist der Statuscode 201
    Dann erhalte ich eine Antwort mit einer Spieler-ID

  Szenario: Spieler anlegen und Spielerliste abrufen
    Angenommen ich habe einen Spieler mit dem Namen "Lukas" und der ClientURL "https://localhost:8080/lukas" registriert
    Wenn ich alle Spieler abrufe
    Dann ist der Statuscode 200
    Dann enthält die Spielerliste einen Spieler mit dem Namen "Lukas" und der ClientURL "https://localhost:8080/lukas" und einem Score von 100 und 0 Siegen und 0 Niederlagen