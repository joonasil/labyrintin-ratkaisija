# Toteutusdokumentti

### Yleisrakenne
Ohjelman runkona toimii Mazesolver luokka, joka luo labyrintin ja graafisen käyttöliittymän oliot. Labyrintin tietorakenneluokka Maze 
käyttää Generator luokan algoritmeja (prim's algorithm) labyrintin luomiselle. Generator luokka käyttää Path ja Wall luokkia labyrintin generoimiseen.
Labyrintin ratkaisemisesta huolehtii Solver luokka, joka käyttää A-star ja breadth first search algoritmeja. Käyttöliittymäluokka Screen piirtää labyrintin näytölle käyttäen ImageConverter luokan
luomaa kuvaa labyrintistä. Menus luokka vastaa ohjelman valikosta. Loput käyttöliittymän luokat vastaavat uuden labyrintin luonnista ja
datankeräys toiminnosta, mikä listaa halutun määrän labyrinttejä ja ajat niiden generoimiselle ja ratkaisemiselle taulukkoon.

######Luokkakaavio (tulee myöhemmin)

### Algoritmien aikavaativuudet
#### Labyrintin generointi primin algoritmilla
Algoritmin toimintaperiaate: 
* aloitetaan ruudukosta täynnä seiniä **O(n)**
* valitaan satunnainen ruutu, merkataan se osaksi labyrinttia ja laitetaan sen seinät listaan **O(4log(m)) = O(log(m))**
* niin kauan, kuin kyseisessä listassa on seiniä **O(4n) = O(n)**
* valitaan sattumanvarainen seinä listasta. Jos vain yksi seinän jakamista ruuduista on osa labyrinttia: **O(log(m))**
  * tehdään seinästä käytävä ja lisätään toinenkin ruutu osaksi labyrinttia **O(4) = O(1) ja O(1)**
  * lisätään labyrinttiin lisätyn ruudun seinät listaan **O(4log(m)) = O(log(m))**
* poistetaan kyseinen seinä listasta **O(log(m)+log(m)) = O(log(m))**

Ruudukon luominen vie aikaa O(n), missä n on ruutujen määrä labyrintissa.
Koska ruudulla on aina enintään 4 seinää, vie niiden listaan lisääminen aikaa O(4log(m)) = O(log(m)), missä m on alv-puun syvyys.
Koska ruudulla on 4 seinää ja ruutuja on n kappaletta, vie while-looppi aikaa O(4n) = O(n).
Sattumanvaraisen seinän valitseminen listasta vie aikaa O(log(m)), koska lista on toteutettu avl-puuna.
Käytävän teko seinästä vie aikaa O(4) = O(1), koska kyseinen seinä pitää etsiä labyrintissä jo olevan ruudun seinistä.
Toisen ruudun lisäys labyrinttiin vie aikaa O(1). Ruudun seinien lisäys listaan vie aikaa O(4log(m)) = O(log(m)).
Seinän poistaminen listasta vie aikaa O(log(m)+log(m)) = O(log(m)), koska avl-puu on toteutettu avain-arvo-parina (TreeMap).
Tästä nähdään, että algoritmin teoreettinen aikavaativuus on O(nlog(m)). While loopin sisällä tapahtuvien listaan kohdistuvien
operaatioiden määrä on sen verran suuri, että vaikka teoreettinen aikavaativuus ei olekkaan kuin O(nlog(m)), niin algoritmi on silti
huomattavasti hitaampi kuin labyrintin ratkaisualgoritmit, joiden teoreettinen aikavaativuus pitäisi myös olla O(nlog(n)).
