# Toteutusdokumentti

### Yleisrakenne
Ohjelman runkona toimii Mazesolver luokka, joka luo labyrintin ja graafisen käyttöliittymän oliot. Labyrintin tietorakenneluokka Maze 
käyttää Generator luokan algoritmeja labyrintin luomiselle.
Labyrintin ratkaisemisesta huolehtii Solver luokka, joka käyttää A-star, breadth-first search ja iterative deepening A-star algoritmeja. Käyttöliittymäluokka Screen piirtää labyrintin näytölle käyttäen ImageConverter luokan
luomaa kuvaa labyrintistä. Menus luokka vastaa ohjelman valikosta. Loput käyttöliittymän luokat vastaavat uuden labyrintin luonnista ja
datankeräys toiminnosta, mikä listaa taulukkoon halutun määrän labyrinttejä ja ajat niiden generoimiselle ja ratkaisemiselle.

######Luokkakaavio (tulee myöhemmin)

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
  * jos ruudut, joita kyseinen seinä jakaa kuuluvat eri kokoelmiin
    * poista kyseinen seinä **O(1)**
    * yhdistä kyseisten ruutujen kokoelmat
    


## Ratkaisualgoritmien aika- ja tilavaativuudet

![BFS's](https://github.com/joonasil/labyrintin-ratkaisija/blob/master/Dokumentaatio/Kuvia/bfs.gif)

![AStar's](https://github.com/joonasil/labyrintin-ratkaisija/blob/master/Dokumentaatio/Kuvia/astar.gif)

![IDAStar's](https://github.com/joonasil/labyrintin-ratkaisija/blob/master/Dokumentaatio/Kuvia/idastar.gif)

![IDAStar's 2](https://github.com/joonasil/labyrintin-ratkaisija/blob/master/Dokumentaatio/Kuvia/ida.gif)
