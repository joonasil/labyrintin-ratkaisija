# Toteutusdokumentti

### Yleisrakenne
Ohjelman runkona toimii Mazesolver luokka, joka luo labyrintin ja graafisen käyttöliittymän oliot. Labyrintin tietorakenneluokka Maze 
käyttää Generator luokan algoritmeja labyrintin luomiselle.
Labyrintin ratkaisemisesta huolehtii Solver luokka, joka käyttää A-star, breadth-first search ja iterative deepening A-star algoritmeja. Käyttöliittymäluokka Screen piirtää labyrintin näytölle käyttäen ImageConverter luokan
luomaa kuvaa labyrintistä. Menus luokka vastaa ohjelman valikosta. Loput käyttöliittymän luokat vastaavat uuden labyrintin luonnista ja
datankeräys toiminnosta, mikä listaa taulukkoon halutun määrän labyrinttejä ja ajat niiden generoimiselle ja ratkaisemiselle.

######Luokkakaavio (tulee myöhemmin)

### Algoritmien aikavaativuudet
#### Labyrintin generointi primin algoritmilla O(n)

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

![DFS's](https://github.com/joonasil/labyrintin-ratkaisija/blob/master/Dokumentaatio/Kuvia/dfs.gif)

![Kruskal's](https://github.com/joonasil/labyrintin-ratkaisija/blob/master/Dokumentaatio/Kuvia/kruskal.gif)

![BFS's](https://github.com/joonasil/labyrintin-ratkaisija/blob/master/Dokumentaatio/Kuvia/bfs.gif)

![AStar's](https://github.com/joonasil/labyrintin-ratkaisija/blob/master/Dokumentaatio/Kuvia/astar.gif)

![IDAStar's](https://github.com/joonasil/labyrintin-ratkaisija/blob/master/Dokumentaatio/Kuvia/idastar.gif)

![IDAStar's 2](https://github.com/joonasil/labyrintin-ratkaisija/blob/master/Dokumentaatio/Kuvia/ida.gif)
