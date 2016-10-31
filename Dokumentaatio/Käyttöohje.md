# Käyttöohje
Ohjelmasta löytyy ajettava .jar tiedosto projektin juuresta nimeltä Labyrintin Ratkaisja.jar
Ohjelman käyttöliittymä on mielestäni aika käyttäjäystävällinen. Kun ohjelman käynnistää luo se valmiiksi satunnaisen labyrintin. Labyrinttia pystyy zoomaamaan käyttämällä hiiren scrollia kursorin ollessa labyrintille varatun alueen päällä. Labyrintin pystyy ratkaisemaan eri algoritmeilla painamalla niitä vastaavia painikkeita ikkunan vasemmassa laidassa. Save painikkeesta voi tallentaa nykyisen labyrintin kuvan tietokoneelleen. Kuva tallentuu images kansioon, joka luodaan samaan kansioon josta .jar tiedosto ajettiin ellei sitä ole olemassa. 

Menu painike tuo esiin ohjelman valikon:
* Generate Maze: Avaa ikkunan uuden labyrintin luomista varten.
* Data Logger: Avaa ikkunan usean labyrintin luomiseksi kerralla. Avaa listan, jossa on ajat jokaisen labyrintin generoinnista ja ratkaisusta sekä mahdollisuus tarkastella miltä labyrintti näyttää.
* Help: Avaa ikkunan, jossa on lueteltuna mitä kaikki eri värit labyrintissa tarkoittavat.
* Quit Program: Sulkee ohjelman.

### New maze ikkuna
* Width: Haluttu labyrintin leveys. Huom! Kyseessä on ruutujen määrä labyrintissa leveyssuunnassa eli labyrintin koko on oikeasti suurempi.
* Height: Haluttu labyrintin korkeus. Sama homma kun leveydessä.
* Seed: Labyrintin seedi. Sama seedi samankokoisessa labyrintissa generoituna samalla algoritmilla luo aina samanlaisen labyrintin.
* Genreration algorithm: Labyrintin generointialgoritmi.
* Close: Sulkee New maze- ikkunan luomatta uutta labyrinttia.
* Generate: Sulkee New maze- ikkunan ja luo uuden labyrintin.

### Log data ikkuna
* Width: Haluttu labyrintin leveys. Huom! Kyseessä on ruutujen määrä labyrintissa leveyssuunnassa eli labyrintin koko on oikeasti suurempi.
* Height: Haluttu labyrintin korkeus. Sama homma kun leveydessä.
* Mazes: Kuinka monta labyrinttia halutaan generoida.
* Genreration algorithm: Labyrinttien generointialgoritmi.
* Seeded: Halutaanko labyrinteista satunnaisia vai seedattuja. Seedina toimii labyrintin järjestysluku-1 eli 0,1,2,...,Mazes-1
* Close: Sulkee Log data- ikkunan luomatta labyrintteja.
* Log Data: Sulkee Log data- ikkunan ja luo listan labyrinteista. Huom! Listan luomisessa saattaa kestää, jos luo monta isoa labyrinttia. Varsinkin jos ei käytä niiden generoimiseen Primin algoritmia.

### Taulukko labyrintteja
Taulukon sarakkeiden paikkoja voi vaihdella ja taulukkoa voi järjestää eri tietojen mukaan painamalla sarakkeen otsikkoa vasemmalla hiiren painikkeella. Taulukon tiedot voi tallentaa suoraan tiedostoon, jota exel voi lukea painamalla taulukon alareunassa olevaa Export to exel painiketta. Ohjelman suorituskansioon luodaan tiedostoille exel niminen kansio, jos sitä ei ole vielä olemassa.
