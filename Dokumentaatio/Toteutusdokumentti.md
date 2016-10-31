# Toteutusdokumentti

### Yleisrakenne
Ohjelman runkona toimii Mazesolver luokka, joka luo labyrintin ja graafisen käyttöliittymän oliot. Labyrintin tietorakenneluokka Maze 
käyttää Generator luokan algoritmeja labyrintin luomiselle.
Labyrintin ratkaisemisesta huolehtii Solver luokka, joka käyttää A-star, breadth-first search ja iterative deepening A-star algoritmeja. Käyttöliittymäluokka Screen piirtää labyrintin näytölle käyttäen ImageConverter luokan
luomaa kuvaa labyrintistä. Menus luokka vastaa ohjelman valikosta. Loput käyttöliittymän luokat vastaavat uuden labyrintin luonnista ja
datankeräys toiminnosta, mikä listaa taulukkoon halutun määrän labyrinttejä ja ajat niiden generoimiselle ja ratkaisemiselle.  
  
### Toteutus
Lähdin toteuttamaan sovellusta generointialgoritmeista, koska ratkaisualgoritmeja on vähän turha toteuttaa ilman labyrinttejä joita ratkaista. Niinpä menin wikipediaan lukemaan labyrintin generointialgoritmeista ja päätin toteuttaa generoinnin avuksi aputietorakenteet Path ja Wall. Path kuvasti labyrintin ruutua ja Wall seinää tai käytävää kahden ruudun välillä. Sen jälkeen toteutin ensimmäisen version primin generointialgoritmista ja toString-metodin Maze luokalle, jotta voisin nähdä toimiiko generointi oikein. ToString:in avuksi tarvitsin Path tietorakenteeseen 3x3 kaksiuloitteisen taulukon kuvastamaan jokaisen ruudun "tilaa", koska Path- olioon liittyvästä listasta Wall olioita ei helposti saanut selville millä sivuilla ruutua oli seiniä ja millä käytäviä. Tästä jo huomaa, että toteutukseni oli huono ja Wall olio sisälsi käytännössä saman datan kahteen kertaan eri muodossa.  
  
Kun huomasin generointialgoritmin toimivan halutulla tavalla, halusin toteuttaa yksinkertaisen graafisen käyttöliittymän, jossa tarkastella labyrinttia. Tiesin labyrinttien koon kasvavan niin suureksi, ettei olisi mitenkään järkevää piirtää koko labyrinttia näytölle kerralla. Aloin etsiä tapaa luoda "google mapsin kaltaisen" ominaisuuden sovellukseeni, missä kuvaa pystyisi raahaamaan hiirellä ja löysin javaFx:stä tarkoitukseen sopivan ScrollPane rakenteen. Ensimmäinen ideani piirtää labyrintti näytölle oli käyttäen tekstuureja ja ruudukkoa kuten ohjelmoinnin harjoitustyössä. Tein pohjaksi tekstuurin, jossa ruudusta lähti avoin polku jokaiseen neljään suuntaan ja lisäksi tekstuurit jokaisen polun muuttamiseksi seinäksi ja ideana oli, että jokasen ruudun kuva luodaan yhdistämällä oikeat seinätekstuurit pohjatekstuuriin. Lopulta kaikkien seinien tekstuurit lisättiin ruudukkoon ja ruudukko asetettiin ScrollPaneen. Tämä toteutus kuitenkin osoittautui kelvottomaksi, koska labyrintin ei tarvinnut olla kovinkaan iso ennen kuin kuvien määrä oli niin suuri, ettei labyrinttia pystynyt raahaamaan ohjelman pätkimisen takia. Lisäksi, koska jokainen ruutu kuvattiin 3x3 ruudukkona, labyrintin sisäseinät olivat kaksi kertaa paksummat kuin ulkoseinät ja labyrintin reitti.  
  
Ennen kuin aloitin ratkaisemaan ilmenneitä ongelmia, mietin miten saisin toteutettua ratkaisualgoritmit. Tiran materiaalia selaillessani vastaan tuli labyrintin ratkaisu- esimerkki, jossa labyrintti oli tallennettu totuusarvotaulukkoon, missä 0 kuvasti seinää ja 1 kuvasti käytävää. Päätin tiran esimerkkin luettuani muuttaa generointialgoritmin luoman labyrintin tyyppiä Path[] kaksiuloitteiseksi kokonaislukutaulukoksi int[][]. Samalla ratkaisin paksujen seinien ongelman. Nyt ongelmaksi jäi vain labyrintin pirtäminen näytölle tavalla, joka ei aiheuta hirveää pätkimistä. Ratkaisu: luo kuva, joka on resoluutioltaan kaksiuloitteisen taulukon kokoinen ja värjää jokainen pikseli erikseen taulukon arvon perusteella ja skaalaa kuvaa isommaksi. Vihdoin käyttöliittymä jota pystyi käyttämään, joten pystyin jatkamaan sovelluksen toteutusta.

Toteutin Breadth-First Search ja A-Star hakualgoritmit, jotka etsivät lyhyimmän reitin labyrintin vasemmasta ylänurkasta oikeaan alanurkkaan. Valitsemani toteutus johtaa väistämättä siihen, että Breadth-First Search on nopeudessa paljon lähempänä A-Staria, kuin vaikka tilanteessa jossa olisin valinnut alkupisteeksi labyrintin keskipisteen ja maaliksi jonkin kulmista, koska Breadth-First Search ei pääse periaatteessa mitenkään poispäin maalista. Tämän jälkeen aloitin toteuttamaan omia tietorakenteita ja lisäsin toisen labyrintin generointialgoritmin (Depth-First Search).    
  
Huomasin generointialgoritmien olevan huomattavasti ratkaisualgoritmeja hitaampia, vaikka generoinnin ei pitäisi viedä kuin vain lineaarisen verran aikaa, koska generointialgoritmin pitää käydä jokaisessa ruudussa vain kerran. Syynä tähän oli käyttämäni aputietorakenteet Path ja Wall, jotka rajoittivat käytettävän tietorakenteen hakupuihin, koska tarvitsin generointiin tietorakenteen jossa on mahdollisimman nopeat lisäys ja satunnaisen alkion poisto toiminnot (hakupuiden kohdalla molemmat O(log n)). Koska generoinnin lopuksi labyrintti kumminkin muutettaisiin kaksiuloitteiseksi taulukoksi, mietin voisinko mitenkään generoida labyrinttia suoraan kyseiseen taulukkoon. Ongelman oli se, että tarvitsin keinon viitata kaksiuloitteisen taulukon niihin koordinaatteihin, jotka ovat labyrintin ruutuja. Joten miten tunnistaa, onko tietty kaksiuloitteisen taulukon koordinaatti ruutu vai seinä? Ongelma on siis seuraavanlainen:  
  
 
######Labyrintin esitysmuoto:  
000000000  
010111010  
010101010  
011101110  
000100000  
011111110  
000000000
  
Yllä olevassa labyrintissa on siis ruutuja 4x3 = 12, vaikka taulukon koko on 9x7 = 63. Nyt siis esim. labyrintin kaikkein ylävasemmalla olevin ruutu on labyrintin taulukossa indeksissä 10 ja sitä vastaa indeksi 0 pelkkien ruutujen listassa. Vastaavasti labyrintin indeksissä 12 olevaa ruutua vastaa indeksi 1. Nyt siis esimerkiksi kun luodaan uusi labyrintti ja halutaan asettaa sattumanvarainen ensimmäinen ruutu osaksi labyrinttia generointialgoritmin alussa, pitää valita ensin jokin ruuduista 0-11 ja sen jälkeen muuttaa valittu luku vastaavaksi labyrintin taulukon indeksiksi. Kaksiuloitteisen taulukon (x,y)- koordinaatin voi muuttaa yksiuloitteisen taulukon indeksiksi kaavalla `indeksi = y-koordinaatti * kaksiuloitteisen taulukon leveys + x-koordinaatti` ja yksiuloitteisen taulukon indeksin voi muuttaa takaisin kaksiuloitteisen taulukon koordinaatiksi kaavoilla `x-koordinaatti = indeksi mod kaksiuloitteisen taulukon leveys` ja `y-koordinaatti = indeksi / kaksiuloitteisen taulukon leveys`. 

Lähdin ratkaisemaan ongelmaa piirtämällä vihkoon erikokoisia "yksiuloittesia taulukkoja" muodossa  
012  
345  
678  
ja etsimällä yhteyttä taulukon leveyden, korkeuden, indeksin ja halutun indeksin välillä. Esimerkiksi, koska kokonaisluvuilla jaettaessa mahdollinen desimaaliosa vain jätetään pois, (4/3)-1 = 0 pitää paikkansa. Lopulta päädyin kaavaan `1 + labyrintin leveys * (1 + 2 * y-koordinaatti) + 2 * x-koordinaatti`. Nyt siis esimerkiksi, jos halutaan viitata kaikkein ylävasempaan ruutuun (indeksi 0) yllä olevassa labyrintissa, saadaan ruudun indeksiksi 1 + 9 * (1 + 2 * (0 / 4)) + 2 * (0 mod 4) = 10 kuten yllä jo mainittiin.


## Generointialgoritmien aika- ja tilavaativuudet
### Labyrintin generointi Primin algoritmilla O(n)

![Prim's](https://github.com/joonasil/labyrintin-ratkaisija/blob/master/Dokumentaatio/Kuvia/prim.gif)

Algoritmin toimintaperiaate: 
* aloitetaan ruudukosta täynnä seiniä **O(n)**
* valitaan satunnainen ruutu, merkataan se osaksi labyrinttia ja laitetaan sen seinät listaan **O(1)**
* niin kauan, kuin kyseisessä listassa on seiniä **O(n)**
  * valitaan sattumanvarainen seinä listasta. Jos vain yksi seinän jakamista ruuduista on osa labyrinttia: **O(1)**
    * tehdään seinästä käytävä ja lisätään toinenkin ruutu osaksi labyrinttia **O(1) ja O(1)**
    * lisätään labyrinttiin lisätyn ruudun seinät listaan **O(1)**
  * poistetaan kyseinen seinä listasta **O(1)**

Ruudukon (2-uloitteinen taulukko) luominen vie aikaa O(n), missä n on ruudukon leveys x ruudukon korkeus.  
Koska ruudulla on aina enintään 4 seinää, vie niiden listaan lisääminen aikaa O(4) = O(1).  
Lista on toteutettu dynaamisella taulukolla.    
Koska ruudulla on 4 seinää ja ruutuja on n kappaletta, vie while-looppi aikaa O(4n) = O(n).  
Sattumanvaraisen seinän valitseminen listasta vie aikaa O(1).  
Käytävän teko seinästä vie aikaa O(1), koska ruudukko on taulukko.  
Toisen ruudun lisäys labyrinttiin vie aikaa O(1). Ruudun seinien lisäys listaan vie aikaa O(1).  
Seinän poistaminen listasta vie aikaa O(1).  
Algoritmin siis täytyy käydä kaikki seinät läpi kerran, ja koska kaikki muutokset tehdään taulukon indekseihin O(1) ajassa, ainoaksi aikaavieväksi toiminnoksi jää taulukon alustus O(n) ja kaikkien seinien läpikäynti O(n).  
Näin ollen algoritmin aikavaativuus on O(n).  
  
Kaksiuloitteisen taulukon tilavaativuus on O(n), missä n on kaksiuloitteisen taulukon leveys x korkeus.  Listan tilavaativuus on myös O(n), koska listalla on kerralla maksimissaan 2/3 labyrintin seinistä. Näin ollen algoritmin tilavaativuus on O(n).
  
  
### Labyrintin generointi depth-first search algoritmilla O(n)

![DFS's](https://github.com/joonasil/labyrintin-ratkaisija/blob/master/Dokumentaatio/Kuvia/dfs.gif)

Algoritmin toimintaperiaate:
* valitse satunnainen ruutu ja merkitse se osaksi labyrinttia **O(1)**
* niin kauan, kuin on ruutja jotka eivät ole osa layrinttia **O(n)**
  * jos tämänhetkisellä ruudulla on naapureita, jotka eivät ole osa labyrinttia **O(1)**
    * valitaan satunnaisesti yksi naapuriruuduista **O(1)**
    * lisätään tämänhetkinen ruutu pinoon **O(1)**
    * poistetaan seinä tämänhetkisen ja valitun ruudun välillä **O(1)**
    * tehdään valitusta ruudusta tämänhetkinen ruutu ja merkataan se osaksi labyrinttia **O(1)**
  * muuten jos pino ei ole tyhjä **O(1)**
    * otetaan pinon päällimmäinen ruutu ja tehdään siitä tämänhetkinen ruutu **O(1)**
    
Ruudukon luominen vie taas aikaa O(n). Ruudukko on taas kaksiuloitteinen taulukko.  
Ruutuja on n kappaletta, joten while looppi vie aikaa O(n).  
Ruudulla on enintään neljä naapuria, joten niiden tarkistaminen taulukosta vie aikaa O(1).  
Satunnaisen ei labyrintissa olevan naapurin valinta vie aikaa O(1), koska naapurit on talletettu dynaamiseen listaan.
Pinoon lisääminen vie aikaa O(1).  
Seinän poisto tapahtuu kaksiuloitteisen taulukon arvon muokkauksella, joten se vie aikaa O(1).
Valitun ruudun asettaminen tämänhetkiseksi ruuduksi vie aikaa O(1). Merkkaaminen taas taulukon arvon muutos O(1).  
Pinon tyhjyyden tarkistaminen vie aikaa O(1).
Pinon päällimmäisen arvon hakeminen ja sen asettaminen tämänhetkiseksi ruuduksi vie aikaa O(1).  
Näin ollen algoritmin aikavaativuus on O(n).  
  
Ruudukon tilavaativus on O(n). Naapurilistan tilavaativuus on O(1), koska naapureita on enintään neljä ja vain yksi naapurilista on käytössä kerrallaan. Näin ollen algoritmin tilavaativuus on O(n).
  
### Labyrintin generointi Kruskalin algoritmilla

![Kruskal's](https://github.com/joonasil/labyrintin-ratkaisija/blob/master/Dokumentaatio/Kuvia/kruskal.gif)

Algoritmin toimintaperiaate:
* luodaan lista kaikista seinistä ja kokoelma, jossa on vain kyseinen ruutu, jokaiselle ruudulle. **O(n)**
* jokaiselle seinälle satunnaisessa järjestyksessä **O(n)**
  * jos ruudut, joita kyseinen seinä jakaa kuuluvat eri kokoelmiin **O(1) amortized**
    * poista kyseinen seinä **O(1)**
    * yhdistä kyseisten ruutujen kokoelmat **O(1) amortized**

Listan luominen vie aikaa O(n), missä n on seinien määrä labyrintissa.  
Disjoint-Set kokoelman luonti vie aikaa O(m), missä m on ruutujen määrä labyrintissa.  
While loopissa käydään läpi kaikki seinät, joten sen aikavaativuus on O(n).  
Tarkistaminen mihin kokoelmaan ruutu kuuluu vie aikaa O(1) amortized eli n määrä tarkistuksia vie aikaa O(n).  
Yksittäinen tarkistus vie aikaa yleensä O(1) ja harvoin O(n).  
Seinän poistaminen vie aikaa O(1), koska kyseessä on taulukon yhden indeksin arvon muutos.  
Kahden ruudun kokoelmien yhdistäminen vie aikaa taas O(1) amortized, koska operaatio kutsuu samaa funktiota kuin tarkistaminen ja muuten on aikavaativuudeltaan O(1).

Tilavaativuus on O(2n+m), missä n on ruutujen määrä ja m on seinien määrä.


## Ratkaisualgoritmien aika- ja tilavaativuudet

![BFS's](https://github.com/joonasil/labyrintin-ratkaisija/blob/master/Dokumentaatio/Kuvia/bfs.gif)

![AStar's](https://github.com/joonasil/labyrintin-ratkaisija/blob/master/Dokumentaatio/Kuvia/astar.gif)

![IDAStar's](https://github.com/joonasil/labyrintin-ratkaisija/blob/master/Dokumentaatio/Kuvia/idastar.gif)

![IDAStar's 2](https://github.com/joonasil/labyrintin-ratkaisija/blob/master/Dokumentaatio/Kuvia/ida.gif)
