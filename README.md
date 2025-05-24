# Ticketify Java

## 🧩 Etapa I: Definirea sistemului și proiectarea aplicației

### Tema: Sistem de gestionare a biletelor pentru evenimente

Ticketify este o aplicație Java care permite utilizatorilor să caute, să rezerve și să achiziționeze bilete pentru diverse tipuri de evenimente.

---

### 1️⃣ Definirea sistemului

#### ✅ Acțiuni / Interogări posibile (minim 10):

1. Adaugă o locație nouă
2. Caută o locație după nume
3. Șterge o locație
4. Adaugă un eveniment nou
5. Actualizează detaliile despre un eveniment
6. Șterge un eveniment
7. Afișează evenimentele după un tip specificat
8. Afișează evenimentele care vor avea loc în viitor
9. Afișează evenimentele din subclasa `EvenimentSportiv`
10. Afișează evenimentele din subclasa `EvenimentCultural`
11. Rezervă unul sau mai multe locuri la un eveniment
12. Crează un cont de cont
13. Cumpără un bilet
14. Validează un bilet
15. Afișează lista de bilete ale unui client specificat


#### 📦 Tipuri de obiecte (minim 8):

1. `Bilet` – reprezintă un bilet cumpărat pentru un eveniment  
2. `Client` – reprezintă un utilizator al aplicației  
3. `Eveniment` – clasa de bază pentru orice tip de eveniment  
4. `EvenimentCultural` – extinde `Eveniment`, adaugă informații specifice  
5. `EvenimentSportiv` – extinde `Eveniment`, adaugă detalii legate de echipe
6. `Locatie` – conține informații despre locul desfășurării evenimentului  
7. `TipEveniment` – enumerare a tipurilor posibile de evenimente  
8. `Status` – enumerare pentru starea biletelor


---

### 2️⃣ Proiectarea aplicației și detalii de implementare

Aplicația este implementată în Java pe baza celor definite mai sus.

#### ✅ Cerințe respectate:

- Clase simple cu atribute `private` / `protected` și metode de acces  
- Utilizarea a cel puțin două colecții diferite pentru gestionarea obiectelor:
  - `List` pentru fiecare clasă, stocate în pachetul Repositories
  - `Map` pentru numărul de evenimente de fiecare tip
- Colecție sortată:
  - Sortare clienți după număr bilete și nume
  - Sortare bilete după preț
- Utilizare moștenire (`EvenimentCultural`, `EvenimentSportiv`)
- Clase de serviciu (`ServiciuBiletImplementation`, `ServiciuClientImplementation`, `ServiciuEvenimentImplementation`, `ServiciuLocatieImplementation`)
- Clasa `Main` pentru testarea funcționalităților
