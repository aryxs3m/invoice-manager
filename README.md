# invoice-manager
Springben készült REST API az Invoice Manager szoftver Angular frontendjéhez.

## Telepítés menete
0. hozz létre egy üres adatbázist a használt adatbáziskezelődben (MySQL-lel tesztelve),
1. maven csomagok szinkronizálása,
2. `application.properties` fájlban a JDBC URL-t, felhasználónevet, jelszót,
3. `InvoiceManagerApplication` `main()` függvényét indítsd el

## Egyebek

- A projekt Hibernate-et használ, így a táblákat létrehozza első indításkor.

## Könyvtárszerkezet

- **dtos** - DTO-k, API-nak küldött üzenetek adataihoz,
- **filters** - Request filterek,
- **jpamodels** - Adatbázis modellek,
- **models** - Request modellek, Spring Authhoz használt modellek,
- **repositories** - JPA Repository-k,
- **services** - Service-ok,
- **util** - segédosztályok, pl. exception handler.

## Licensz

MIT License.

## Kapcsolat

[Tóth Patrik](mailto:aryx@pvga.hu)