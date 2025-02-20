**Refactoringbeslut för PaymentProcessor**

- Extrahering av beroenden

    Beslut: Beroenden (PaymentApi, DatabaseConnection, EmailService) har extraherats till interface.
    Anledning: Förbättrar testbarhet och flexibilitet genom att frikoppla PaymentProcessor från konkreta implementationer.

- Dependency Injection

    Beslut: Beroenden injiceras nu via konstruktorn.
    Anledning: Följer Dependency Inversion Principle (DIP) och gör klassen enklare att testa med mock-objekt.

- Borttagning av hårdkodade värden

    Beslut: API-nyckeln skickas nu som en parameter istället för att vara hårdkodad.
    Anledning: Förbättrar säkerheten och gör det möjligt att konfigurera API-nyckeln externt.

- Förbättrad testbarhet

    Beslut: Klassen är nu fullt testbar genom att använda mock-objekt för sina beroenden.
    Anledning: Säkerställer att klassen beter sig som förväntat utan att förlita sig på externa system.

- Skapande av PaymentApiResponse

    Beslut: En PaymentApiResponse-klass har skapats för att kapsla in svaret från betalnings-API:et.
    Anledning: Förbättrar typsäkerhet och gör koden mer lättläst.
